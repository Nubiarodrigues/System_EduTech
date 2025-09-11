import { useState } from "react";
import { useAuthContext } from "../contexts/auth/AuthContext";
import { UserMenuModal } from "./Modal/UserMenu/UserMenuModal";
import { Icon } from "@iconify/react";

export default function Header() {
  const [modalOpen, setModalOpen] = useState(false);
  const { user, isLoadingLoggedUser } = useAuthContext();

  if (isLoadingLoggedUser) return <p>Carregando usuário...</p>;
  if (!user) return <p>Usuário não está logado.</p>;

  const firstName = user.name.split(" ")[0];

  return (
    <>
      <div className="flex items-center gap-2 cursor-pointer bg-[#F4F4F4] p-2 mr-4 rounded-md text-[12px]" onClick={() => setModalOpen(!modalOpen)}>
       <div>
          <Icon icon="material-symbols:user-attributes" width={25}/>
       </div>
       
        <div>
          <p className="font-semibold">Olá, {firstName}!</p>
          <p className="text-sm">MINHA CONTA</p>
        </div>
      </div>

      <UserMenuModal
        isOpen={modalOpen}
        setOpen={setModalOpen}
        name={user.name}
        role={user.role}
        registration={user.registration}
      />
    </>
  );
}
