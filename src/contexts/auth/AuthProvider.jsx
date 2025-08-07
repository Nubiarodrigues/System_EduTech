import { useEffect, useState } from "react";
import * as authService from '../../services/authService';
import { AuthContext } from "./AuthContext";

export default function AuthProvider({ children }) {

    const [user, setUser] = useState(null);
    const [isLoadingLoggedUser, setIsLoadingLoggedUser] = useState(true);

    useEffect(() => {
        authService.getUser()
            .then(response => setUser(response.data))
            .catch(err => {
                if (err.response?.status === 401) {
                    setUser(null);
                } else {
                    console.error("Erro ao buscar usuário", err);
                }
            })
            .finally(() => setIsLoadingLoggedUser(false));
    }, []);



    async function sendLogin(credentials) {
        try {
            await authService.login(credentials);
            const response = await authService.getUser();
            setUser(response.data);
            return true;
        } catch (error) {
            throw error;
        }
    }

    async function logout() {
        try {
            await authService.logout();
            setUser(null);
            return true;
        } catch (error) {
            console.log("Erro ao deslogar", error);
        }
    }


    return (
        <AuthContext.Provider value={{ user, isLoadingLoggedUser, sendLogin, logout }}>
            {children}
        </AuthContext.Provider>
    );
}