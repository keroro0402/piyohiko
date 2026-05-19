export const useAuthValidation = () => {
  const validateLoginForm = (loginId: string, password: string) => {
    if (!loginId || !password) {
      return false; // ログインIDとパスワードの両方が必要
    }
    return true;
  };

  const validateRegisterForm = (loginId: string, password: string, confirmPassword: string) => {
    if (!loginId || !password || !confirmPassword) {
      return false; // ログインID、パスワード、確認用パスワードの全てが必要
    }
    return true;
  };
  return {
    validateLoginForm,
    validateRegisterForm,
  };
};
