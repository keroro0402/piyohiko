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

const signupNewUser = async (loginId: string, password: string, securityPhrase: string) => {
  const apiClient = getApiClient();
  return apiClient.post('/signup', { loginId, password, securityPhrase });
};

const passwordReset = async (loginId: string) => {
  const apiClient = getApiClient();
  return apiClient.post('/password-reset', { loginId });
};

export { login, signupNewUser, passwordReset };
