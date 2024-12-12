"use client"

import { useState } from 'react';
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { z } from 'zod';

// Definir el esquema de validación con Zod
const transactionSchema = z.object({
  transactionType: z.enum(['Ingreso', 'Egreso']),
  method: z.enum(['Efectivo', 'Tarjeta']),
  amount: z.string().min(1, "El monto es requerido"),
  title: z.string().min(1, "El título es requerido"),
  branch: z.enum(['Norte', 'Sur']),
  box: z.enum(['Caja 1', 'Caja 2']),
  cashier: z.enum(['José', 'María']),
  date: z.string().min(1, "La fecha es requerida"),
});
interface TransactionFormProps {
  onClose: () => void; 
}

const TransactionForm = ({ onClose }: TransactionFormProps) => {
  const [transactionType, setTransactionType] = useState('Ingreso');
  const [method, setMethod] = useState('Efectivo');
  const [amount, setAmount] = useState('');
  const [title, setTitle] = useState('');
  const [branch, setBranch] = useState('Norte');
  const [box, setBox] = useState('Caja 1');
  const [cashier, setCashier] = useState('José');
  const [date, setDate] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    const result = transactionSchema.safeParse({
      transactionType,
      method,
      amount,
      title,
      branch,
      box,
      cashier,
      date,
    });


    // Si la validación es exitosa, puedes manejar el envío del formulario
    console.log(result.data);
    onClose(); // Cierra el modal después de enviar
  };

  return (
    <form className="flex flex-col p-6 space-y-4" onSubmit={handleSubmit}>
      <h2 className="text-xl font-bold mb-4">Agregando una nueva transacción</h2>
      
      <Label>
        Tipo
        <select value={transactionType} onChange={(e) => setTransactionType(e.target.value)} className="border rounded-md p-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option>Ingreso</option>
          <option>Egreso</option>
        </select>
      </Label>

      <Label>
        Método
        <select value={method} onChange={(e) => setMethod(e.target.value)} className="border rounded-md p-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option>Efectivo</option>
          <option>Tarjeta</option>
        </select>
      </Label>

      <Label>
        Monto
        <Input type="text" value={amount} onChange={(e) => setAmount(e.target.value)} placeholder="$10,500.00" className="mt-1" />
      </Label>

      <Label>
        Título
        <Input type="text" value={title} onChange={(e) => setTitle(e.target.value)} placeholder="Nombre de la transacción" className="mt-1" />
      </Label>

      <Label>
        Sucursal
        <select value={branch} onChange={(e) => setBranch(e.target.value)} className="border rounded-md p-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option>Norte</option>
          <option>Sur</option>
        </select>
      </Label>

      <Label>
        Caja
        <select value={box} onChange={(e) => setBox(e.target.value)} className="border rounded-md p-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option>Caja 1</option>
          <option>Caja 2</option>
        </select>
      </Label>

      <Label>
        Cajero
        <select value={cashier} onChange={(e) => setCashier(e.target.value)} className="border rounded-md p-2 mt-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option>José</option>
          <option>María</option>
        </select>
      </Label>

      <Label>
        Fecha
        <Input type="date" value={date} onChange={(e) => setDate(e.target.value)} className="mt-1" />

      </Label>

      <div className="flex justify-end mt-4">
        <Button variant="default" onClick={onClose} className="mr-2">Cancelar</Button>
        <Button variant="default" className="bg-blue-500 text-white" type="submit">Guardar</Button>
      </div>
    </form>
  );
};

export default TransactionForm;