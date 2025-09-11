import axios from "axios";

const BASE_URL = "https://localhost:8443"

export const create = (data) => {
    return axios.post(`${BASE_URL}/administrators`, data);
}

export const update = (id, data) => {
    return axios.put(`${BASE_URL}/administrators/${id}`, data, { withCredentials: true });
}