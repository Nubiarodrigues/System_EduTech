import axios from "axios";

const BASE_URL = "https://localhost:8443";

export const findAll = () => {
  return axios.get(`${BASE_URL}/classrooms`, { withCredentials: true });
};

export const findAllFiltered = (params) => {
  return axios.get(`${BASE_URL}/classrooms?${params}`, { withCredentials: true });
};
