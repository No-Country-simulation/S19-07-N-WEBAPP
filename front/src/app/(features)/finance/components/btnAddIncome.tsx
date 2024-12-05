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
const BtnAddIncome: FC = () => {
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const setOpen = () => setIsOpen(true);
  const setClose = () => setIsOpen(false);
  return (
    <>
      <Button onClick={setOpen}>
        <span
          className="icon-[hugeicons--money-add-02]"
          role="img"
          aria-hidden="true"
        />
        <span>Agregar ingreso</span>
      </Button>
      <Dialog open={isOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Agregar ingreso</DialogTitle>
            <DialogDescription></DialogDescription>
          </DialogHeader>
          <AddIncome
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
