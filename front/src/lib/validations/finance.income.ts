import { z } from "zod";

export const IncomeSchema = z.object({
  title: z
    .string()
    .min(5, "El título debe tener al menos 5 caracteres.")
    .max(100, "El título no debe exceder los 100 caracteres."),
  description: z
    .string()
    .max(255, "La descripción no debe exceder los 255 caracteres.")
    .optional(),
  amount: z.string().regex(/^\d+$/, "El monto debe contener solo números."),
});

export type IncomeType = z.infer<typeof IncomeSchema>;
