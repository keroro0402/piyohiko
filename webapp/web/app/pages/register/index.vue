<template>
  <main class="register-page">
    <section class="register-page__content">
      <h1 class="register-page__title">{{ TEXT.REGISTER.LABEL }}</h1>
      <p v-if="registerFailed" class="error-message">
        {{ registerFailed }}
      </p>
      <form :class="BLOCK_NAME" @submit.prevent="onSubmit">
        <FormGroupInput :id="FIELD.LOGIN_ID" v-model="loginId" v-bind="loginIdProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.FORM.LOGINID" placeholder="test" autocomplete="username" required />
        <FormGroupInput :id="FIELD.PASSWORD" v-model="password" v-bind="passwordProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.FORM.PASSWORD" type="password" minlength="1" placeholder="test" required />
        <FormGroupInput :id="FIELD.CONFIRM_PASSWORD" v-model="confirmPassword" v-bind="confirmPasswordProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.REGISTER.CONFIRM_PASSWORD_LABEL" type="password" minlength="1" placeholder="test" required />
        <SubmitButton :block="BLOCK_NAME" :is-form-valid="isFormValid" :text="TEXT.REGISTER.LABEL" />
      </form>
    </section>
  </main>
</template>

<script setup lang="ts">
import { useForm } from 'vee-validate';
import { TEXT } from '~/constants/text';
import { ref } from 'vue';
import { login } from '~/api/apiClient';
import { PAGE_TITLES } from '~/constants/pages';
import { COOKIE_EXPIRATION } from '~/constants/cookie';
import { errorHandler } from '~/api/errorHandler';
import { useAuth } from '~/composables/useAuth';
import FormGroupInput from '~/components/FormGroupInput.vue';
import SubmitButton from '~/components/SubmitButton.vue';

const BLOCK_NAME = 'register-form';
const FIELD = {
  LOGIN_ID: 'loginId',
  PASSWORD: 'password',
  CONFIRM_PASSWORD: 'confirmPassword',
};

const { setCookies } = useAuth();
definePageMeta({
  layout: 'blank',
});

const userInfoStore = useUserInfoStore();
const { registerSchema } = useAuthValidation();
const { defineField, errors, handleSubmit, meta } = useForm({
  validationSchema: registerSchema,
  initialValues: {
    loginId: '',
    password: '',
  },
});
const [loginId, loginIdProps] = defineField('loginId');
const [password, passwordProps] = defineField('password');
const [confirmPassword, confirmPasswordProps] = defineField('confirmPassword');

const route = useRoute();

const registerFailed = ref('');
const rememberMe = ref(false);

const pageKey = route.name?.toString() || '';
useHead({
  title: PAGE_TITLES[pageKey as keyof typeof PAGE_TITLES] ?? '',
});

const isFormValid = computed(() => meta.value.valid);

const onSubmit = handleSubmit(async (values) => {
  registerFailed.value = '';
  // login API呼び出し
  try {
    const response = await login(values.loginId, values.password, rememberMe.value ? COOKIE_EXPIRATION.REMEMBER_ME : COOKIE_EXPIRATION.DEFAULT);
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
});
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;
@use 'sass:color';

.register-page {
  @include auth-page-layout($color-dark-green);
}
</style>
