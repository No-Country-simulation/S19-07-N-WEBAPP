"use client"

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";

const placeSchema = z.object({
  name: z.string().min(2, "El nombre es requerido"),
  address: z.string().min(5, "La dirección es requerida"),
  manager: z.object({
    name: z.string().min(2, "El nombre del gerente es requerido"),
    role: z.string().min(2, "El rol es requerido"),
  }),
});

type PlaceFormData = z.infer<typeof placeSchema>;

interface PlaceFormProps {
  initialData?: PlaceFormData;
  onSubmit: (data: PlaceFormData) => void;
  onCancel: () => void;
}

const PlaceForm = ({ initialData, onSubmit, onCancel }: PlaceFormProps) => {
  const form = useForm<PlaceFormData>({
    resolver: zodResolver(placeSchema),
    defaultValues: initialData || {
      name: "",
      address: "",
      manager: {
        name: "",
        role: "",
      },
    },
  });

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
        <FormField
          control={form.control}
          name="name"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Nombre de la Sucursal</FormLabel>
              <FormControl>
                <Input {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="address"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Dirección</FormLabel>
              <FormControl>
                <Input {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="manager.name"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Nombre del Gerente</FormLabel>
              <FormControl>
                <Input {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <FormField
          control={form.control}
          name="manager.role"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Rol del Gerente</FormLabel>
              <FormControl>
                <Input {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <div className="flex justify-end gap-4">
          <Button type="button" variant="outline" onClick={onCancel}>
            Cancelar
          </Button>
          <Button type="submit">
            {initialData ? "Actualizar" : "Crear"} Sucursal
          </Button>
        </div>
      </form>
    </Form>
  );
};

export default PlaceForm;