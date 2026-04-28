import axios from 'axios';
import { useRuntimeConfig } from '#app';

function getApiClient() {
  const config = useRuntimeConfig();
  return axios.create({
    baseURL: config.public.apiUrl,
  });
}

const login = async (loginId: string, password: string) => {
  const apiClient = getApiClient();
  return apiClient.post('/login', { loginId, password });
};

export { login };
