"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import {
  Area,
  AreaChart,
  Bar,
  BarChart,
  ResponsiveContainer,
  XAxis,
  YAxis,
} from "recharts"

const areaData = [
  { month: "Jan", ingresos: 100000, egresos: 80000 },
  { month: "Feb", ingresos: 120000, egresos: 90000 },
  { month: "Mar", ingresos: 150000, egresos: 100000 },
  // Add more data points
]

const barData = [
  { month: "Jan", amount: 20000 },
  { month: "Feb", amount: 30000 },
  { month: "Mar", amount: 25000 },
  // Add more data points
]

export function DashboardCharts() {
  return (
    <div className="grid gap-4 md:grid-cols-2">
      <Card>
        <CardHeader>
          <CardTitle>Ingresos vs Egresos</CardTitle>
        </CardHeader>
        <CardContent>
          <ResponsiveContainer width="100%" height={300}>
            <AreaChart data={areaData}>
              <XAxis dataKey="month" />
              <YAxis />
              <Area
                type="monotone"
                dataKey="ingresos"
                stroke="#22c55e"
                fill="#22c55e"
                fillOpacity={0.2}
              />
              <Area
                type="monotone"
                dataKey="egresos"
                stroke="#ef4444"
                fill="#ef4444"
                fillOpacity={0.2}
              />
            </AreaChart>
          </ResponsiveContainer>
        </CardContent>
      </Card>
      <Card>
        <CardHeader>
          <CardTitle>Movimientos Mensuales</CardTitle>
        </CardHeader>
        <CardContent>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={barData}>
              <XAxis dataKey="month" />
              <YAxis />
              <Bar dataKey="amount" fill="#1a3937" />
            </BarChart>
          </ResponsiveContainer>
        </CardContent>
      </Card>
    </div>
  )
}

