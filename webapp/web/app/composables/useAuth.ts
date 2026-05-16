import type { CookieOptions } from '#app';

export const useAuth = () => {
  const logout = () => {
    const accessTokenCookie = useCookie('accessToken');
    const userInfoCookie = useCookie('userInfo');
    const userInfoStore = useUserInfoStore();

    userInfoStore.clearUserInfo(); // ストアのユーザー情報を削除
    accessTokenCookie.value = null; // アクセストークンのクッキーを削除
    userInfoCookie.value = null; // ユーザー情報のクッキーを削除
    navigateTo('/login'); // ログインページへリダイレクト
  };

  const setCookies = (accessToken: string, configToken: CookieOptions<string>) => {
    const accessTokenCookie = useCookie('accessToken', {
      maxAge: configToken.maxAge,
      sameSite: configToken.sameSite,
      secure: configToken.secure,
    });
    accessTokenCookie.value = accessToken; // アクセストークンをクッキーに保存
  };
  return {
    logout,
    setCookies,
  };
};
