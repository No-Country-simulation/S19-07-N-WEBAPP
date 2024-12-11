"use client"

import { ComponentType, ReactNode, useState } from 'react';
import { Button } from "@/components/ui/button";
import Modal from "@/components/ui/Modal";
import TransactionForm from "@/components/transactionForm"; 
import { Calendar as CalendarComponent } from "@/components/ui/calendar";

interface FinanceLayoutProps {
  children: ReactNode;
  activeTab: 'ingresos' | 'egresos';
  onTabChange: (tab: 'ingresos' | 'egresos') => void;
}

const FinanceLayout: ComponentType<FinanceLayoutProps> = ({ children, activeTab, onTabChange }) => {
  const [isCalendarOpen, setIsCalendarOpen] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const toggleCalendar = () => {
    setIsCalendarOpen(!isCalendarOpen);
  };

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <div className="p-1">
      {/* Contenedor principal para los botones */}
      <div className="flex justify-between items-center mb-6">
        {/* Switch de Ingresos/Egresos */}
        <div className="flex gap-2">
          <Button
            variant={activeTab === 'ingresos' ? 'default' : 'outline'}
            onClick={() => onTabChange('ingresos')}
            className="bg-green-500 text-white" // Botón Ingresos verde
          >
            Ingresos
          </Button>
          <Button
            variant={activeTab === 'egresos' ? 'default' : 'outline'}
            onClick={() => onTabChange('egresos')}
            className="bg-purple-500 text-white" // Botón Egresos morado
          >
            Egresos
          </Button>
        </div>

        {/* Botones adicionales alineados a la derecha */}
        <div className="flex items-center gap-4">
          <Button variant="default" onClick={toggleCalendar}>
            {isCalendarOpen ? "Cerrar Calendario" : "Agregar Tag"}
          </Button>
          {isCalendarOpen && <CalendarComponent className="w-64" />}
          <Button variant="outline">Exportar</Button>
          <Button variant="default" className="bg-blue-500 text-white" onClick={openModal}>
            Agregar
          </Button> {/* Botón Agregar azul */}
        </div>
      </div>

      {/* Modal para agregar transacción */}
      <Modal isOpen={isModalOpen} onClose={closeModal}>
        <TransactionForm onClose={closeModal} />
      </Modal>

      {children}
    </div>
  );
};

export default FinanceLayout;