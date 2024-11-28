"use client";
import { ComponentType } from "react";
import { Button } from "@/components/ui/button";
import Image from "next/image";
import useNavigation from "@/hooks/useNavigation";

const NotFound: ComponentType = () => {
  const { goBack, goHome } = useNavigation();
  return (
    <div className="h-dvh flex flex-col justify-center items-center gap-5">
      <Image
        priority
        src="/404.svg"
        className=" w-80 md:w-96"
        alt="404"
        width={800}
        height={800}
      />
      <div className="flex flex-col text-center gap-2">
        <span className="text-3xl font-bold">¿Estas perdido?</span>
        <small className="text-zinc-500">
          Parece que esta página invirtió en criptomonedas y se evaporó.
          <br />
          ¡Sal de aquí antes de que tus finanzas también desaparezcan!
        </small>
        <div className="grid grid-cols-2 gap-5">
          <Button onClick={goBack}>Volver</Button>
          <Button onClick={goHome} variant="outline">
            Ir al inicio
          </Button>
        </div>
      </div>
    </div>
  );
};

export default NotFound;
