<template>
  <div class="password-reset-page">
    <section class="password-reset-page__content">
      <h1 class="password-reset-page__title">{{ TEXT.PASSWORD_RESET.LABEL }}</h1>
      <p v-if="passwordResetFailed" class="error-message">
        {{ passwordResetFailed }}
      </p>
      <p class="description">
        {{ TEXT.PASSWORD_RESET.DESCRIPTION }}
      </p>
      <form :class="BLOCK_NAME" @submit.prevent="onSubmit">
        <FormGroupInput :id="FIELD.EMAIL" v-model="email" v-bind="emailProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.FORM.EMAIL" :placeholder="TEXT.FORM.EMAIL_PLACEHOLDER" autocomplete="username" required />
        <SubmitButton :block="BLOCK_NAME" :is-form-valid="isFormValid" :text="TEXT.PASSWORD_RESET.BUTTON_SEND" />
        <div :class="`${BLOCK_NAME}__switch-page`">
          <NuxtLink :class="`${BLOCK_NAME}__switch-link`" :to="LINKS.TEXT.LOGIN">{{ TEXT.SIGNUP.EXISTING_USER }}</NuxtLink>
        </div>
      </form>
      <PasswordResetModal v-if="isModalOpen" :email="email!" @close="isModalOpen = false" />
    </section>
  </div>
</template>

<script setup lang="ts">
/* 外部ライブラリ（Vue本体やnpmパッケージ） */
import { ref, computed } from 'vue';
import { useForm } from 'vee-validate';
/* プロジェクト共通の仕組み（API、エラーハンドラー、汎用コンポーザブル） */
import { requestPasswordReset } from '~/api/apiClient';
import { errorHandler } from '~/api/errorHandler';
import type { CustomAxiosError } from '~/types/customAxiosError';
/* プロジェクト共通の定数（マスターデータ系） */
import { PAGE_TITLES, LINKS } from '~/constants/pages';
import { TEXT } from '~/constants/text';
/* 子コンポーネント（画面を構成する部品） */
import FormGroupInput from '~/components/FormGroupInput.vue';
import SubmitButton from '~/components/SubmitButton.vue';
import PasswordResetModal from '~/components/PasswordResetModal.vue';

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
const BLOCK_NAME = 'password-reset-form';
const FIELD = {
  EMAIL: 'email',
};

/* 外部データ・状態管理（Storeや共通コンポーザブルの呼び出し） */
const { passwordResetSchema } = useAuthValidation();
const { showSuccessToast, showApiErrorToast } = useToast();

/* フォーム・バリデーション関連（VeeValidate）*/
const {
  defineField, // 入力欄の値をバリデーションと紐付ける関数
  errors, // リアルタイムのエラーメッセージが詰まったオブジェクト
  handleSubmit, // 送信時にバリデーションを実行する門番関数
  meta, // フォーム全体の検証状態（エラーの有無など）を持つオブジェクト
} = useForm({
  validationSchema: passwordResetSchema, // Zodで定義した検証ルール
  initialValues: {
    // 画面表示時の初期値（空文字で初期化）
    email: '',
  },
});
const [
  // email用のVeeValidate処理一式を定義（値と裏方のイベント設定を取得）
  email, // v-modelで双方バインディグされた値
  emailProps, // 入力欄が動くために必要な「裏方のイベント設定」が入ったオブジェクト
] = defineField('email');
/* 画面独自のリアクティブな状態（ref / computed） */
const passwordResetFailed = ref('');
const isModalOpen = ref(false);
const isFormValid = computed(() => meta.value.valid && meta.value.dirty); // VeeValidateの結果がvalidにbooleanで入る

/* 送信などのアクション（関数・イベントハンドラー） */
const onSubmit = handleSubmit(async (values) => {
  passwordResetFailed.value = '';
  // passwordReset API呼び出し
  try {
    const response = await requestPasswordReset(values.email);
    if (response.data) {
      showSuccessToast(TEXT.PASSWORD_RESET.SEND_SUCCESS); // トーストで通知
      isModalOpen.value = true;
    }
  } catch (error) {
    showApiErrorToast(error as CustomAxiosError);
    passwordResetFailed.value = errorHandler(error);
    return;
  }
});
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;

.password-reset-page {
  @include auth-page-layout($color-light-purple);
}
.password-reset-form {
  &__switch-page {
    text-align: center;
    margin-top: 1rem;
  }
}
</style>
