"use client"

import { ComponentType, useState } from 'react';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import FinanceLayout from './financeLayout';

interface Transaction {
  id: string;
  titulo: string;
  fecha: string;
  monto: number;
}

const Finance: ComponentType = () => {
  const [activeTab, setActiveTab] = useState<'ingresos' | 'egresos'>('ingresos');

  const transactions: Transaction[] = [
    { id: '000001', titulo: 'Carne Res', fecha: '01-05-2023', monto: 50 },
    { id: '000002', titulo: 'Carne Pollo', fecha: '01-05-2023', monto: 50 },
    { id: '000003', titulo: 'Pescado', fecha: '02-05-2023', monto: 30 },
    { id: '000004', titulo: 'Verduras', fecha: '03-05-2023', monto: 20 },
    { id: '000005', titulo: 'Frutas', fecha: '04-05-2023', monto: 25 },
    { id: '000006', titulo: 'Lácteos', fecha: '05-05-2023', monto: 15 },
    { id: '000007', titulo: 'Pan', fecha: '06-05-2023', monto: 10 },
    { id: '000008', titulo: 'Arroz', fecha: '07-05-2023', monto: 40 },
    { id: '000009', titulo: 'Pasta', fecha: '08-05-2023', monto: 35 },
  ];

  const egresos: Transaction[] = [
    { id: '000001', titulo: 'Pago Luz', fecha: '01-05-2023', monto: 100 },
    { id: '000002', titulo: 'Pago Agua', fecha: '01-05-2023', monto: 50 },
    // ... más datos de egresos
  ];

  return (
    <FinanceLayout activeTab={activeTab} onTabChange={setActiveTab}>
      {/* Tabla */}
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Título</TableHead>
            <TableHead>Fecha</TableHead>
            <TableHead className="text-right">Monto</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {(activeTab === 'ingresos' ? transactions : egresos).map((transaction) => (
            <TableRow key={transaction.id}>
              <TableCell>{transaction.id}</TableCell>
              <TableCell>{transaction.titulo}</TableCell>
              <TableCell>{transaction.fecha}</TableCell>
              <TableCell className="text-right">
                {activeTab === 'ingresos' ? `$${transaction.monto}` : `-$${transaction.monto}`}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      {/* Total */}
      <div className="flex justify-end mt-4">
        <p className="font-semibold">
          Monto total: <span className={activeTab === 'ingresos' ? 'text-green-600' : 'text-red-600'}>
            {activeTab === 'ingresos' ? `$${transactions.reduce((acc, curr) => acc + curr.monto, 0)}` : `-$${egresos.reduce((acc, curr) => acc + curr.monto, 0)}`}
          </span>
        </p>
      </div>
    </FinanceLayout>
  );
};

export default Finance;