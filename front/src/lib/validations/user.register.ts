import { z, TypeOf } from "zod";

export const UserSchema = z
  .object({
    name: z
      .string()
      .trim()
      .min(1, { message: "Ingrese nombre" })
      .min(5, { message: "Nombre muy corto" })
      .regex(/^[a-zA-Z]+(?:\s[a-zA-Z]+)*$/, { message: "Solo letras" }),
    email: z
      .string()
      .min(1, { message: "Ingrese correo" })
      .email({ message: "Correo no válido." }),
    password: z
      .string()
      .trim()
      .min(1, { message: "Ingrese clave" })
      .min(8, { message: "Clave muy corta" })
      .regex(/^(?=.*[A-Z])/, {
        message: "Debe contener al menos una mayúscula",
      })
      .regex(/^(?=.*[a-z])/, {
        message: "Debe contener al menos una minúscula",
      })
      .regex(/^(?=.*\d)/, { message: "Debe contener al menos un número" })
      .regex(/^[A-Za-z\d]+$/, {
        message: "Clave solo Alfanumerica",
      })
      .regex(/^(?=.*[!@#$%^&*(),.?":{}|<>])/, {
        message: "Debe contener al menos un caracter",
      }),

    confirmPassword: z.string().trim().min(1, { message: "Repita la clave" }),
  })
  .refine((data) => data.confirmPassword === data.password, {
    message: "Las claves no coinciden",
    path: ["confirmPassword"],
  });

export type UserType = TypeOf<typeof UserSchema>;
