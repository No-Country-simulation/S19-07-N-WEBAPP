"use client";
import { ComponentProps, ComponentType } from "react";
import { LazyMotion, motion, domAnimation } from "motion/react";
interface Section extends ComponentProps<"section"> {}

const Transition: ComponentType<Section> = ({ children }) => {
  return (
    <LazyMotion features={domAnimation}>
      <motion.div
        initial={{ opacity: 0, y: 5 }}
        animate={{ opacity: 1, y: 0 }}
        exit={{ opacity: 0, y: 5 }}
        transition={{ duration: 0.2, ease: "easeIn" }}
        className="flex-1 "
      >
        {children}
      </motion.div>
    </LazyMotion>
  );
};

export default Transition;
