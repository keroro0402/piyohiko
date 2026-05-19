<template>
  <main class="register-page">
    <section class="register-page__content">
      <h1 class="register-page__title">{{ TEXT.REGISTER.REGISTERLABEL }}</h1>
      <p v-if="registerFailed" class="error-message">
        {{ registerFailed }}
      </p>
      <form class="register-form" @submit.prevent="handleSubmit">
        <div class="register-form__group">
          <label class="register-form__label" for="loginId">
            {{ TEXT.REGISTER.LOGINIDLABEL }}
          </label>
          <input id="loginId" v-model="loginId" class="register-form__input" required placeholder="test" autocomplete="username" />
        </div>
        <div class="register-form__group">
          <label class="register-form__label" for="password">
            {{ TEXT.REGISTER.PASSWORDLABEL }}
          </label>
          <input id="password" v-model="password" class="register-form__input" type="password" minlength="1" placeholder="test" autocomplete="new-password" />
        </div>
        <div class="register-form__group">
          <label class="register-form__label" for="confirmPassword">
            {{ TEXT.REGISTER.CONFIRM_PASSWORDLABEL }}
          </label>
          <input id="confirmPassword" v-model="confirmPassword" class="register-form__input" type="password" minlength="1" placeholder="test" autocomplete="new-password" />
        </div>
        <button class="register-form__submit" type="submit">{{ TEXT.REGISTER.REGISTERLABEL }}</button>
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

const { setCookies } = useAuth();
definePageMeta({
  layout: 'blank',
});

const userInfoStore = useUserInfoStore();
const route = useRoute();
const loginId = ref('');
const password = ref('');
const confirmPassword = ref('');
const registerFailed = ref('');
const rememberMe = ref(false);

const pageKey = route.name?.toString() || '';
useHead({
  title: PAGE_TITLES[pageKey as keyof typeof PAGE_TITLES] ?? '',
});

const handleSubmit = async () => {
  registerFailed.value = '';
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
    registerFailed.value = errorHandler(error);
    return;
  }
};
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;
@use 'sass:color';

.register-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: $color-dark-green;
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
.register-form {
  &__group {
    margin-bottom: 1.5rem;
  }
  &__label {
    display: block;
    margin-bottom: 0.5rem;
    color: $color-dark-gray;
  }
  &__input {
    width: 100%;
    box-sizing: border-box;
    padding: 0.5rem;
    border: 1px solid $color-white;
    border-radius: 4px;
  }
  &__check {
    display: flex;
    align-items: center;
    margin-bottom: 1.5rem;
  }
  &__checkbox {
    margin-right: 0.5rem;
  }
  &__submit {
    width: 100%;
    padding: 0.75rem;
    background-color: $color-warm-orange;
    color: $color-white;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
    &:hover {
      background-color: color.adjust($color-warm-orange, $lightness: -10%);
    }
  }
  &__forgot-password {
    text-align: center;
    margin-top: 1rem;
  }
}
</style>
