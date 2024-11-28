"use client";
import { ComponentType } from "react";
import { UserSchema, UserType } from "@/lib/validations/add.user.validation";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
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
import { toast } from "sonner";

const AddUser: ComponentType = () => {
  const userForm = useForm<UserType>({
    resolver: zodResolver(UserSchema),
    defaultValues: {
      name: "",
      username: "",
      email: "",
      password: "",
      address: "",
      phone: "",
    },
  });
  const onSubmit = (data: UserType) => {
    console.log(data);
    toast("esperando conexion a api");
  };
  interface FieldProps {
    name: keyof UserType;
    text: string;
    placeholder: string;
  }
  const FormFields: FieldProps[] = [
    {
      name: "name",
      text: "Name",
      placeholder: "Jhon Doe",
    },
    {
      name: "username",
      text: "Username",
      placeholder: "Doej69",
    },
    {
      name: "email",
      text: "Email",
      placeholder: "jdoeh@gmidos.com",
    },
    {
      name: "password",
      text: "Password",
      placeholder: "********",
    },
    {
      name: "address",
      text: "Address",
      placeholder: "Calle 123",
    },
    {
      name: "phone",
      text: "Phone",
      placeholder: "1234567890",
    },
  ];

  return (
    <Form {...userForm}>
      <form
        className="flex flex-col gap-5 p-5"
        onSubmit={userForm.handleSubmit(onSubmit)}
      >
        {FormFields.map((ff) => (
          <FormField
            key={ff.name}
            control={userForm.control}
            name={ff.name}
            render={({ field }) => (
              <FormItem>
                <FormLabel>{ff.text}</FormLabel>
                <FormControl>
                  <Input placeholder={ff.placeholder} {...field} />
                </FormControl>
                  <FormMessage className="text-xs" />
              </FormItem>
            )}
          />
        ))}

        <Button type="submit">Register</Button>
      </form>
    </Form>
  );
};

export default AddUser;
