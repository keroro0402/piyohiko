// ログイン済みは / にリダイレクトさせるためのミドルウェア
export default defineNuxtRouteMiddleware((to) => {
  const token = useCookie('accessToken');
  // トークンがある（ログイン中）なのに /login or /signup に遷移すると / にリダイレクトする
  if (token.value && (to.path === '/login' || to.path === '/signup')) {
    return navigateTo('/');
  }
});
