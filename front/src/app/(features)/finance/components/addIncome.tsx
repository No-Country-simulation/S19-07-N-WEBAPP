"use client";
import { FC, useState } from "react";
import { Input } from "@/components/ui/input";
import { useForm } from "react-hook-form";
import { IncomeSchema, IncomeType } from "@/lib/validations/finance.income";
import { zodResolver } from "@hookform/resolvers/zod";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Textarea } from "@/components/ui/textarea";

import { IncomeFields } from "@/app/(core)/register/utils/formFields";
import { toast } from "sonner";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { NewExpense, NewIncome } from "@/lib/actions/financeActions";
interface Props {
  buttons: any;
  isIncome: boolean;
}

const AddIncome: FC<Props> = ({ buttons, isIncome }) => {
  const [isLoading, setIsLoading] = useState(false);
  const incomeForm = useForm<IncomeType>({
    resolver: zodResolver(IncomeSchema),
    defaultValues: {
      amount: "",
      description: "",
      title: "",
    },
  });
  const {
    formState: { errors },
    reset,
  } = incomeForm;
  const fn = isIncome ? NewIncome : NewExpense;
  const qClient = useQueryClient();
  const mutation = useMutation({
    mutationFn: (data: IncomeType) => fn(data),
  });

  const onSubmit = (data: IncomeType) => {
    setIsLoading(true);
    toast.dismiss();
    toast.promise(
      new Promise((resolve, reject) => {
        mutation.mutate(data, {
          onSuccess: (res) => resolve(res),
          onError: (err) => reject(err),
        });
      }),
      {
        loading: "Agregando...",
        success: (res: any) => {
          qClient.invalidateQueries({ queryKey: ["incomes"] });
          // close();
          reset();
          return res;
        },
        error: (err) => {
          return err;
        },
        finally: () => {
          setIsLoading(false);
        },
      }
    );
  };
  return (
    <Form {...incomeForm}>
      <form
        onSubmit={incomeForm.handleSubmit(onSubmit)}
        className="flex flex-col gap-3"
        autoComplete="off"
      >
        {IncomeFields.map((income, index) => (
          <FormField
            key={index}
            control={incomeForm.control}
            name={income.name}
            render={({ field }) =>
              income.name === "description" ? (
                <div>
                  <div className="flex items-center mb-2 justify-between">
                    <FormLabel className="text-zinc-950">
                      {income.text}
                    </FormLabel>
                    <FormMessage className="text-[10px] leading-3" />
                  </div>
                  <Textarea
                    disabled={isLoading}
                    className="resize-none"
                    key={index}
                    placeholder={income.placeholder}
                    {...field}
                  />
                </div>
              ) : (
                <FormItem className="space-y-1">
                  <div className="flex items-center justify-between">
                    <FormLabel className="text-zinc-950">
                      {income.text}
                    </FormLabel>
                    <FormMessage className="text-[10px] leading-3" />
                  </div>
                  <FormControl>
                    <Input
                      disabled={isLoading}
                      className={
                        errors[income.name] && "focus-visible:ring-rose-500"
                      }
                      placeholder={income.placeholder}
                      {...field}
                    />
                  </FormControl>
                </FormItem>
              )
            }
          />
        ))}
        {buttons}
      </form>
    </Form>
  );
};

export default AddIncome;
