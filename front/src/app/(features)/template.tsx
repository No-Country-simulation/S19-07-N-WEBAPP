"use client";
import { ComponentProps, ComponentType } from "react";
import { LazyMotion, motion, domAnimation } from "motion/react";

type Section = ComponentProps<"section">;

const Transition: ComponentType<Section> = ({ children }) => {
  return (
    <LazyMotion features={domAnimation}>
      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        exit={{ opacity: 0 }}
        transition={{ duration: 0.2, ease: "easeIn" }}
        className="flex-1"
      >
        {children}
      </motion.div>
    </LazyMotion>
  );
};

export default Transition;