import { ComponentType } from "react";
import Logo from "./icons/logo";

const Navbar: ComponentType = () => {
  const name = "carlos bacca";
  const role = "Administrador";
  return (
    <header className="p-5 flex items-center h-20 justify-between bg-zinc-900 text-zinc-50">
      <Logo theme="light" size="lg" />
      <div className="flex-col flex text-end">
        <span>{name}</span>
        <small>{role}</small>
      </div>
    </header>
  );
};

export default Navbar;
