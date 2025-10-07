import { useNavigate } from "react-router-dom";

export function UserMenuModal({ isOpen, setOpen, name, role, registration }) {
  const navigate = useNavigate();

  if (!isOpen) return null;

  return (
    <>
      <div className="fixed inset-0 bg-black opacity-50 z-40 transition-opacity duration-300" onClick={() => setOpen(false)}/>

      <div className="fixed top-1/2 left-250 transform -translate-x-1/2 -translate-y-60  bg-gray-400 p-6 rounded shadow-lg z-50" onClick={(e) => e.stopPropagation()}>
        <div className="flex flex-col gap-2">
          <p>Nome: {name}</p>
          <p>Permissão: {role}</p>
          <p>Matrícula: {registration}</p>
        </div>

        <div className="flex gap-10 mt-4 bg-blue-200 justify-center">
          <button className="bg-red-500 text-white px-4 py-2 rounded"  onClick={() => console.log("Logout")}>
            Sair
          </button>
          <button className="bg-blue-500 text-white px-4 py-2 rounded" onClick={() => navigate("/profile")}>
            Perfil
          </button>
        </div>
      </div>
    </>
  );
}
