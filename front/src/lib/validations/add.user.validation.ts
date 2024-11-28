import z from "zod";

export const UserSchema = z.object({
  name: z
    .string()
    .min(5, { message: "El nombre debe tener al menos 5 caracteres." }),
  username: z.string().min(5, {
    message: "El nombre de usuario debe tener al menos 5 caracteres.",
  }),
  email: z
    .string()
    .email({ message: "Debe proporcionar un correo electrónico válido." }),
  password: z
    .string()
    .min(8, { message: "La contraseña debe tener al menos 8 caracteres." }),
  address: z
    .string()
    .min(5, { message: "La dirección debe tener al menos 5 caracteres." }),
  phone: z.string().regex(/^\d+$/, {
    message: "El número de teléfono solo debe contener numeros.",
  }),
});

export type UserType = z.infer<typeof UserSchema>;
