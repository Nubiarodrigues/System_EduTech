import { Link, useNavigate } from 'react-router-dom';
import { useAuthContext } from '../../contexts/auth/AuthContext';
import logo from '/src/assets/image/logo.png';

export default function SideBar() {

    const { logout } = useAuthContext();

    const navigate = useNavigate();

    const handleLogout = async (e) => {
        const sucess = await logout();

        if (sucess) {
            navigate("/login");
        }
    };

    return (
        <div className="
      bg-blue-500 
      flex flex-col 
      h-screen 
      min-w-[200px] 
      max-w-[300px] 
      w-1/5
      sm:w-1/4
      md:w-1/5
      lg:w-1/6
      p-4
    ">
            <div className="flex flex-col items-center mb-4">
                <img className="w-40 h-auto mb-2" src={logo} alt="logo" />
                <hr className="w-full border-gray-300" />
            </div>

            <nav className="flex flex-col gap-2 mt-4">
                <Link className="hover:bg-blue-400 p-2 rounded" to="/">Home</Link>
                <Link className="hover:bg-blue-400 p-2 rounded" to="/">Turmas</Link>
                <Link className="hover:bg-blue-400 p-2 rounded" to="/">Professores</Link>
                <Link className="hover:bg-blue-400 p-2 rounded" to="/school-details">Escola</Link>

                <button
                    className="mt-auto bg-red-500 hover:bg-red-400 text-white p-2 rounded"
                    type='button'
                    onClick={handleLogout}
                >
                    Sair
                </button>
            </nav>
        </div>
    )
}