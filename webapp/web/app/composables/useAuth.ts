export const useAuth = () => {
  const logout = () => {
    const accessTokenCookie = useCookie('accessToken');
    const configTokenCookie = useCookie('configToken');

    accessTokenCookie.value = null; // クッキーを削除
    configTokenCookie.value = null; // 設定情報のクッキーを削除

    navigateTo('/login'); // ログインページへリダイレクト
  };

  const setCookies = (accessToken: string, configToken: { maxAge: number; sameSite: string; secure: boolean }) => {
    const accessTokenCookie = useCookie('accessToken');
    const configTokenCookie = useCookie('configToken');

    accessTokenCookie.value = accessToken; // アクセストークンをクッキーに保存
    configTokenCookie.value = JSON.stringify(configToken); // 設定情報をクッキーに保存
  };
  return {
    logout,
    setCookies,
  };
};
