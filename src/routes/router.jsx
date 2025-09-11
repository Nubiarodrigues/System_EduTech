import { createBrowserRouter } from "react-router-dom";
import AdminForm from "../components/AdminForm";
import FormRegister from "../components/School/FormRegister";
import MainLayout from "../layouts/MainLayout";
import Home from '../pages/Home/Home';
import Login from "../pages/Login/Login";
import Profile from "../pages/ProfileUser/Profile";
import SchoolPanel from "../pages/School/SchoolPanel";
import SideBar from "../components/SideBar/SideBar";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <MainLayout />,
        children: [
            { index: true, element: <Home /> },
            { path: "/school-details", element: <SchoolPanel /> },
            { path: "/profile", element: <Profile /> }
        ]
    },

    {
        path: "/login",
        element: <Login />
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