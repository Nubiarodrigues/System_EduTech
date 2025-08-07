import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthContext } from '../../contexts/auth/AuthContext';
import styles from './LoginForm.module.css';
import logo_login from '/src/assets/image/logo_login.png';

const LoginForm = () => {

    const navigate = useNavigate();

    const { user, isLoadingLoggedUser, sendLogin, logout } = useAuthContext();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);

        try {
            const result = await sendLogin({ email, password });
            console.log(result);

            if (result) {
                navigate("/");
            }
        } catch {
            setError("Falha ao logar.");
        }
    }

    return (
        <div className={styles.container_global}>

            <div>
                <img className={styles.logo} src={logo_login} alt="logo-login" />
                <hr />

                <p>Faça seu login para continuar</p>
            </div>

            <form onSubmit={handleSubmit}>

                <label>
                    Email
                    <input
                        type="email"
                        name="email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        required />
                </label>

                <label>
                    Senha
                    <input
                        type="password"
                        name="password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        required />
                </label>

                <p className={styles.parag}>Esqueceu a senha? </p>

                <button type='submit'>
                    Entrar
                </button>
                {error && <p>Erro: {error.message}</p>}

            </form>

        </div>
    )
}

export default LoginForm