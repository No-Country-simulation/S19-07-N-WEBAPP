import React, { type LabelHTMLAttributes } from "react";
import { cva, VariantProps } from "class-variance-authority";

export const label = cva(
  [
    "font-medium", 
    "mb-1", 
    "aria-[invalid=true]:text-red-600",
    "text-sm", 
    "text-zinc-900",
  ],
  {
    variants: {
      size: {
        small: ["text-xs", "font-medium"],
        medium: ["text-sm"],
      }
    },
    compoundVariants: [
      {
        size: "medium",
        className: "",
      },
    ],
    defaultVariants: {
      size: "medium",
    },
  }
);

type LabelProps = {
  children: React.ReactNode;
  "aria-invalid"?: boolean;
  srOnly?: boolean;
} & Omit<LabelHTMLAttributes<HTMLLabelElement>, "children" | "aria-invalid"> 
  &  VariantProps<typeof label>;

export const Label = ({ srOnly = false, size, className, ...props }: LabelProps) => (
  <label
    className={!srOnly 
      ? label({
          size,
          className
        })
      : "sr-only"
    }
    {...props}
  />
);
