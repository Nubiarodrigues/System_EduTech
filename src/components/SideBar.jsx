import { useNavigate } from "react-router-dom";
import { useAuthContext } from "../contexts/auth/AuthContext";
import { Icon } from "@iconify/react";

export default function SideBar({ isOpen, setIsOpen }) {
  const { logout } = useAuthContext();

  const navigate = useNavigate();

  const handleLogout = async (e) => {
    const sucess = await logout();

    if (sucess) {
      navigate("/login");
    }
  };

  const menuItems = [
    { icon: "mdi:home", label: "Home", path: "/" },
    { icon: "mdi:school", label: "Turmas", path: "/turmas" },
    { icon: "mdi:teach-poll", label: "Professores", path: "/professores" },
    { icon: "mdi:notebook-multiple",label: "Disciplinas",path: "/disciplinas",},
    { icon: "mdi:office-building", label: "Escola", path: "/school-details" },
  ];

  return (
    <div className={`bg-[#074F8A] text-white flex flex-col items-center h-full p-2 transition-all ${isOpen ? "w-60" : "w-16"}`}>
      <div className="flex items-center mb-4 cursor-pointer mt-3" onClick={() => setIsOpen(!isOpen)}>
        <Icon
          icon={isOpen ? "ic:baseline-close" : "ic:baseline-menu"}
          width={20}/>
      </div>

      <nav className="flex flex-col gap-2 mt-4">
        {menuItems.map((item) => (
          <button
            key={item.path}
            onClick={() => navigate(item.path)}
            className="hover:bg-blue-400 p-2 rounded flex items-center gap-2 cursor-pointer w-full">
            <Icon icon={item.icon} width={20} />
            {isOpen && <span>{item.label}</span>}
          </button>
        ))}

        <div className="flex flex-col mt-53">
          <button className="hover:bg-blue-400 p-2 rounded flex items-center gap-2 cursor-pointer w-full">
            <Icon icon="ic:baseline-settings" width={20} />
            {isOpen && "Configurações"}
          </button>

          <button onClick={handleLogout} className="hover:bg-blue-400 p-2 rounded flex items-center gap-2 cursor-pointer w-full">
            <Icon icon="ic:baseline-logout" width={20} />
            {isOpen && "Sair"}
          </button>
        </div>
      </nav>
    </div>
  );
}
