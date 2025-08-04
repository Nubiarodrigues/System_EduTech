import axios from "axios"

const BASE_URL = "http://localhost:8080/auth"

export const login = (credentials) => {
    return axios.post(`${BASE_URL}/login`, credentials, { withCredentials: true });
}