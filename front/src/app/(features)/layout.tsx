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
        {children}
      </div>
    </div>
  );
}
