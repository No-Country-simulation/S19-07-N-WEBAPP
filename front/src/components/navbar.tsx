import { ComponentType } from "react";
import Logo from "./icons/logo";
import { BellIcon, SearchIcon } from 'lucide-react';
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

const HeaderNavbar: ComponentType = () => {
  const name = "Carlos Bacca";
  const role = "Administrador";
  
  return (
    <header className="flex flex-col">
      <div className="flex items-center justify-between p-5 h-20 bg-[#2c3e50] text-zinc-50"> {/* Color actualizado */}
        <Logo theme="light" size="lg" />
        <div className="flex items-center gap-4">
          <div className="relative w-full max-w-sm">
            <SearchIcon className="absolute left-2.5 top-2.5 h-4 w-4 text-muted-foreground" />
            <Input
              type="search"
              placeholder="Buscar..."
              className="w-full pl-8"
            />
          </div>
          <Button variant="ghost" size="icon" className="text-muted-foreground">
            <BellIcon className="h-5 w-5" />
          </Button>
          <Button variant="ghost" size="icon" className="rounded-full">
            <img
              alt="Avatar"
              className="rounded-full"
              height="32"
              src="/placeholder.svg"
              style={{
                aspectRatio: "32/32",
                objectFit: "cover",
              }}
              width="32"
            />
          </Button>
          <span className="mr-2">{name}</span>
          <small>{role}</small>
        </div>
      </div>
    </header>
  );
};

export default HeaderNavbar;