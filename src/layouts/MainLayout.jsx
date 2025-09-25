import { useState } from "react";
import Header from "../components/layout/Header";
import SideBar from "../components/layout/SideBar";
import { Outlet } from "react-router-dom";

export default function MainLayout() {
  const [isOpen, setIsOpen] = useState(false);

  const sidebarWidth = isOpen ? 240 : 64; 

  return (
    <div className="flex h-screen w-screen">
      <aside
        style={{ width: sidebarWidth }}
        className="fixed top-0 left-0 h-screen bg-[#074F8A] text-white">
        <SideBar isOpen={isOpen} setIsOpen={setIsOpen} />
      </aside>

      <div
        style={{ marginLeft: sidebarWidth }}
        className="flex-1 flex flex-col">

        <header className="flex justify-end items-center bg-[#A0A0A0] h-16 sticky top-0 z-10">
          <Header />
        </header>

        <main className="flex-1 bg-white overflow-y-auto p-4">
          <Outlet />
        </main>
      </div>
    </div>
  );
}
