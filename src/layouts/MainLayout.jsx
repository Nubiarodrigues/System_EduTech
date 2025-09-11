import Header from "../components/Header/Header";
import SideBar from "../components/SideBar/SideBar";
import { Outlet } from "react-router-dom";

export default function MainLayout() {
  return (
    <div className="flex h-screen w-screen">
      <aside className="">
        <SideBar />
      </aside>

      <div className="flex-1 flex flex-col">
        <header className="bg-fuchsia-300 flex items-center p-4">
          <Header />
        </header>
        <main className="flex-1 bg-gray-100">
          <Outlet />
        </main>
      </div>
    </div>

  );
}
