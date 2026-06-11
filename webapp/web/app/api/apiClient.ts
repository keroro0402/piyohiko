import axios from 'axios';
import { useRuntimeConfig } from '#app';

function getApiClient() {
  const config = useRuntimeConfig();
  return axios.create({
    baseURL: config.public.apiUrl,
  });
}

const login = async (email: string, password: string, expiration: number) => {
  const apiClient = getApiClient();
  return apiClient.post('/login', { email, password, expiration });
};

const signupNewUser = async (email: string, password: string, securityPhrase: string) => {
  const apiClient = getApiClient();
  return apiClient.post('/signup', { email, password, securityPhrase });
};

const requestPasswordReset = async (email: string) => {
  const apiClient = getApiClient();
  return apiClient.post('/password-reset-email', { email });
};

const passwordReset = async (secretCode: string, password: string) => {
  const apiClient = getApiClient();
  return apiClient.post('/password-reset', { secretCode, password });
};

export { login, signupNewUser, requestPasswordReset, passwordReset };
