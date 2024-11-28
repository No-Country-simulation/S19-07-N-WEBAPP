import { SidebarProvider, SidebarTrigger } from "@/components/ui/sidebar";
import { AppSidebar } from "@/components/app-sidebar";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Toaster } from "@/components/ui/sonner";

const queryClient = new QueryClient();

const LoginLayout = ({ children }: { children: React.ReactNode }) => {
    return (
        <QueryClientProvider client={queryClient}>
            <SidebarProvider>
                <AppSidebar />
                <div className="flex justify-center items-center min-h-screen bg-white md:gradient-bg">
                    <SidebarTrigger />
                    {children}
                </div>
            </SidebarProvider>
            <Toaster />
        </QueryClientProvider>
    );
};

export default LoginLayout;