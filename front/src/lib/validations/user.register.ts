import { z, TypeOf } from "zod";

export const UserSchema = z
  .object({
    name: z
      .string()
      .trim()
      .min(1, { message: "Ingrese nombre" })
      .min(5, { message: "Nombre muy corto" })
      .regex(/^[a-zA-Z]+(?:\s[a-zA-Z]+)*$/, { message: "Solo letras" }),
    username: z
      .string()
      .trim()
      .min(1, { message: "Ingrese Usuario" })
      .min(5, {
        message: "Usuario muy corto",
      })
      .regex(/^[a-zA-Z0-9_]+$/, { message: "nombre de usuario no valido" }),
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
      }),

    confirmPassword: z.string().trim().min(1, { message: "Repita la clave" }),
    address: z.string().trim().min(1, { message: "Ingrese direccion" }),
    phone: z
      .string()
      .trim()
      .min(1, { message: "Ingrese telefono" })
      .regex(/^\d+$/, {
        message: "Solo números",
      }),
  })
  .refine((data) => data.confirmPassword === data.password, {
    message: "Las claves no coinciden",
    path: ["confirmPassword"],
  });

export type UserType = TypeOf<typeof UserSchema>;
