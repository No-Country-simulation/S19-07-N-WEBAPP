import { UserType } from "@/lib/validations/user.register";
import axios from "axios";

const api = axios.create({
  baseURL: "https://myapi.com",
});

export const registerUser = async (userData: UserType) => {
  const promise = new Promise((resolve, reject) => {
    // ⬇️ Activa este bloque para que de success ⬇️
    setTimeout(() => {
      resolve("Usuario registrado! Inicie sesión.");
    }, 2000);
    // ⬇️ Activa este bloque para que de error ⬇️
    // setTimeout(() => {
    //   reject("Usuario no registrado!");
    // }, 2000);
  });
  return promise;
  // ⬇️ para cuando funcionene los endpoints ⬇️
  // return api.post("/register", userData);
};
