// "use client"
import { IncomeType } from "../validations/finance.income";
import { api } from "./api";

export const NewIncome = async (data: IncomeType) => {
  const newData = { ...data, date: new Date() };
  const prom = new Promise((resolve, reject) => {
    setTimeout(() => {
      pushIncome(newData);
      resolve("Ingreso agregado correctamente");
    }, 3000);
    // setTimeout(() => {
    //   reject("Error al agregar el ingreso");
    // }, 3000);
  });

  return prom;
};

export const NewExpense = async (data: IncomeType) => {
  const newData = { ...data, date: new Date() };
  const prom = new Promise((resolve, reject) => {
    setTimeout(() => {
      pushExpense(newData);
      resolve("Egreso agregado correctamente");
    }, 3000);
    // setTimeout(() => {
    //   reject("Error al agregar el egreso");
    // }, 3000);
  });

  return prom;
};

const pushIncome = (data: IncomeType) => {
  const incomes = localStorage.getItem("incomes");
  if (incomes) {
    const incomesParsed = JSON.parse(incomes);
    incomesParsed.push(data);
    localStorage.setItem("incomes", JSON.stringify(incomesParsed));
  } else {
    localStorage.setItem("incomes", JSON.stringify([data]));
  }
};
const pushExpense = (data: IncomeType) => {
  const incomes = localStorage.getItem("incomes");
  if (incomes) {
    const expensesParsed = JSON.parse(incomes);
    expensesParsed.push(data);
    localStorage.setItem("expenses", JSON.stringify(expensesParsed));
  } else {
    localStorage.setItem("expenses", JSON.stringify([data]));
  }
};
