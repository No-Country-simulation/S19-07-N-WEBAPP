import { ComponentType } from "react";
import Logo from "./icons/logo";
import { BellIcon, SearchIcon } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

const HeaderNavbar: ComponentType = () => {
  const name = "Carlos Bacca";
  const role = "Administrador";

  return (
    <header className="flex z-10 sticky top-0 flex-col">
      <div className="flex items-center justify-between p-5 h-20 bg-zinc-950 text-zinc-50">
        {" "}
        {/* Color actualizado */}
        <Logo theme="light" size="lg" />
        <div className="flex items-center gap-4">
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
