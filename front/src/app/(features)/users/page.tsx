"use client"

import { useState } from 'react';
import { useUsers, useCreateUser, useUpdateUser, useDeleteUser } from '@/services/users.service';
import UserTable from "@/components/tables/users-table";
import UserForm from "@/components/users/UserForm";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { toast } from "sonner";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Plus } from 'lucide-react';

interface User {
  id: number;
  name: string;
  position: string;
  branch: string;
  area: string;
  startDate: string;
}

const Users = () => {
  // Estados
  const [isOpen, setIsOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedUser, setSelectedUser] = useState<User | null>(null);
  const [branchFilter, setBranchFilter] = useState("all");
  const [areaFilter, setAreaFilter] = useState("all");

  // Queries y Mutations
  const { data: users = [], isLoading } = useUsers();
  const createUserMutation = useCreateUser();
  const updateUserMutation = useUpdateUser();
  const deleteUserMutation = useDeleteUser();

  // Memoized values
  const branches = Array.isArray(users)
    ? Array.from(new Set(users.map(user => user.branch)))
    : [];
  const areas = Array.isArray(users)
    ? Array.from(new Set(users.map(user => user.area)))
    : [];

  const filteredUsers = Array.isArray(users)
    ? users.filter(user => {
        const matchesSearch = user.name.toLowerCase().includes(searchTerm.toLowerCase());
        const matchesBranch = branchFilter === "all" || user.branch === branchFilter;
        const matchesArea = areaFilter === "all" || user.area === areaFilter;
        return matchesSearch && matchesBranch && matchesArea;
      })
    : [];

  // Handlers
  const handleCreateUser = async (data: Omit<User, 'id'>) => {
    try {
      await createUserMutation.mutateAsync(data);
      toast.success("Usuario creado correctamente");
      setIsOpen(false);
    } catch (error) {
      toast.error("No se pudo crear el usuario");
    }
  };

  const handleEditUser = async (data: User) => {
    if (!selectedUser) return;
    try {
      await updateUserMutation.mutateAsync({ ...data, id: selectedUser.id });
      toast.success("Usuario actualizado correctamente");
      setIsOpen(false);
      setSelectedUser(null);
    } catch (error) {
      toast.error("No se pudo actualizar el usuario");
    }
  };

  const handleDeleteUser = async (id: number) => {
    try {
      await deleteUserMutation.mutateAsync(id);
      toast.success("Usuario eliminado correctamente");
    } catch (error) {
      toast.error("No se pudo eliminar el usuario");
    }
  };

  const handleModalClose = () => {
    setIsOpen(false);
    setSelectedUser(null);
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto p-6 space-y-6">
        {/* Header */}
        <div className="flex justify-between items-center">
          <h1 className="text-2xl font-bold text-gray-900">Personal</h1>
        </div>

        {/* Stats and Filters */}
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2 bg-white px-4 py-2 rounded-lg shadow-sm">
            <span className="text-purple-500">usuarios</span>
            <span className="text-xl font-semibold">
              {isLoading ? 'Cargando...' : filteredUsers?.length || 0}
            </span>
            <span className="text-gray-500">Empleados Activos</span>
          </div>

          <div className="flex items-center gap-4">
            <div className="flex gap-4">
              <Input
                type="text"
                placeholder="Buscar personal..."
                className="w-[300px] bg-white"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
              <Select value={branchFilter} onValueChange={setBranchFilter}>
                <SelectTrigger className="w-[200px] bg-white">
                  <SelectValue placeholder="Todas las sucursales" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="all">Todas las sucursales</SelectItem>
                  {branches.map(branch => (
                    <SelectItem key={branch} value={branch}>
                      {branch}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
              <Select value={areaFilter} onValueChange={setAreaFilter}>
                <SelectTrigger className="w-[200px] bg-white">
                  <SelectValue placeholder="Todas las áreas" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="all">Todas las áreas</SelectItem>
                  {areas.map(area => (
                    <SelectItem key={area} value={area}>
                      {area}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
            <Button
              onClick={() => setIsOpen(true)}
              className="bg-purple-500 hover:bg-purple-600 text-white"
            >
              <Plus className="h-4 w-4 mr-2" />
              Agregar Personal
            </Button>
          </div>
        </div>

        {/* Table */}
        <div className="bg-white rounded-lg shadow overflow-hidden">
          {isLoading ? (
            <div className="p-8 text-center">
              <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-purple-500 mx-auto" />
              <p className="mt-2 text-gray-500">Cargando usuarios...</p>
            </div>
          ) : (
            <UserTable
              users={filteredUsers}
              onEdit={(user) => {
                setSelectedUser(user);
                setIsOpen(true);
              }}
              onDelete={handleDeleteUser}
            />
          )}
        </div>

        {/* Modal */}
        <Dialog open={isOpen} onOpenChange={handleModalClose}>
          <DialogContent className="sm:max-w-[425px]">
            <DialogHeader>
              <DialogTitle>
                {selectedUser ? 'Editar Usuario' : 'Agregar Nuevo Usuario'}
              </DialogTitle>
            </DialogHeader>
            <UserForm
              initialData={selectedUser || undefined}
              onSubmit={selectedUser ? handleEditUser : handleCreateUser}
              onCancel={handleModalClose}
              isSubmitting={createUserMutation.isPending || updateUserMutation.isPending}
            />
          </DialogContent>
        </Dialog>
      </div>
    </div>
  );
};

export default Users;