<template>
  <div class="login-page">
    <section class="login-page__content">
      <h1 class="login-page__title">{{ TEXT.LOGIN.LABEL }}</h1>
      <p v-if="loginFailed" class="error-message">
        {{ loginFailed }}
      </p>
      <form :class="BLOCK_NAME" @submit.prevent="onSubmit">
        <FormGroupInput :id="FIELD.LOGIN_ID" v-model="loginId" v-bind="loginIdProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.FORM.LOGINID" placeholder="test" autocomplete="username" required />
        <FormGroupInput :id="FIELD.PASSWORD" v-model="password" v-bind="passwordProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.FORM.PASSWORD" type="password" minlength="1" placeholder="test" required />
        <div :class="`${BLOCK_NAME}__check`">
          <input :id="FIELD.REMEMBER_ME" v-model="rememberMe" :class="`${BLOCK_NAME}__checkbox`" type="checkbox" />
          <label :for="FIELD.REMEMBER_ME">
            {{ TEXT.LOGIN.REMEMBER_ME }}
          </label>
        </div>
        <SubmitButton :block="BLOCK_NAME" :is-form-valid="isFormValid" :text="TEXT.LOGIN.LABEL" />
        <div :class="`${BLOCK_NAME}__forgot-password`">
          <NuxtLink :class="`${BLOCK_NAME}__forgot-link`" :to="LINKS.TEXT.PASSWORD_RESET">{{ TEXT.LOGIN.FORGOT_PASSWORD }}</NuxtLink>
        </div>
        <div :class="`${BLOCK_NAME}__switch-page`">
          <NuxtLink :class="`${BLOCK_NAME}__switch-link`" :to="LINKS.TEXT.SIGNUP">{{ TEXT.LOGIN.NEW_USER }}</NuxtLink>
        </div>
      </form>
    </section>
  </div>
</template>

<script setup lang="ts">
/* 外部ライブラリ（Vue本体やnpmパッケージ） */
import { ref } from 'vue';
import { useForm } from 'vee-validate';
/* プロジェクト共通の仕組み（API、エラーハンドラー、汎用コンポーザブル） */
import { login } from '~/api/apiClient';
import { errorHandler } from '~/api/errorHandler';
import { useAuth } from '~/composables/useAuth';
/* プロジェクト共通の定数（マスターデータ系） */
import { COOKIE_EXPIRATION } from '~/constants/cookie';
import { PAGE_TITLES, LINKS } from '~/constants/pages';
import { TEXT } from '~/constants/text';
/* 子コンポーネント（画面を構成する部品） */
import FormGroupInput from '~/components/FormGroupInput.vue';
import SubmitButton from '~/components/SubmitButton.vue';

/* 画面のメタ情報（Nuxt/Vueのシステム設定） */
definePageMeta({
  layout: 'blank',
  middleware: 'guest',
});
const route = useRoute();
const pageKey = route.name?.toString() || '';
useHead({
  title: PAGE_TITLES[pageKey as keyof typeof PAGE_TITLES] ?? '',
});

/* 当該ページ（ログイン画面）でのみ使用する定数（タイポ防止用） */
const BLOCK_NAME = 'login-form';
const FIELD = {
  LOGIN_ID: 'loginId',
  PASSWORD: 'password',
  REMEMBER_ME: 'rememberMe',
};

/* 外部データ・状態管理（Storeや共通コンポーザブルの呼び出し） */
const userInfoStore = useUserInfoStore();
const { setCookies } = useAuth();
const { loginSchema } = useAuthValidation();

/* フォーム・バリデーション関連（VeeValidate） */
const {
  defineField, // 入力欄の値をバリデーションと紐付ける関数
  errors, // リアルタイムのエラーメッセージが詰まったオブジェクト
  handleSubmit, // 送信時にバリデーションを実行する門番関数
  meta, // フォーム全体の検証状態（エラーの有無など）を持つオブジェクト
} = useForm({
  validationSchema: loginSchema, // Zodで定義した検証ルール
  initialValues: {
    // 画面表示時の初期値（空文字で初期化）
    loginId: '',
    password: '',
  },
});
const [
  // loginId用のVeeValidate処理一式を定義（値と裏方のイベント設定を取得）
  loginId, // v-modelで双方バインディグされた値
  loginIdProps, // 入力欄が動くために必要な「裏方のイベント設定」が入ったオブジェクト
] = defineField('loginId');
const [password, passwordProps] = defineField('password');

/* 画面独自のリアクティブな状態（ref / computed） */
const loginFailed = ref('');
const rememberMe = ref(false);
const isFormValid = computed(() => meta.value.valid && meta.value.dirty); // VeeValidateの結果がvalidにbooleanで入る

/* 送信などのアクション（関数・イベントハンドラー） */
const onSubmit = handleSubmit(async (values) => {
  loginFailed.value = '';
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
    loginFailed.value = errorHandler(error);
    return;
  }
});
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;
@use 'sass:color';

.login-page {
  @include auth-page-layout($color-dark-brown);
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
  &__switch-page {
    text-align: center;
    margin-top: 1rem;
  }
}
</style>
