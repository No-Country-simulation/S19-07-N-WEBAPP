"use client";
import { ComponentType, useState } from "react";
import BtnAddIncome from "./components/btnAddIncome";
import { Switch } from "@/components/ui/switch";

const Finance: ComponentType = () => {
  const [isIncome, setIsIncome] = useState(true);
  const toggleIsIncome = () => setIsIncome(!isIncome);
  return (
    
      <div className="flex items-center justify-between p-5">
        <div className="flex items-center gap-2">
          <span>Ingresos</span>
          <Switch checked={!isIncome} onCheckedChange={toggleIsIncome} />
          <span>Egresos</span>
        </div>
        <BtnAddIncome isIncome={isIncome} />
      </div>
    
  );
};

export default Finance;
