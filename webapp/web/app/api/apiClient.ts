import axios from 'axios';

const apiClient = {
  login: axios.create({
    baseURL: 'http://localhost:8080/login',
  }),
};

export default apiClient;
