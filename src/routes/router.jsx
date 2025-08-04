import { createBrowserRouter } from "react-router-dom";
import FormRegister from "../components/School/FormRegister";
import MainLayout from "../layouts/MainLayout";
import Home from '../pages/Home/Home';
import SchoolPanel from "../pages/School/SchoolPanel";
import LoginScreen from '../pages/LoginScreen/Login';

export const router = createBrowserRouter([
    {
        path: "/",
        element: <MainLayout />,
        children: [
            { index: true, element: <Home /> },
            { path: "/school-details", element: <SchoolPanel /> },
            { path: "/register-school", element: <FormRegister /> },
        ]
    },

    {
        path: "/login",
        element: <LoginScreen />
    }
])