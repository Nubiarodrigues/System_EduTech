import { useState } from "react";
import Header from "../components/Header";
import SideBar from "../components/SideBar";
import { Outlet } from "react-router-dom";

export default function MainLayout() {

  const [isOpen, setIsOpen] = useState(false);

  return (
    <div className="flex h-screen w-screen">
      <aside className="">
        <SideBar isOpen={isOpen} setIsOpen={setIsOpen}/> 
      </aside>

      <div className="flex-1 flex flex-col">
        <header className="flex justify-end items-center bg-[#A0A0A0] h-[12%]">
          <Header /> 
        </header>
        <main className="flex-1 bg-white">
          <Outlet />
        </main>
      </div>
    </div>
  );
}
