"use client";
import { useState } from "react"; // Importar useState
import { Home, Building2, Users, PieChart, LogOut } from "lucide-react";
import { cn } from "@/lib/utils";
import Link from "next/link";
import { Button } from "./ui/button";
export type SidebarProps = React.HTMLAttributes<HTMLDivElement>;

export function Sidebar({ className }: SidebarProps) {
  const [isCollapsed, setIsCollapsed] = useState(true);

  const toggleSidebar = () => {
    setIsCollapsed(!isCollapsed);
  };

  return (
    <div
      className={cn(
        "pb-12 sticky top-20 h-[calc(100dvh-80px)] bg-zinc-950",
        className,
        {
          "w-16": isCollapsed,
          "w-64": !isCollapsed,
        }
      )}
    >
      <div className="space-y-4 py-4">
        <div className="px-4 py-2">
          {/*!!TODO MakeBetterThisButton*/}
          <Button
            onClick={toggleSidebar}
            className="text-white rounded-full p-2 transition-transform transform hover:scale-110"
          >
            {isCollapsed ? (
              <span
                className="icon-[stash--burger-arrow-right-duotone]"
                role="img"
                aria-hidden="true"
              />
            ) : (
              <span
                className="icon-[stash--burger-arrow-left-duotone]"
                role="img"
                aria-hidden="true"
              />
            )}
          </Button>
        </div>
        <nav className="space-y-1 px-2">
          <NavItem icon={Home} text={isCollapsed ? "" : "Tablero"} to="/dash" />
          <NavItem
            icon={Building2}
            text={isCollapsed ? "" : "Sucursales"}
            to="/places"
          />
          <NavItem
            icon={Users}
            text={isCollapsed ? "" : "Personas"}
            to="/users"
          />
          <NavItem
            icon={PieChart}
            text={isCollapsed ? "" : "Finanzas"}
            to="/finance"
          />
        </nav>
      </div>
      <div className="absolute bottom-4 px-2 w-full">
        <NavItem
          icon={LogOut}
          text={isCollapsed ? "" : "Cerrar Sesión"}
          to="/logout"
          className="hover:text-red-500" // Cambiar color a rojo en hover
        />
      </div>
    </div>
  );
}

interface NavItemProps {
  icon?: React.ElementType;
  text: string;
  to: string;
  active?: boolean;
  className?: string; // Añadido para permitir clases adicionales
}

function NavItem({ icon: Icon, text, to, active, className }: NavItemProps) {
  return (
    <Link
      href={to}
      className={cn(
        "flex items-center gap-3 rounded-lg px-3 py-2 text-sm transition-all hover:text-white",
        active ? "bg-white/10 text-white" : "text-white/70",
        className // Aplicar clases adicionales
      )}
    >
      {Icon && <Icon className="h-4 w-4" />}
      {text}
    </Link>
  );
}
