import { UserType } from "@/lib/validations/user.register";
import { api } from "./api";

export const registerUser = async (userData: UserType) => {
  const { confirmPassword, ...rest } = userData;
  const res = await api.post("/user", rest);
  return res;
};
