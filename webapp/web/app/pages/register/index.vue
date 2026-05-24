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
/* 外部ライブラリ（Vue本体やnpmパッケージ） */
import { ref } from 'vue';
import { useForm } from 'vee-validate';
/* プロジェクト共通の仕組み（API、エラーハンドラー、汎用コンポーザブル） */
import { registerNewUser } from '~/api/apiClient';
import { errorHandler } from '~/api/errorHandler';
/* プロジェクト共通の定数（マスターデータ系） */
import { PAGE_TITLES } from '~/constants/pages';
import { TEXT } from '~/constants/text';
/* 子コンポーネント（画面を構成する部品） */
import FormGroupInput from '~/components/FormGroupInput.vue';
import SubmitButton from '~/components/SubmitButton.vue';

/* 画面のメタ情報（Nuxt/Vueのシステム設定） */
definePageMeta({
  layout: 'blank',
});
const route = useRoute();
const pageKey = route.name?.toString() || '';
useHead({
  title: PAGE_TITLES[pageKey as keyof typeof PAGE_TITLES] ?? '',
});

/* 当該ページ（新規登録画面）でのみ使用する定数（タイポ防止用） */
const BLOCK_NAME = 'register-form';
const FIELD = {
  LOGIN_ID: 'loginId',
  PASSWORD: 'password',
  CONFIRM_PASSWORD: 'confirmPassword',
};

/* 外部データ・状態管理（Storeや共通コンポーザブルの呼び出し） */
const { registerSchema } = useAuthValidation();

/* フォーム・バリデーション関連（VeeValidate）*/
const {
  defineField, // 入力欄の値をバリデーションと紐付ける関数
  errors, // リアルタイムのエラーメッセージが詰まったオブジェクト
  handleSubmit, // 送信時にバリデーションを実行する門番関数
  meta, // フォーム全体の検証状態（エラーの有無など）を持つオブジェクト
} = useForm({
  validationSchema: registerSchema, // Zodで定義した検証ルール
  initialValues: {
    // 画面表示時の初期値（空文字で初期化）
    loginId: '',
    password: '',
    confirmPassword: '',
  },
});
const [
  // loginId用のVeeValidate処理一式を定義（値と裏方のイベント設定を取得）
  loginId, // v-modelで双方バインディグされた値
  loginIdProps, // 入力欄が動くために必要な「裏方のイベント設定」が入ったオブジェクト
] = defineField('loginId');
const [password, passwordProps] = defineField('password');
const [confirmPassword, confirmPasswordProps] = defineField('confirmPassword');

/* 画面独自のリアクティブな状態（ref / computed） */
const registerFailed = ref('');
const isFormValid = computed(() => meta.value.valid); // VeeValidateの結果がvalidにbooleanで入る

/* 送信などのアクション（関数・イベントハンドラー） */
const onSubmit = handleSubmit(async (values) => {
  registerFailed.value = '';
  // register API呼び出し
  try {
    const response = await registerNewUser(values.loginId, values.password);
    if (response.data) {
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
