import { useEffect, useState } from "react";
import Cookies from "js-cookie";

export const useAuth = () => {
  const [user, setUser] = useState<{ access_token: string | undefined, refresh_token: string | undefined } | null>(null);

  useEffect(() => {
    const access_token = Cookies.get("access_token");
    const refresh_token = Cookies.get("refresh_token");

    setUser({
      access_token,
      refresh_token
    });

  }, []);

  const login = async ({username, password}:{username:string, password: string }) => {
    // Simulating a login response
    const data = { access_token: "mock_access_token", refresh_token: "mock_refresh_token", currency: "USD" }; // Simulated data

    if (data) {
      Cookies.set("access_token", data.access_token, { expires: 3 });
      Cookies.set("refresh_token", data.refresh_token, { expires: 10 });
      Cookies.set("currency", data.currency);
      setUser(data); // Update user state with simulated data
    }

    return data;
  };

  const logout = () => {
    Cookies.remove("refresh_token");
    Cookies.remove("access_token");
    setUser(null);
  };

  return { user, login, logout };
};