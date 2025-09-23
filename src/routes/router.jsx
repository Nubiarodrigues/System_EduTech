import { createBrowserRouter } from "react-router-dom";
import AdminForm from "../components/AdminForm";
import FormRegister from "../components/FormRegister";
import MainLayout from "../layouts/MainLayout";
import Home from "../pages/Home";
import Login from "../pages/Login";
import Profile from "../pages/Profile";
import SchoolPanel from "../pages/SchoolPanel";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <MainLayout />,
    children: [
      { index: true, element: <Home /> },
      { path: "/school-details", element: <SchoolPanel /> },
      { path: "/profile", element: <Profile /> },
    ],
  },

  {
    path: "/login",
    element: <Login />,
  },

  {
    path: "/createAdmin",
    element: <AdminForm />,
  },

  {
    path: "/createSchool",
    element: <FormRegister />,
  },
]);
