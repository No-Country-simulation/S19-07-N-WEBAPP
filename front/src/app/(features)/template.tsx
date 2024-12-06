"use client";
import { ComponentProps, ComponentType } from "react";
import { LazyMotion, motion, domAnimation } from "motion/react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();
interface Section extends ComponentProps<"section"> {}

const Transition: ComponentType<Section> = ({ children }) => {
  return (
    <LazyMotion features={domAnimation}>
      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        exit={{ opacity: 0 }}
        transition={{ duration: 0.2, ease: "easeIn" }}
        className="flex-1 "
      >
        <QueryClientProvider client={queryClient}>
          {children}
        </QueryClientProvider>
      </motion.div>
    </LazyMotion>
  );
};

export default Transition;
