import { createBrowserRouter } from "react-router-dom";
import FormRegister from "../components/School/FormRegister";
import MainLayout from "../layouts/MainLayout";
import Home from '../pages/Home/Home';
import SchoolPanel from "../pages/School/SchoolPanel";
import LoginScreen from '../pages/LoginScreen/Login';
import AdminForm from "../components/AdminForm/AdminForm";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <MainLayout />,
        children: [
            { index: true, element: <Home /> },
            { path: "/school-details", element: <SchoolPanel /> },
        ]
    },

    {
        path: "/login",
        element: <LoginScreen />
    },

    {
        path: "/createAdmin",
        element: <AdminForm />
    },

    {
        path: "/createSchool",
        element: <FormRegister />
    }
])