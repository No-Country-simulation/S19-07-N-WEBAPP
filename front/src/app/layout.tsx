import type { Metadata } from "next";
import { Poppins } from "next/font/google";
import "./globals.css";
import { Toaster } from "@/components/ui/sonner";
import HeaderNavbar from "@/components/navbar";

const poppins = Poppins({
  subsets: ["latin"],
  weight: ["100", "200", "300", "400", "500", "600", "700", "800", "900"],
  variable: "--font-poppins",
});

export const metadata: Metadata = {
  title: "Financy",
  description: "seleccionado 19 | grupo 7",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={`${poppins.variable} antialiased`}>
        <div className="flex min-h-screen overflow-hidden">
          <div className="flex-1">
            <HeaderNavbar /> {/* Navbar en la parte superior */}
            <main>{children}</main> {/* Contenido principal */}
          </div>
        </div>
        <Toaster expand />
      </body>
    </html>
  );
}