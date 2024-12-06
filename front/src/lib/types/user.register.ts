import { IncomeType } from "../validations/finance.income";
import { UserType } from "../validations/user.register";

export interface FormFieldType {
  name: keyof UserType;
  text: string;
  placeholder: string;
}

export interface IncomeFieldsType {
  name: keyof IncomeType;
  text: string;
  placeholder: string;
}
