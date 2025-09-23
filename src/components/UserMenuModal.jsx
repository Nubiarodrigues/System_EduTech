import { useNavigate } from "react-router-dom";

export function UserMenuModal({ isOpen, setOpen, name, role, registration }) {
  const navigate = useNavigate();

  if (!isOpen) return null;

  return (
    <div onClick={() => setOpen(false)}>
      <div onClick={(e) => e.stopPropagation()}>
        <div>
          <p>Nome: {name}</p>
          <p>Permissão: {role}</p>
          <p>Matrícula: {registration}</p>
        </div>
        <div>
          <button>Sair</button>
          <button onClick={() => navigate("/profile")}>Perfil</button>
        </div>
      </div>
    </div>
  );
}
