"use client";
import { FC, useState } from "react";
import { Button } from "@/components/ui/button";
import AddIncome from "./addIncome";
import {
  Dialog,
  DialogClose,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogDescription,
} from "@/components/ui/dialog";

interface Props {
  isIncome: boolean;
}

const BtnAddIncome: FC<Props> = ({ isIncome }) => {
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const setOpen = () => setIsOpen(true);
  const setClose = () => setIsOpen(false);
  const text = isIncome ? "Ingreso" : "Egreso";
  const iconType = isIncome
    ? "hugeicons--money-add-02"
    : "hugeicons--money-remove-02";
  return (
    <>
      <Button onClick={setOpen}>
        <span className={`icon-[${iconType}]`} role="img" aria-hidden="true" />
        <span>Agregar {text}</span>
      </Button>
      <Dialog open={isOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Agregar {text}</DialogTitle>
            <DialogDescription></DialogDescription>
          </DialogHeader>
          <AddIncome
            isIncome={isIncome}
            buttons={
              <DialogFooter>
                <DialogClose asChild>
                  <Button onClick={setClose} variant="outline">
                    Cancelar
                  </Button>
                </DialogClose>
                <Button type="submit">Agregar</Button>
              </DialogFooter>
            }
          />
        </DialogContent>
      </Dialog>
    </>
  );
};

export default BtnAddIncome;
