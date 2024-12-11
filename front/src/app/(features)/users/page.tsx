"use client"

import { ComponentType } from 'react';
import UserTable from "@/components/tables/users-table";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

const Users: ComponentType = () => {
  // Datos de ejemplo
  const users = [
    { 
      id: 1, 
      name: 'Empleado 1', 
      position: 'Cajero', 
      branch: 'Sucursal Norte', 
      area: 'Frutería', 
      startDate: '2022-12-16',
      avatar: '/path-to-avatar-1.jpg'
    },
    { 
      id: 2, 
      name: 'Empleado 2', 
      position: 'Supervisor', 
      branch: 'Sucursal Norte', 
      area: 'Frutería', 
      startDate: '2022-09-06',
      avatar: '/path-to-avatar-2.jpg'
    },
    { 
      id: 3, 
      name: 'Empleado 3', 
      position: 'Cajero', 
      branch: 'Sucursal Sur', 
      area: 'Administración', 
      startDate: '2022-08-12',
      avatar: '/path-to-avatar-3.jpg'
    },
  ];

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto p-6">
        {/* Header Section */}
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold text-gray-900">Personal</h1>
        </div>
                {/* Stats and Filters Row */}
                <div className="flex items-center justify-between mb-6">
          <div className="flex items-center gap-2">
          <span className="text-purple-500">👥</span>
            <span className="text-xl font-semibold">100</span>
            <span className="text-gray-500">Empleados Activos</span>
          </div>

          <div className="flex items-center gap-4">
            <div className="flex gap-4">
              <Input 
                type="text" 
                placeholder="Buscar personal..." 
                className="w-[300px] bg-white"
              />
              <select className="border rounded-md px-3 py-2 bg-white">
                <option>Todas las...</option>
              </select>
              <select className="border rounded-md px-3 py-2 bg-white">
                <option>Todas las...</option>
              </select>
            </div>
            <Button 
              className="bg-purple-500 hover:bg-purple-600 text-white px-4 py-2 rounded-md flex items-center gap-2"
            >
              <span>+</span>
              <span>Agregar Personal</span>
            </Button>
          </div>
        </div>

        {/* Table Container */}
        <div className="bg-white rounded-lg shadow">
          <UserTable users={users} />
        </div>
      </div>
    </div>
  );
};

export default Users;