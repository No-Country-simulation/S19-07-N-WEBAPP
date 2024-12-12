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

// Tipos base
interface PlaceManager {
  name: string;
  role: string;
  avatar?: string;
}

interface Place {
  id: number;
  name: string;
  address: string;
  employeeCount: number;
  cashierCount: number;
  departments: string[];
  manager: PlaceManager;
}

// Tipo para el formulario
export type PlaceFormData = {
  name: string;
  address: string;
  manager: {
    name: string;
    role: string;
  };
};

const Places = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedPlace, setSelectedPlace] = useState<Place | null>(null);
  const [searchTerm, setSearchTerm] = useState("");

  const places: Place[] = [
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
  ];

  const handleCreatePlace = async (formData: PlaceFormData) => {
    // Crear un nuevo lugar con valores por defecto
    const newPlace: Omit<Place, 'id'> = {
      ...formData,
      employeeCount: 0,
      cashierCount: 0,
      departments: [],
      manager: {
        ...formData.manager,
        avatar: undefined,
      },
    };
    
    console.log('Crear sucursal:', newPlace);
    setIsOpen(false);
  };

  const handleEditPlace = async (formData: PlaceFormData) => {
    if (!selectedPlace) return;

    // Mantener los datos existentes y actualizar con los nuevos
    const updatedPlace: Place = {
      ...selectedPlace,
      name: formData.name,
      address: formData.address,
      manager: {
        ...selectedPlace.manager,
        name: formData.manager.name,
        role: formData.manager.role,
      },
    };

    console.log('Editar sucursal:', updatedPlace);
    setIsOpen(false);
    setSelectedPlace(null);
  };

  const handleDeletePlace = async (id: number) => {
    console.log('Eliminar sucursal:', id);
  };

  const filteredPlaces = places.filter(place =>
    place.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

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
            <span className="text-xl font-semibold">{filteredPlaces.length}</span>
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
          {filteredPlaces.map((place) => (
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
              initialData={
                selectedPlace
                  ? {
                      name: selectedPlace.name,
                      address: selectedPlace.address,
                      manager: {
                        name: selectedPlace.manager.name,
                        role: selectedPlace.manager.role,
                      },
                    }
                  : undefined
              }
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

export default Places