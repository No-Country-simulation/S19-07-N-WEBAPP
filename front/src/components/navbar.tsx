import { ComponentType } from "react";
import Logo from "./icons/logo";
import { BellIcon} from 'lucide-react';
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

const HeaderNavbar: ComponentType = () => {
  const name = "Carlos Bacca";
  const role = "Administrador";

  return (
    <header className="flex flex-col">
      <div className="flex items-center justify-between p-5 h-12 bg-[#2c3e50] text-zinc-50">
        <Logo theme="light" size="lg" />
        <div className="flex items-center gap-4">
          <div className="relative w-full max-w-sm">
            <Input
              type="search"
              placeholder="Buscar..."
              className="w-full pl-8"
            />
          </div>
          <Button variant="ghost" size="icon" className="text-muted-foreground">
            <BellIcon className="h-5 w-5" />
          </Button>
          <span className="mr-2">{name}</span>
          <small>{role}</small>
        </div>
      </div>
    </header>
  );
};

export default HeaderNavbar;
