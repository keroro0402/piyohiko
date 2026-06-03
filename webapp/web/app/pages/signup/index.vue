<template>
  <main class="signup-page">
    <section class="signup-page__content">
      <h1 class="signup-page__title">{{ TEXT.SIGNUP.LABEL }}</h1>
      <p v-if="signupFailed" class="error-message">
        {{ signupFailed }}
      </p>
      <form :class="BLOCK_NAME" @submit.prevent="onSubmit">
        <FormGroupInput :id="FIELD.LOGIN_ID" v-model="loginId" v-bind="loginIdProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.FORM.LOGINID" placeholder="test" autocomplete="username" required />
        <FormGroupInput :id="FIELD.PASSWORD" v-model="password" v-bind="passwordProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.FORM.PASSWORD" type="password" minlength="1" placeholder="test" required />
        <FormGroupInput :id="FIELD.CONFIRM_PASSWORD" v-model="confirmPassword" v-bind="confirmPasswordProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.SIGNUP.CONFIRM_PASSWORD_LABEL" type="password" minlength="1" placeholder="test" required />
        <FormGroupInput :id="FIELD.SECURITY_PHRASE" v-model="securityPhrase" v-bind="securityPhraseProps" :block="BLOCK_NAME" :text="SECURITY_SUBJECT" placeholder="test" />
        <SubmitButton :block="BLOCK_NAME" :is-form-valid="isFormValid" :text="TEXT.SIGNUP.LABEL" />
        <div :class="`${BLOCK_NAME}__switch-page`">
          <NuxtLink :class="`${BLOCK_NAME}__switch-link`" :to="LINKS.TEXT.LOGIN">{{ TEXT.SIGNUP.EXISTING_USER }}</NuxtLink>
        </div>
      </form>
    </section>
  </main>
</template>

<script setup lang="ts">
/* 外部ライブラリ（Vue本体やnpmパッケージ） */
import { ref } from 'vue';
import { useForm } from 'vee-validate';
/* プロジェクト共通の仕組み（API、エラーハンドラー、汎用コンポーザブル） */
import { signupNewUser } from '~/api/apiClient';
import { errorHandler } from '~/api/errorHandler';
/* プロジェクト共通の定数（マスターデータ系） */
import { PAGE_TITLES, LINKS } from '~/constants/pages';
import { TEXT, SECURITY_SUBJECT } from '~/constants/text';
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

/* 当該ページ（新規登録画面）でのみ使用する定数（タイポ防止用） */
const BLOCK_NAME = 'signup-form';
const FIELD = {
  LOGIN_ID: 'loginId',
  PASSWORD: 'password',
  SECURITY_PHRASE: 'securityPhrase',
  CONFIRM_PASSWORD: 'confirmPassword',
};

/* 外部データ・状態管理（Storeや共通コンポーザブルの呼び出し） */
const { signupSchema } = useAuthValidation();

/* フォーム・バリデーション関連（VeeValidate）*/
const {
  defineField, // 入力欄の値をバリデーションと紐付ける関数
  errors, // リアルタイムのエラーメッセージが詰まったオブジェクト
  handleSubmit, // 送信時にバリデーションを実行する門番関数
  meta, // フォーム全体の検証状態（エラーの有無など）を持つオブジェクト
} = useForm({
  validationSchema: signupSchema, // Zodで定義した検証ルール
  initialValues: {
    // 画面表示時の初期値（空文字で初期化）
    loginId: '',
    password: '',
    confirmPassword: '',
    securityPhrase: '',
  },
});
const [
  // loginId用のVeeValidate処理一式を定義（値と裏方のイベント設定を取得）
  loginId, // v-modelで双方バインディグされた値
  loginIdProps, // 入力欄が動くために必要な「裏方のイベント設定」が入ったオブジェクト
] = defineField('loginId');
const [password, passwordProps] = defineField('password');
const [confirmPassword, confirmPasswordProps] = defineField('confirmPassword');
const [securityPhrase, securityPhraseProps] = defineField('securityPhrase');
/* 画面独自のリアクティブな状態（ref / computed） */
const signupFailed = ref('');
const isFormValid = computed(() => meta.value.valid && meta.value.dirty); // VeeValidateの結果がvalidにbooleanで入る

/* 送信などのアクション（関数・イベントハンドラー） */
const onSubmit = handleSubmit(async (values) => {
  signupFailed.value = '';
  // signup API呼び出し
  try {
    const response = await signupNewUser(values.loginId, values.password, values.securityPhrase ?? '');
    if (response.data) {
      await navigateTo('/'); // TOPページへ遷移
    }
  } catch (error) {
    signupFailed.value = errorHandler(error);
    return;
  }
});
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;
@use 'sass:color';

.signup-page {
  @include auth-page-layout($color-dark-green);
}
.signup-form {
  &__switch-page {
    text-align: center;
    margin-top: 1rem;
  }
}
</style>
