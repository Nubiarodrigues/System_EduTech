import { useAuthContext } from '../../contexts/auth/AuthContext';
import styles from './Header.module.css';

export default function Header() {

    const { user, isLoadingLoggedUser, sendLogin, logout } = useAuthContext();

    if (isLoadingLoggedUser) return <p>Carregando usuário...</p>;

    if (!user) return <p>Usuário não está logado.</p>;

    return <p>Olá, {user.name} - {user.role}!</p>;
}