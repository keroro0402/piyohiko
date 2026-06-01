import axios from 'axios';
import { useRuntimeConfig } from '#app';

function getApiClient() {
  const config = useRuntimeConfig();
  return axios.create({
    baseURL: config.public.apiUrl,
  });
}

const login = async (loginId: string, password: string, expiration: number) => {
  const apiClient = getApiClient();
  return apiClient.post('/login', { loginId, password, expiration });
};

const registerNewUser = async (loginId: string, password: string, securityPhrase: string) => {
  const apiClient = getApiClient();
  return apiClient.post('/register', { loginId, password, securityPhrase });
};

export { login, registerNewUser };
