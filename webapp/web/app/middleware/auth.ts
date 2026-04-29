export default defineNuxtRouteMiddleware((to) => {
  const token = useCookie('accessToken');
  if (!token.value && to.path !== '/login') {
    return navigateTo('/login');
  }
});
