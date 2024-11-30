import { UserType } from "@/lib/validations/user.register";
import axios from "axios";
const url = process.env.NEXT_PUBLIC_API;
const api = axios.create({
  baseURL: url,
});

export const registerUser = async (userData: UserType) => {
  const {confirmPassword, ...rest} = userData 
  const res = await api.post("/user", rest);
  return res;
};
