import { createBrowserRouter } from "react-router-dom";
import AdminForm from "../components/forms/AdminForm";
import SchoolForm from "../components/forms/SchoolForm";
import MainLayout from "../layouts/MainLayout";
import Home from "../pages/Home";
import Login from "../pages/Login";;
import SchoolPanel from "../pages/SchoolPanel";
import ClassesPanel from "../pages/ClassesPanel";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <MainLayout />,
    children: [
      { index: true, element: <Home /> },
      { path: "/school-details", element: <SchoolPanel /> },
      {path: "classes", element: <ClassesPanel />}
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
    element: <SchoolForm />,
  },
]);
