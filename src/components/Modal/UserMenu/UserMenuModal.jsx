import styles from './UserMenuModal.module.css';
import { useNavigate } from 'react-router-dom';

export function UserMenuModal({ isOpen, setOpen, name, role, registration }) {

    const navigate = useNavigate();

    if (!isOpen) return null;

    return (
        <div className={styles.overlay} onClick={() => setOpen(false)}>
            <div className={styles.container} onClick={(e) => e.stopPropagation()}>
                <div className={styles.container_internal}>
                    <p>Nome: {name}</p>
                    <p>Permissão: {role}</p>
                    <p>Matrícula: {registration}</p>
                </div>
                <div className={styles.container_button}>
                    <button className={styles.btn_logout}>Sair</button>
                    <button className={styles.btn_profile} onClick={() => navigate("/profile")}>Perfil</button>
                </div>
            </div>
        </div>
    );
}
