import type { Metadata } from "next";
import { Poppins } from "next/font/google";
import "./globals.css";
import { Toaster } from "@/components/ui/sonner";

const poppins = Poppins({
  subsets: ["latin"],
  weight: ["100", "200", "300", "400", "500", "600", "700", "800", "900"],
  variable: "--font-poppins",
});

export const metadata: Metadata = {
  title: "Fineazily | Gestión Integral para PYMES",
  description: "Herramienta all-in-one para administrar sucursales, personal y finanzas. Optimiza tu negocio con tecnología inteligente.",
  keywords: [
    "PYME", 
    "gestión empresarial", 
    "finanzas", 
    "administración", 
    "sucursales", 
    "recursos humanos", 
    "software de gestión"
  ],
  openGraph: {
    title: "Fineazily - Solución Integral para tu Negocio",
    description: "Transforma la gestión de tu empresa con nuestra plataforma todo en uno",
    images: [
      {
        url: "https://raw.githubusercontent.com/No-Country-simulation/S19-07-N-WEBAPP/MS-A/front/public/FLogo.webp",
        width: 1200,
        height: 630,
        alt: "Financy - Gestión Empresarial"
      }
    ],
    type: "website",
    locale: "es_ES"
  },
  twitter: {
    card: "summary_large_image",
    title: "Fineazily| Gestión Integral para PYMES",
    description: "Herramienta all-in-one para administrar sucursales, personal y finanzas.",
    images: [
      "https://raw.githubusercontent.com/No-Country-simulation/S19-07-N-WEBAPP/MS-A/front/public/FLogo.webp"
    ]
  },
  applicationName: "Fineazily",
  authors: [{ name: "No Country Team", url: "https://www.nocountry.tech" }],
  creator: "No Country Simulation Team equipo-s19-07-n-webapp ",
  publisher: "No Country",
  robots: "index, follow",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="es">
      <body className={`${poppins.variable} antialiased`}>
        <div className="flex min-h-screen overflow-hidden">
          <div className="flex-1">
            <main>{children}</main> 
          </div>
        </div>
        <Toaster expand />
      </body>
    </html>
  );
}