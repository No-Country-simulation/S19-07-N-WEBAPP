import { FormFieldType } from "@/lib/types/user.register";

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
