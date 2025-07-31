import { createBrowserRouter } from "react-router-dom";
import SideBar from "../components/SideBar/SideBar";
import MainLayout from "../layouts/MainLayout";
import Home from '../pages/Home/Home';
import Header from "../components/Header/Header";

export const router = createBrowserRouter ([
    {
        path: "/",
        element: <MainLayout />,
        children: [
            {index: true, element: <Home />}
        ]
    }
])