"use client"

import React from 'react';
import { Eye } from 'lucide-react';

interface User {
  id: number;
  name: string;
  position: string;
  branch: string;
  area: string;
  startDate: string;
}

interface UserTableProps {
  users: User[];
}

const UserTable: React.FC<UserTableProps> = ({ users }) => {
  return (
    <div className="overflow-x-auto">
      <table className="min-w-full">
        <thead>
          <tr className="border-b">
            <th className="py-4 px-6 text-left text-sm font-medium text-gray-500">Nombre</th>
            <th className="py-4 px-6 text-left text-sm font-medium text-gray-500">Posición</th>
            <th className="py-4 px-6 text-left text-sm font-medium text-gray-500">Sucursal</th>
            <th className="py-4 px-6 text-left text-sm font-medium text-gray-500">Área</th>
            <th className="py-4 px-6 text-left text-sm font-medium text-gray-500">Fecha de Inicio</th>
            <th className="py-4 px-6 text-left text-sm font-medium text-gray-500">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id} className="border-b hover:bg-gray-50">
              <td className="py-4 px-6 flex items-center gap-3">
                <div className="w-8 h-8 rounded-full bg-gray-200" /> {/* Placeholder for avatar */}
                {user.name}
              </td>
              <td className="py-4 px-6">{user.position}</td>
              <td className="py-4 px-6">{user.branch}</td>
              <td className="py-4 px-6">{user.area}</td>
              <td className="py-4 px-6">{user.startDate}</td>
              <td className="py-4 px-6">
                <button className="text-gray-500 hover:text-gray-700">
                  <Eye size={20} />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default UserTable;