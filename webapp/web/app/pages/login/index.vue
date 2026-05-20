<template>
  <main class="login-page">
    <section class="login-page__content">
      <h1 class="login-page__title">{{ TEXT.LOGIN.LABEL }}</h1>
      <p v-if="loginFailed" class="error-message">
        {{ loginFailed }}
      </p>
      <form :class="BLOCK_NAME" @submit.prevent="handleSubmit">
        <FormGroupInput :id="FIELD.LOGIN_ID" v-model="loginId" :block="BLOCK_NAME" :text="TEXT.FORM.LOGINID" placeholder="test" autocomplete="username" required />
        <FormGroupInput :id="FIELD.PASSWORD" v-model="password" :block="BLOCK_NAME" :text="TEXT.FORM.PASSWORD" type="password" minlength="1" placeholder="test" required />
        <div :class="`${BLOCK_NAME}__check`">
          <input :id="FIELD.REMEMBER_ME" v-model="rememberMe" :class="`${BLOCK_NAME}__checkbox`" type="checkbox" />
          <label :for="FIELD.REMEMBER_ME">
            {{ TEXT.LOGIN.REMEMBERME }}
          </label>
        </div>
        <SubmitButton :block="BLOCK_NAME" :is-form-valid="isFormValid" :text="TEXT.LOGIN.LABEL" />
        <div :class="`${BLOCK_NAME}__forgot-password`">
          <a :class="`${BLOCK_NAME}__forgot-link`" href="#">{{ TEXT.LOGIN.FORGOTPASSWORD }}</a>
        </div>
      </form>
    </section>
  </main>
</template>

<script setup lang="ts">
import { TEXT } from '~/constants/text';
import { ref } from 'vue';
import { login } from '~/api/apiClient';
import { PAGE_TITLES } from '~/constants/pages';
import { COOKIE_EXPIRATION } from '~/constants/cookie';
import { errorHandler } from '~/api/errorHandler';
import { useAuth } from '~/composables/useAuth';
import FormGroupInput from '~/components/FormGroupInput.vue';
import SubmitButton from '~/components/SubmitButton.vue';

const BLOCK_NAME = 'login-form';
const FIELD = {
  LOGIN_ID: 'loginId',
  PASSWORD: 'password',
  REMEMBER_ME: 'rememberMe',
};

const { setCookies } = useAuth();
definePageMeta({
  layout: 'blank',
});

const userInfoStore = useUserInfoStore();
const { validateLoginForm } = useAuthValidation();
const route = useRoute();
const loginId = ref('');
const password = ref('');
const loginFailed = ref('');
const rememberMe = ref(false);

const pageKey = route.name?.toString() || '';
useHead({
  title: PAGE_TITLES[pageKey as keyof typeof PAGE_TITLES] ?? '',
});

const isFormValid = computed(() => {
  return validateLoginForm(loginId.value, password.value);
});

const handleSubmit = async () => {
  loginFailed.value = '';
  // login API呼び出し
  try {
    const response = await login(loginId.value, password.value, rememberMe.value ? COOKIE_EXPIRATION.REMEMBER_ME : COOKIE_EXPIRATION.DEFAULT);
    if (response.data) {
      // Cookieにアクセストークンを保存
      setCookies(response.data.token.accessToken, {
        maxAge: rememberMe.value ? COOKIE_EXPIRATION.REMEMBER_ME : COOKIE_EXPIRATION.DEFAULT,
        sameSite: 'lax',
        secure: true,
      });
      // ストアにユーザー情報を保存
      userInfoStore.setUserName(response.data.user.loginId);
      userInfoStore.setUserRole(response.data.user.role);
      await navigateTo('/'); // TOPページへ遷移
    }
  } catch (error) {
    loginFailed.value = errorHandler(error);
    return;
  }
};
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;
@use 'sass:color';

.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: $color-dark-brown;
  &__content {
    width: 400px;
    margin: 0 auto;
    padding: 2rem;
    background: $color-light-gray;
    border-radius: 12px;
    box-shadow: 0 10px 30px $color-thin-black;
  }
  &__title {
    text-align: center;
    margin-bottom: 2rem;
    color: $color-dark-gray;
    font-weight: 600;
  }
}
.login-form {
  &__check {
    display: flex;
    align-items: center;
    margin-bottom: 1.5rem;
  }
  &__checkbox {
    margin-right: 0.5rem;
  }
  &__forgot-password {
    text-align: center;
    margin-top: 1rem;
  }
}
</style>
