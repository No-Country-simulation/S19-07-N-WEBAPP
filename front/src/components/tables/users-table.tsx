"use client"

import { Edit, Trash2 } from 'lucide-react';
import { Button } from '@/components/ui/button';
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from "@/components/ui/alert-dialog";
import { useState } from 'react';

interface User {
  id: number;
  name: string;
  position: string;
  branch: string;
  area: string;
  startDate: string;
}

interface UserTableProps {
  users: User[] | undefined; // Actualizamos para permitir undefined
  onEdit: (user: User) => void;
  onDelete: (id: number) => void;
}

const UserTable: React.FC<UserTableProps> = ({ users = [], onEdit, onDelete }) => { // Valor por defecto vacío
  const [userToDelete, setUserToDelete] = useState<User | null>(null);

  if (!users || users.length === 0) {
    return (
      <div className="p-8 text-center text-gray-500">
        No hay usuarios para mostrar
      </div>
    );
  }

  return (
    <>
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
                  <div className="w-8 h-8 rounded-full bg-gray-200" />
                  {user.name}
                </td>
                <td className="py-4 px-6">{user.position}</td>
                <td className="py-4 px-6">{user.branch}</td>
                <td className="py-4 px-6">{user.area}</td>
                <td className="py-4 px-6">{user.startDate}</td>
                <td className="py-4 px-6">
                  <div className="flex gap-2">
                    <Button
                      variant="ghost"
                      size="sm"
                      onClick={() => onEdit(user)}
                    >
                      <Edit className="h-4 w-4" />
                    </Button>
                    <Button
                      variant="ghost"
                      size="sm"
                      onClick={() => setUserToDelete(user)}
                    >
                      <Trash2 className="h-4 w-4 text-red-500" />
                    </Button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <AlertDialog open={!!userToDelete} onOpenChange={() => setUserToDelete(null)}>
        <AlertDialogContent>
          <AlertDialogHeader>
            <AlertDialogTitle>¿Estás seguro?</AlertDialogTitle>
            <AlertDialogDescription>
              Esta acción no se puede deshacer. Se eliminará permanentemente el usuario
              {userToDelete?.name}.
            </AlertDialogDescription>
          </AlertDialogHeader>
          <AlertDialogFooter>
            <AlertDialogCancel>Cancelar</AlertDialogCancel>
            <AlertDialogAction
              onClick={() => {
                if (userToDelete) {
                  onDelete(userToDelete.id);
                  setUserToDelete(null);
                }
              }}
              className="bg-red-500 hover:bg-red-600"
            >
              Eliminar
            </AlertDialogAction>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog>
    </>
  );
};

export default UserTable;