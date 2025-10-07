import axios from "axios";

const BASE_URL = "https://localhost:8443";

export const findById = (id) => {
  return axios.get(`${BASE_URL}/students/${id}`, { withCredentials: true });
};

export const findAllFiltered = (params) => {
  return axios.get(`${BASE_URL}/students?${params}`, { withCredentials: true });
};
