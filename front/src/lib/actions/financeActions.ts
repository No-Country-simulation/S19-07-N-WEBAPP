// "use client"
import { IncomeType } from "../validations/finance.income";
import { api } from "./api";

export const NewIncome = async (data: IncomeType) => {
  const prom = new Promise((resolve, reject) => {
    setTimeout(() => {
      puschIncome(data);
      resolve("Ingreso agregado correctamente");
    }, 3000);
    // setTimeout(() => {
    //   reject("Error al agregar el ingreso");
    // }, 3000);
  });

  return prom;
};

const puschIncome = (data: IncomeType) => {
  const incomes = localStorage.getItem("incomes");
  if (incomes) {
    const incomesParsed = JSON.parse(incomes);
    incomesParsed.push(data);
    localStorage.setItem("incomes", JSON.stringify(incomesParsed));
  } else {
    localStorage.setItem("incomes", JSON.stringify([data]));
  }
};
