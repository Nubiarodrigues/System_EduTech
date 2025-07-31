import { Link } from 'react-router-dom'
import logo from '/src/assets/image/logo.png'
import styles from './SideBar.module.css'

export default function SideBar(){
    return (
        <div className={styles.sidebar_container}>
            <div>
                <img className={styles.logo} src={logo} alt="logo" />
                <hr/>
            </div>
            <nav>
                <Link className={styles.item} to="/">Home</Link>
                <Link className={styles.item} to="/">Turmas</Link>
                <Link className={styles.item} to="/">Professores</Link>
                <Link className={styles.item} to="/">Sair</Link>
            </nav>
        </div>
    )
}