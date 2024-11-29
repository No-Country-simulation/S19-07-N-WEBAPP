import { Card, CardContent } from "@/components/ui/card"
import { cn } from "@/lib/utils"
import { ArrowDownIcon, ArrowUpIcon } from 'lucide-react'

interface KPICardProps {
  title: string
  amount: number
  change: number
  type: "ingresos" | "egresos" | "saldo"
}

export function KPICard({ title, amount, change, type }: KPICardProps) {
  const isPositive = change > 0
  const changeColor = type === "saldo" 
    ? "text-purple-500" 
    : isPositive 
      ? "text-green-500" 
      : "text-red-500"
  
  return (
    <Card>
      <CardContent className="pt-6">
        <div className="text-sm text-muted-foreground mb-2">{title}</div>
        <div className="flex items-center justify-between">
          <div className="text-2xl font-bold">
            ${amount.toLocaleString()}
          </div>
          <div className={cn("flex items-center gap-1 text-sm", changeColor)}>
            {isPositive ? (
              <ArrowUpIcon className="h-4 w-4" />
            ) : (
              <ArrowDownIcon className="h-4 w-4" />
            )}
            {Math.abs(change)}%
          </div>
        </div>
      </CardContent>
    </Card>
  )
}

