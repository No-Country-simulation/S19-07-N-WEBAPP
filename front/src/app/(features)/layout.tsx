"use client"

import HeaderNavbar from "@/components/navbar";
import { Sidebar } from "@/components/sidebar";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { useState } from "react";

export default function FeatureLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const [queryClient] = useState(() => new QueryClient({
    defaultOptions: {
      queries: {
        refetchOnWindowFocus: false, 
        retry: 1,
      },
    },
  }));

  return (
    <QueryClientProvider client={queryClient}>
      <div className="flex flex-col">
        <HeaderNavbar />
        <div className="flex">
          <Sidebar className="w-64" />
          <div className="flex-grow">
            {children}
          </div>
        </div>
      </div>
    </QueryClientProvider>
  );
}