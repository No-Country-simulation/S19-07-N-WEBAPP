"use client";

import { useRouter } from "next/navigation";

const useNavigation = () => {
  const nav = useRouter();

  const goHome = () => nav.push("/dash");
  const goBack = () => nav.back();
  const goLogin = () => nav.push("/login");

  return { goHome, goBack, goLogin };
};

export default useNavigation;
