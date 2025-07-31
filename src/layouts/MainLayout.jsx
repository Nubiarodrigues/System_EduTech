import { Outlet } from "react-router-dom";
import Header from "../components/Header/Header";
import SideBar from "../components/SideBar/SideBar";
import styles from './MainLayout.module.css';

export default function MainLayout(){
    return (
      <div className={styles.layout_container}>
      <aside className={styles.sidebar_container}><SideBar /></aside>

      <div className={styles.main_wrapper}>
        <header className={styles.header_container}><Header/></header>
        <main className={styles.outlet}>
          <Outlet />
        </main>
      </div>
    </div>
    )
}