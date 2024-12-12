import { Edit, Trash2, Users, CreditCard } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Avatar, AvatarFallback } from '@/components/ui/avatar';

interface PlaceCardProps {
  place: {
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
  };
  onEdit: () => void;
  onDelete: () => void;
}

const PlaceCard = ({ place, onEdit, onDelete }: PlaceCardProps) => {
  return (
    <div className="bg-white rounded-lg shadow-sm p-6">
      <div className="flex items-start justify-between mb-4">
        <div>
          <h3 className="text-lg font-semibold text-gray-900">{place.name}</h3>
          <p className="text-sm text-gray-500">{place.address}</p>
        </div>
        <div className="flex gap-2">
          <Button variant="ghost" size="sm" onClick={onEdit}>
            <Edit className="h-4 w-4" />
          </Button>
          <Button variant="ghost" size="sm" onClick={onDelete}>
            <Trash2 className="h-4 w-4 text-red-500" />
          </Button>
        </div>
      </div>

      <div className="flex items-center gap-4 mb-4">
        <Avatar className="h-10 w-10">
          <AvatarFallback>
            {place.manager.name.charAt(0)}
          </AvatarFallback>
        </Avatar>
        <div>
          <p className="text-sm font-medium">{place.manager.name}</p>
          <p className="text-xs text-gray-500">{place.manager.role}</p>
        </div>
      </div>

      <div className="grid grid-cols-2 gap-4 mb-4">
        <div className="flex items-center gap-2">
          <Users className="h-4 w-4 text-gray-400" />
          <span className="text-sm text-gray-600">
            {place.employeeCount} Empleados
          </span>
        </div>
        <div className="flex items-center gap-2">
          <CreditCard className="h-4 w-4 text-gray-400" />
          <span className="text-sm text-gray-600">
            {place.cashierCount} Cajas
          </span>
        </div>
      </div>

      <div className="flex flex-wrap gap-2">
        {place.departments.map((dept, index) => (
          <span
            key={index}
            className="px-2 py-1 text-xs bg-purple-50 text-purple-600 rounded"
          >
            {dept}
          </span>
        ))}
      </div>
    </div>
  );
};

export default PlaceCard;