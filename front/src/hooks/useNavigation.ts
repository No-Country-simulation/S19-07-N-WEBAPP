"use client";

import { useRouter } from "next/navigation";

const useNavigation = () => {
  const nav = useRouter();

  const goHome = () => nav.push("/dash");
  const goBack = () => nav.back();

  return { goHome, goBack };
};

export default useNavigation;
