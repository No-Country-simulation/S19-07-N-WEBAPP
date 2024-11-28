'use client'
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Toaster } from "@/components/ui/sonner";

const queryClient = new QueryClient();

const LoginLayout = ({ children }: { children: React.ReactNode }) => {
    return (
        <QueryClientProvider client={queryClient}>
                    {children}

            <Toaster />
        </QueryClientProvider>
    );
};

export default LoginLayout;