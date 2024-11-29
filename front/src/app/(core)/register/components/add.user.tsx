"use client";
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
import { PasswordInput } from "@/components/ui/password-input";
import { UserSchema, UserType } from "@/lib/validations/user.register";
import { zodResolver } from "@hookform/resolvers/zod";
import { ComponentType } from "react";
import { useForm } from "react-hook-form";
import { toast } from "sonner";

const AddUser: ComponentType = () => {
  const userForm = useForm<UserType>({
    resolver: zodResolver(UserSchema),
    defaultValues: {
      address: "",
      email: "",
      name: "",
      password: "",
      confirmPassword: "",
      phone: "",
      username: "",
    },
  });
  const {
    formState: { errors },
  } = userForm;
  const onSubmit = (data: UserType) => {
    toast.promise(
      new Promise((resolve, reject) => {
        setTimeout(() => {
          reject();
        }, 3000);
      }),
      {
        loading: "Cargando...",
        success: "Usuario registrado con éxito",
        error: "Conexion con API proximamente",
      }
    );
    console.log(data);
  };
  interface FormFieldType {
    name: keyof UserType;
    text: string;
    placeholder: string;
  }
  const BasicField: FormFieldType[] = [
    {
      name: "name",
      text: "Nombre",
      placeholder: "Jhon Doeh",
    },
    {
      name: "username",
      text: "Usuario",
      placeholder: "doeh69",
    },
    {
      name: "address",
      text: "Dirección",
      placeholder: "Calle 123",
    },
    {
      name: "phone",
      text: "Teléfono",
      placeholder: "1234567890",
    },
  ];
  const AuthFields: FormFieldType[] = [
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
  return (
    <Form {...userForm}>
      <form
        onSubmit={userForm.handleSubmit(onSubmit)}
        className="flex flex-col gap-5 text-xs rounded"
        autoComplete="off"
      >
        <small className="text-xs leading-3 text-zinc-500">Basic Info</small>
        {BasicField.map((ff, i) => (
          <FormField
            key={i}
            control={userForm.control}
            name={ff.name}
            render={({ field }) => (
              <FormItem className="space-y-1">
                <div className="flex items-center justify-between">
                  <FormLabel className="text-zinc-950">{ff.text}</FormLabel>
                  <FormMessage className="text-[10px] leading-3" />
                </div>
                <FormControl>
                  {ff.name === "password" ? (
                    <PasswordInput
                      className={
                        errors[ff.name] && "focus-visible:ring-rose-500"
                      }
                      placeholder={ff.placeholder}
                      {...field}
                    />
                  ) : (
                    <Input
                      className={
                        errors[ff.name] && "focus-visible:ring-rose-500"
                      }
                      placeholder={ff.placeholder}
                      {...field}
                    />
                  )}
                </FormControl>
              </FormItem>
            )}
          />
        ))}
        <small className="text-xs leading-3 text-zinc-500">Auth info</small>
        {AuthFields.map((af, i) => (
          <FormField
            key={i}
            control={userForm.control}
            name={af.name}
            render={({ field }) => (
              <FormItem className="space-y-1">
                <div className="flex items-center justify-between">
                  <FormLabel className="text-zinc-950">{af.text}</FormLabel>
                  <FormMessage className="text-[10px] leading-3" />
                </div>
                <FormControl>
                  {af.name === "password" || af.name === "confirmPassword" ? (
                    <PasswordInput
                      className={
                        errors[af.name] && "focus-visible:ring-rose-500"
                      }
                      placeholder={af.placeholder}
                      {...field}
                    />
                  ) : (
                    <Input
                      className={
                        errors[af.name] && "focus-visible:ring-rose-500"
                      }
                      placeholder={af.placeholder}
                      {...field}
                    />
                  )}
                </FormControl>
              </FormItem>
            )}
          />
        ))}
        <Button className="mt-5" type="submit">
          Registrar
        </Button>
      </form>
    </Form>
  );
};

export default AddUser;
