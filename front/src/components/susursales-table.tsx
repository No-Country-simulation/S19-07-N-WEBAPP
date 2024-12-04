import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
  } from "@/components/ui/table"
  
  interface Sucursal {
    id: string
    name: string
    ingresos: number
    egresos: number
    saldo: number
  }
  
  const data: Sucursal[] = [
    {
      id: "401",
      name: "SucursalNorte",
      ingresos: 15000,
      egresos: 10000,
      saldo: 5000,
    },
    {
      id: "402",
      name: "SucursalSur",
      ingresos: 12000,
      egresos: 8000,
      saldo: 4000,
    },
    // Add more sample data as needed
  ]
  
  export function SucursalesTable() {
    return (
      <div className="rounded-lg border bg-card">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>Sucursal</TableHead>
              <TableHead className="text-right">Ingresos</TableHead>
              <TableHead className="text-right">Egresos</TableHead>
              <TableHead className="text-right">Saldo</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {data.map((sucursal) => (
              <TableRow key={sucursal.id}>
                <TableCell>{sucursal.id}</TableCell>
                <TableCell>{sucursal.name}</TableCell>
                <TableCell className="text-right">${sucursal.ingresos}</TableCell>
                <TableCell className="text-right">${sucursal.egresos}</TableCell>
                <TableCell className="text-right">${sucursal.saldo}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    )
  }
  
  