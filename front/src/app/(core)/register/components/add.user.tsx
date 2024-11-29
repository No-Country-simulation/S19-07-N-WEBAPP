"use client";
import { Button } from "@/components/ui/button";
import { registerUser } from "@/lib/actions/formAction";
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
import { ComponentType, FC, useState } from "react";
import { useForm } from "react-hook-form";
import { toast } from "sonner";
import { AuthFields, BasicField } from "../utils/formFields";
import useNavigation from "@/hooks/useNavigation";

const AddUser: ComponentType = () => {
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const { goLogin } = useNavigation();
  const userForm = useForm<UserType>({
    resolver: zodResolver(UserSchema),
    defaultValues: {
      email: "",
      name: "",
      password: "",
      confirmPassword: "",
    },
  });
  const {
    formState: { errors },
  } = userForm;
  const onSubmit = (data: UserType) => {
    setIsLoading(true);
    toast.promise(registerUser(data), {
      loading: "Cargando...",
      success: (res: any) => {
        goLogin();
        return res;
      },
      error: (err) => err,
      finally: () => {
        setIsLoading(false);
      },
    });
  };

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
                  <Input
                    disabled={isLoading}
                    className={errors[ff.name] && "focus-visible:ring-rose-500"}
                    placeholder={ff.placeholder}
                    {...field}
                  />
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
                      disabled={isLoading}
                      className={
                        errors[af.name] && "focus-visible:ring-rose-500"
                      }
                      placeholder={af.placeholder}
                      {...field}
                    />
                  ) : (
                    <Input
                      disabled={isLoading}
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
        <SubmitButton isLoading={isLoading} field="Registrar" />
      </form>
    </Form>
  );
};

const SubmitButton: FC<{ isLoading: boolean; field: string }> = ({
  isLoading,
  field,
}) => {
  return (
    <Button
      disabled={isLoading}
      className="mt-5"
      type={isLoading ? "button" : "submit"}
    >
      {isLoading ? (
        <span
          className="icon-[line-md--loading-twotone-loop]"
          role="img"
          aria-hidden="true"
        />
      ) : (
        field
      )}
    </Button>
  );
};

export default AddUser;
