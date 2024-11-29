import { UserType } from "../validations/user.register";

export interface FormFieldType {
  name: keyof UserType;
  text: string;
  placeholder: string;
}
