import { useQuery, useMutation, QueryClient } from '@tanstack/react-query';
import axios from 'axios';
import { toast } from 'sonner';

const BASE_URL = 'https://s19-07-n-webapp.onrender.com/api';
const queryClient = new QueryClient();

// Types
export interface User {
  id: string;
  name?: string;
  email?: string;
  position?: string;
  branch?: string;
  area?: string;
  startDate?: string;
}

// Funciones de fetch
const fetchUsers = async (): Promise<User[]> => {
  const { data } = await axios.get(`${BASE_URL}/user`);
  return data;
};

const fetchUserById = async (id: string): Promise<User> => {
  const { data } = await axios.get(`${BASE_URL}/user/${id}`);
  return data;
};

// Custom Hooks con React Query
export const useUsers = () => {
  return useQuery({
    queryKey: ['users'],
    queryFn: fetchUsers,
  });
};

export const useUser = (id: string) => {
  return useQuery({
    queryKey: ['user', id],
    queryFn: () => fetchUserById(id),
    enabled: !!id, // Solo se ejecuta si hay un ID
  });
};

// Mutaciones
export const useCreateUser = () => {
  return useMutation({
    mutationFn: (newUser: Omit<User, 'id'>) => 
      axios.post(`${BASE_URL}/user`, newUser),
    onError: (error) => {
      console.error('Error creating user:', error);
      toast.error("No se pudo crear el usuario");
    },
    onSuccess: () => {
      toast.success("Usuario creado correctamente");
      queryClient.invalidateQueries({ queryKey: ['users'] });
    },
  });
};

export const useUpdateUser = () => {
  return useMutation({
    mutationFn: (user: User) => 
      axios.put(`${BASE_URL}/user/${user.id}`, user),
    onError: (error) => {
      console.error('Error updating user:', error);
      toast.error("No se pudo actualizar el usuario");
    },
    onSuccess: () => {
      toast.success("Usuario actualizado correctamente");
      queryClient.invalidateQueries({ queryKey: ['users'] });
    },
  });
};

export const useDeleteUser = () => {
  return useMutation({
    mutationFn: (id: string) => 
      axios.delete(`${BASE_URL}/user/${id}`),
    onError: (error) => {
      console.error('Error deleting user:', error);
      toast.error("No se pudo eliminar el usuario");
    },
    onSuccess: () => {
      toast.success("Usuario eliminado correctamente");
      queryClient.invalidateQueries({ queryKey: ['users'] });
    },
  });
};

// Utilidad para manejar errores de API (opcional)
export const handleApiError = (error: unknown) => {
  if (axios.isAxiosError(error)) {
    return error.response?.data?.message || "Error en la operación";
  }
  return "Error inesperado";
};