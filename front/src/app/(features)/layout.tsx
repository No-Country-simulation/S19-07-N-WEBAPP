import HeaderNavbar from "@/components/navbar";
import { Sidebar } from "@/components/sidebar";

export default function FeatureLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="flex flex-col">
      <HeaderNavbar />
      <div className="flex">
        <Sidebar className="w-64" />
        <div className="flex-grow"> {/* Asegúrate de que el contenido ocupe el espacio restante */}
          {children}
        </div>
      </div>
    </div>
  );
}