'use client' // Asegúrate de que este archivo sea un componente de cliente
import type { NextPage } from "next";
import { useState, useId, useEffect } from "react";
import { useForm, type SubmitHandler } from "react-hook-form";
import { useAuth } from "@/hooks/useAuth";
import { toast } from "sonner";
import { useRouter } from "next/router";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { useQueryClient, useMutation } from "@tanstack/react-query";
import { Button } from "@/components/ui/button";
import { EyeOff,UserRound,Lock } from "lucide-react";
import { Input } from "@/components/inputLogin/Input";
import {Loader} from "@/components/ui/Loader"
import Image from "next/image";
import Link from "next/link";


const loginSchema = z.object({
  user: z.string().min(1, { message: "Este campo es requerido" }),
  password: z.string().min(1, { message: "Este campo es requerido" }),
});

const Page: NextPage = () => {
  const id = useId();
  //const router = useRouter();
  const { login } = useAuth();
  const queryClient = useQueryClient();
  const [isMobile, setIsMobile] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [isActive, setIsActive] = useState(false);

  useEffect(() => {
      const handleResize = () => {
          setIsMobile(window.innerWidth < 768);
      };
      handleResize();
      window.addEventListener("resize", handleResize);
      return () => window.removeEventListener("resize", handleResize);
  }, []);

  const loginMutation = useMutation({
      mutationFn: login,
      mutationKey: [login.name],
      onSuccess: () => {
          queryClient.removeQueries();
          //router.push("/redirect");
      },
      onError: () => {
          toast.error("Usuario o contraseña incorrectos");
      },
  });

  const {
      register,
      handleSubmit,
      formState: { errors, isSubmitting },
  } = useForm<z.infer<typeof loginSchema>>({
      resolver: zodResolver(loginSchema),
      defaultValues: {
          user: "",
          password: "",
      },
  });

  const onSubmit: SubmitHandler<z.infer<typeof loginSchema>> = (data) => {

      loginMutation.mutate({
          username: data.user,
          password: data.password,
      });
  };

  const handleRegisterClick = () => {
      setIsActive(true);
  };

  const handleLoginClick = () => {
      setIsActive(false);
  };

  const LoginForm = () => (
    <form className="w-full " onSubmit={handleSubmit(onSubmit)} noValidate>
        {isMobile && (
            <div className="flex items-center w-full mb-8 flex-col gap-10">
                <Image
                    width={180}
                    height={90}
                    alt="Logo Fineasy"
                    src="./Fineasy-Logo.svg"
                    className="h-8"
                />
                <h1 className="text-zinc-800 leading-normal text-xl">
                    ¡El mejor Software de Gestión!
                </h1>
            </div>
        )}
        {!isMobile && (
            <h1 className="text-gray-900 md:text-primary-1000 md:text-4xl mb-10">¡Bienvenido a Fineasy!</h1>
        )}
        <div className="relative w-full mb-2">
            {isMobile ? (
                <Input
                    {...register("user")}
                    label="Nombre de Usuario"
                    type="text"
                    id={id + "-" + "user"}
                    name="user"
                    size="large"
                    errors={errors}
                    placeholder="Ingresa tu nombre de usuario"
                    autoComplete="username"
                    className="w-full!"
                />
            ) : (
                <div className="relative mb-5 flex justify-center ">
                    <label
                        aria-invalid={!!errors["user"]}
                        htmlFor={id + "-" + "user"}
                        className={`relative block border-gray-300 border border-solid rounded-md focus-within:border-[#fcba03] focus-within:ring-1 focus-within:ring-[#fcba03] ${errors["user"] ? "border-red-600 animate-shake" : ""
                            }`}
                    >
                        <input
                            {...register("user")}
                            type="text"
                            id={id + "-" + "user"}
                            name="user"
                            placeholder="Nombre de Usuario"
                            autoComplete="username"
                            className="peer w-full text-base py-3 pl-10 px-3 bg-transparent border-none placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0"
                        />
                        <span className="pointer-events-none absolute start-12 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs">
                            Nombre de Usuario
                        </span>
                        <UserRound className="absolute left-3 top-1/2 transform -translate-y-1/2 text-secondary-900" />
                    </label>
                    {errors["user"] && (
                        <span className="text-red-600 text-sm absolute -top-6">
                            Campo Requerido
                        </span>
                    )}
                </div>
            )}
        </div>
        <div className="relative w-full mb-8">
            {isMobile ? (
                <Input
                    {...register("password")}
                    label="Contraseña"
                    type={showPassword ? "text" : "password"}
                    id={id + "-" + "password"}
                    name="password"
                    size="large"
                    errors={errors}
                    placeholder="Ingresa tu contraseña"
                    autoComplete="current-password"
                    className="w-full!"
                />
            ) : (
                <div className="relative mb-5 flex justify-center ">
                    <label
                        aria-invalid={!!errors["password"]}
                        htmlFor={id + "-" + "password"}
                        className={`relative block border-gray-300 border border-solid rounded-md focus-within:border-[#fcba03] focus-within:ring-1 focus-within:ring-[#fcba03] ${errors["password"] ? "border-red-600 animate-shake" : ""
                            }`}
                    >
                        <input
                            {...register("password")}
                            type={showPassword ? "text" : "password"}
                            id={id + "-" + "password"}
                            name="password"
                            placeholder="Contraseña"
                            autoComplete="current-password"
                            className="peer w-full text-base py-3 pl-10 px-3 bg-transparent border-none placeholder-transparent focus:border-transparent focus:outline-none focus:ring-0"
                        />
                        <span className="pointer-events-none absolute start-12 top-0 -translate-y-1/2 bg-white p-0.5 text-xs text-gray-700 transition-all peer-placeholder-shown:top-1/2 peer-placeholder-shown:text-sm peer-focus:top-0 peer-focus:text-xs">
                            Contraseña
                        </span>
                        <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-secondary-900" />
                        <EyeOff
                            className="absolute right-2 top-1/2 transform -translate-y-1/2 cursor-pointer text-secondary-900"
                            onClick={() => setShowPassword(!showPassword)}
                        />
                    </label>
                    {errors["password"] && (
                        <span className="text-red-600 text-sm absolute -top-6">
                            Campo Requerido
                        </span>
                    )}
                </div>
            )}
            <label className="flex mt-4 text-sm items-center gap-2 block md:hidden">
                <input
                    type="checkbox"
                    checked={showPassword}
                    onChange={() => setShowPassword(!showPassword)}
                />
                Mostrar contraseña
            </label>
        </div>
        <div className={isMobile ? "mt-16" : "-mt-8"}>
            <Button
                type="submit"
                className={isMobile ? "w-full h-12" : "button "}
                disabled={isSubmitting}
            >
                {loginMutation.isPending ? "Cargando ..." : "Ingresar"}
            </Button>
        </div>
        {loginMutation.isPending && <Loader className="fixed right-20 bottom-12" />}
        {isMobile && (
            <div className="text-zinc-800 text-center text-xs mt-10">
                <Link
                    href="/register"
                    className="text-underline underline-offset-2 text-zinc-800"
                >
                    Registrarse
                </Link>
                <div className="mt-3 text-zinc-800">
                    Fineasy - {new Date().getFullYear()} @ Todos los derechos
                    reservados
                </div>
            </div>
        )}
    </form>
);

return (
    <div className="flex justify-center items-center min-h-screen bg-white md:gradient-bg">
        {isMobile ? (
            <div className="flex relative justify-center items-center bg-white py-10 min-h-screen px-4 w-full">
                <span className="absolute z-10 bottom-0 h-[18vh] w-full bg-primary-1000" />
                <div className="bg-white z-20 flex w-full border border-zinc-300 border-solid items-center shadow justify-center rounded-3xl px-8 py-10 max-w-xl">
                    <LoginForm />
                </div>
            </div>
        ) : (
            <div className="flex justify-center items-center h-screen w-full">
                <div className={`container login ${isActive ? "active" : ""}`} id="container">
                    <div className="form-container sign-up">
                        <form>
                            <h4 className="text-3xl font-semibold mb-3">Versión Antigua de Fineasy</h4>
                            <p className="text-sm mb-5">El mejor software de gestión desde entonces.</p>
                            <Input
                                {...register("newUser")}
                                label="Nombre de Usuario"
                                type="text"
                                id={id + "-" + "newUser"}
                                name="newUser"
                                placeholder="Ingresa tu nombre de usuario"
                                autoComplete="username"
                                className="w-full mb-4"
                            />
                            <Input
                                {...register("newPassword")}
                                label="Contraseña"
                                type="password"
                                id={id + "-" + "newPassword"}
                                name="newPassword"
                                placeholder="Ingresa tu contraseña"
                                autoComplete="new-password"
                                className="w-full mb-4"
                            />
                            <Button type="submit" className="w-full h-12">
                                Registrarse
                            </Button>
                            <div className="mt-3 text-zinc-800">
                                Fineasy - {new Date().getFullYear()} @ Todos los derechos reservados
                            </div>
                        </form>
                    </div>
                    <div className="form-container sign-in ">
                        <LoginForm />
                    </div>
                    {/* Botones de transición */}
                    <div className="toggle-container">
                        <div className="toggle">
                            <div className="toggle-panel toggle-left">
                                <h1>Bienvenido a Fineasy</h1>
                                <p>Si ya tienes Cuenta puedes ir a esta seccion</p>
                                <Button
                                    className="loginbtn shine-button"
                                    id="login"
                                    onClick={handleLoginClick}
                                >
                                    <span>Login</span>
                                </Button>
                                <div className="p-8 text-white/80 text-sm footer flex justify-center items-end border-transparent" style={{ position: "absolute", bottom: 0, width: "100%" }}>
                                    <span>
                                        Fineasy - {new Date().getFullYear()} @ Todos los derechos reservados
                                    </span>
                                </div>
                            </div>
                            <div className="toggle-panel toggle-right">
                                <h1>Fineasy</h1>
                                <p>El mejor software de gestión.</p>
                                <Button className="loginbtn " id="register" onClick={handleRegisterClick}>
                                   Registro de Usuarios
                                </Button>
                                <div className="p-8 text-white/80 text-sm footer flex justify-center items-end border-transparent" style={{ position: "absolute", bottom: 0, width: "100%" }}>
                                    <span>
                                        Fineasy - {new Date().getFullYear()} @ Todos los derechos reservados
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )}
    </div>
);
};

export default Page;