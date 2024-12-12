"use client"

import { useState } from 'react';
import { Plus } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog';
import PlaceForm from '@/components/places/place-form';
import PlaceCard from '@/components/places/place-card';

interface Place {
  id: number;
  name: string;
  address: string;
  employeeCount: number;
  cashierCount: number;
  departments: string[];
  manager: {
    name: string;
    role: string;
    avatar?: string;
  };
}

const Places = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedPlace, setSelectedPlace] = useState<Place | null>(null);
  const [searchTerm, setSearchTerm] = useState("");

  // Datos de ejemplo - reemplazar con llamada a API real
  const places = [
    {
      id: 1,
      name: "Sucursal Central",
      address: "Calle Principal 123",
      employeeCount: 15,
      cashierCount: 3,
      departments: ["Cremería", "Carnicería"],
      manager: {
        name: "Ana García",
        role: "Cajero",
      },
    },
    // ... más sucursales
  ];

  const handleCreatePlace = async (data: Omit<Place, 'id'>) => {
    // Implementar lógica de creación
    console.log('Crear sucursal:', data);
    setIsOpen(false);
  };

  const handleEditPlace = async (data: Place) => {
    // Implementar lógica de edición
    console.log('Editar sucursal:', data);
    setIsOpen(false);
    setSelectedPlace(null);
  };

  const handleDeletePlace = async (id: number) => {
    // Implementar lógica de eliminación
    console.log('Eliminar sucursal:', id);
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto p-6">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold text-gray-900">Sucursales</h1>
          <Button
            onClick={() => setIsOpen(true)}
            className="bg-purple-500 hover:bg-purple-600 text-white"
          >
            <Plus className="h-4 w-4 mr-2" />
            Agregar Sucursal
          </Button>
        </div>

        <div className="flex items-center justify-between mb-6">
          <div className="flex items-center gap-2 bg-white px-4 py-2 rounded-lg shadow-sm">
            <span className="text-purple-500">sucursales</span>
            <span className="text-xl font-semibold">{places.length}</span>
            <span className="text-gray-500">Activas</span>
          </div>

          <Input
            type="text"
            placeholder="Buscar sucursal..."
            className="w-[300px] bg-white"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {places.map((place) => (
            <PlaceCard
              key={place.id}
              place={place}
              onEdit={() => {
                setSelectedPlace(place);
                setIsOpen(true);
              }}
              onDelete={() => handleDeletePlace(place.id)}
            />
          ))}
        </div>

        <Dialog open={isOpen} onOpenChange={setIsOpen}>
          <DialogContent className="sm:max-w-[425px]">
            <DialogHeader>
              <DialogTitle>
                {selectedPlace ? 'Editar Sucursal' : 'Agregar Nueva Sucursal'}
              </DialogTitle>
            </DialogHeader>
            <PlaceForm
              initialData={selectedPlace || undefined}
              onSubmit={selectedPlace ? handleEditPlace : handleCreatePlace}
              onCancel={() => {
                setIsOpen(false);
                setSelectedPlace(null);
              }}
            />
          </DialogContent>
        </Dialog>
      </div>
    </div>
  );
};

export default Places;