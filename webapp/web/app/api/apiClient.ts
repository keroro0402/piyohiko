import axios from 'axios';
import { useRuntimeConfig } from '#app';

function getApiClient() {
  const config = useRuntimeConfig();
  return axios.create({
    baseURL: config.public.apiUrl,
  });
}

export const login = async (email: string, password: string) => {
  const apiClient = getApiClient();
  return apiClient.post('/login', { email, password });
};

export default getApiClient;
