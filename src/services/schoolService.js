import axios from "axios";

const BASE_URL = "https://localhost:8443";

export const create = (data) => {
    return axios.post(`${BASE_URL}/school`, data);
};

export const findById = (id) => {
    return axios.get(`${BASE_URL}/school/${id}`, { withCredentials: true });
};