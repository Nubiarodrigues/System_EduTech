import LoginForm from "../../components/LoginForm/LoginForm"
import styles from './Login.module.css';

const Login = () => {
    return (
        <div className={styles.container_global}>

            <LoginForm />

            <div className={styles.container_right}>

            </div>

        </div>
    )
}

export default Login