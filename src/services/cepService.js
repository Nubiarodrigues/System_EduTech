import axios from "axios";

const BASE_URL = "https://localhost:8443/api/cep";

export const findAddress = (cep) => {
    return axios.get(`${BASE_URL}/${cep}`);
}