
import { useAuthContext } from '../../contexts/auth/AuthContext';
import styles from './ProfileUser.module.css';

const ProfileUser = () => {
    const { user } = useAuthContext();

    if (!user) return <p>Usuário não está logado.</p>;

    return (
        <div className={styles.container}>
            <form className={styles.form}>
                <div className={styles.img_user_container}>
                    <button type="button" className={styles.btn_edit}>
                        
                    </button>
                </div>

                <label>
                    Nome
                    <input type="text" name="name" value={user.name} />
                </label>

                <label>
                    E-mail
                    <input type="email" name="email" value={user.email} />
                </label>

                <label>
                    Permissão
                    <select id="role" value={user.role}>
                        <option value="ADMIN">Administrador</option>
                        <option value="OPERATOR">Operador</option>
                    </select>
                </label>

                <label>
                    Senha
                    <input type="password" name="password" value={user.password} />
                </label>
            </form>
        </div>
    );
};

export default ProfileUser;
