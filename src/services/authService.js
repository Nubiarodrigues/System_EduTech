import axios from "axios";

const BASE_URL = "https://localhost:8443/auth"

export const login = (credentials) => {
    return axios.post(`${BASE_URL}/login`, credentials, { withCredentials: true });
}

export const logout = () => {
    return axios.post(`${BASE_URL}/logout`, {}, { withCredentials: true })
}

export const getUser = async () => {
    return axios.get(`${BASE_URL}/user`, { withCredentials: true });
}