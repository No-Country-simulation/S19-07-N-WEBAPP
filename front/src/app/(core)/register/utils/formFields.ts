import { FormFieldType, IncomeFieldsType } from "@/lib/types/user.register";

export const BasicField: FormFieldType[] = [
  {
    name: "name",
    text: "Nombre",
    placeholder: "Jhon Doeh",
  },
];
export const AuthFields: FormFieldType[] = [
  {
    name: "email",
    text: "Correo Electrónico",
    placeholder: "jhondoe69@gmail.com",
  },
  {
    name: "password",
    text: "Clave",
    placeholder: "********",
  },
  {
    name: "confirmPassword",
    text: "Confirmar Clave",
    placeholder: "********",
  },
];

export const IncomeFields: IncomeFieldsType[] = [
  {
    name: "title",
    text: "Titulo",
    placeholder: "Compra carne",
  },
  {
    name: "description",
    text: "Descripción",
    placeholder:
      "se compro una carne al señor josé de la esquina por que luis anda de vacaciones.",
  },
  {
    name: "amount",
    text: "Monto",
    placeholder: "$35500",
  },
];
