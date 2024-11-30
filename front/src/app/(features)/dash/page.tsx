import { KPICard } from "@/components/kpi-card";
import { SucursalesTable } from "@/components/susursales-table";
import { DashboardCharts } from "@/components/dashboard-charts";

export default function DashboardPage() {
  return (
    <div className="flex">
      <div className="flex-1">
        <main className="p-8 space-y-8">
          <div className="grid gap-4 md:grid-cols-3">
            <KPICard
              title="Ingresos"
              amount={200000}
              change={5.5}
              type="ingresos"
            />
            <KPICard
              title="Egresos"
              amount={100000}
              change={-2.3}
              type="egresos"
            />
            <KPICard
              title="Saldo"
              amount={100000}
              change={1.2}
              type="saldo"
            />
          </div>
          <DashboardCharts />
          <div className="space-y-4">
            <h2 className="text-lg font-semibold">Sucursales</h2>
            <SucursalesTable />
          </div>
        </main>
      </div>
    </div>
  );
}