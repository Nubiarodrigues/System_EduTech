import { Link, useNavigate } from 'react-router-dom';
import { useAuthContext } from '../../contexts/auth/AuthContext';
import styles from './SideBar.module.css';
import logo from '/src/assets/image/logo.png';

export default function SideBar() {

    const { user, isLoadingLoggedUser, login, logout } = useAuthContext();

    const navigate = useNavigate();

    const handleLogout = async (e) => {
        const sucess = await logout();

        if (sucess) {
            navigate("/login");
        }
    };

    return (
        <div className={styles.sidebar_container}>
            <div>
                <img className={styles.logo} src={logo} alt="logo" />
                <hr />
            </div>
            <nav>
                <Link className={styles.item} to="/">Home</Link>
                <Link className={styles.item} to="/">Turmas</Link>
                <Link className={styles.item} to="/">Professores</Link>
                <Link className={styles.item} to="/school-details">Escola</Link>

                <button type='button' onClick={handleLogout}>
                    Sair
                </button>
            </nav>
        </div>
    )
}