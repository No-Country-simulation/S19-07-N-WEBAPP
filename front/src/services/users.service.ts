import { useQuery, useMutation } from '@tanstack/react-query';
import axios from 'axios';

const BASE_URL = 'https://s19-07-n-webapp.onrender.com/api';

// Types
export interface User {
  id: string;
  name?: string;
  email?: string;
  // Añade más campos según la respuesta real de tu API
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

// Ejemplo de uso de mutaciones (para POST, PUT, DELETE)
export const useCreateUser = () => {
  return useMutation({
    mutationFn: (newUser: Omit<User, 'id'>) => 
      axios.post(`${BASE_URL}/user`, newUser),
  });
};

export const useUpdateUser = () => {
  return useMutation({
    mutationFn: (user: User) => 
      axios.put(`${BASE_URL}/user/${user.id}`, user),
  });
};

export const useDeleteUser = () => {
  return useMutation({
    mutationFn: (id: string) => 
      axios.delete(`${BASE_URL}/user/${id}`),
  });
};