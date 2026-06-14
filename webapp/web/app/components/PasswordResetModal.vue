<template>
  <BaseModal :disable-overlay-click="true" @close="$emit('close')">
    <div class="password-reset-modal">
      <section class="password-reset-modal__content">
        <h1 class="password-reset-modal__title">{{ TEXT.PASSWORD_RESET_MODAL.LABEL }}</h1>
        <p v-if="passwordResetModalFailed" class="error-message">
          {{ passwordResetModalFailed }}
        </p>
        <p class="description">
          {{ TEXT.PASSWORD_RESET_MODAL.DESCRIPTION }}
        </p>
        <form :class="BLOCK_NAME" @submit.prevent="onSubmit">
          <FormGroupInput :id="FIELD.SECRETCODE" v-model="secretCode" v-bind="secretCodeProps" :errors="errors" :block="BLOCK_NAME" option="short" :text="TEXT.PASSWORD_RESET_MODAL.SECRETCODE_LABEL" minlength="1" :placeholder="TEXT.PASSWORD_RESET_MODAL.SECRETCODE_PLACEHOLDER" required />
          <FormGroupInput :id="FIELD.PASSWORD" v-model="password" v-bind="passwordProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.PASSWORD_RESET_MODAL.NEW_PASSWORD_LABEL" type="password" minlength="1" placeholder="test" required />
          <FormGroupInput :id="FIELD.CONFIRM_PASSWORD" v-model="confirmPassword" v-bind="confirmPasswordProps" :errors="errors" :block="BLOCK_NAME" :text="TEXT.PASSWORD_RESET_MODAL.NEW_PASSWORD_CONFIRM_LABEL" type="password" minlength="1" placeholder="test" required />
          <SubmitButton :block="BLOCK_NAME" :is-form-valid="isFormValid" :text="TEXT.PASSWORD_RESET_MODAL.BUTTON_UPDATE" />
        </form>
      </section>
    </div>
  </BaseModal>
</template>
<script setup lang="ts">
/* 外部ライブラリ（Vue本体やnpmパッケージ） */
import { ref, computed } from 'vue';
import { useForm } from 'vee-validate';
/* プロジェクト共通の仕組み（API、エラーハンドラー、汎用コンポーザブル） */
import { passwordReset } from '~/api/apiClient';
import { errorHandler } from '~/api/errorHandler';
import type { CustomAxiosError } from '~/types/customAxiosError';
/* プロジェクト共通の定数（マスターデータ系） */
import { TEXT } from '~/constants/text';
import { TIME } from '~/constants/number';
/* 子コンポーネント（画面を構成する部品） */
import FormGroupInput from '~/components/FormGroupInput.vue';
import SubmitButton from '~/components/SubmitButton.vue';

/* 当該ページ（パスワード変更モーダル画面）でのみ使用する定数（タイポ防止用） */
const BLOCK_NAME = 'password-reset-modal';
const FIELD = {
  SECRETCODE: 'secretCode',
  PASSWORD: 'password',
  CONFIRM_PASSWORD: 'confirmPassword',
};

/* 外部データ・状態管理（Storeや共通コンポーザブルの呼び出し） */
const { passwordResetModalSchema } = useAuthValidation();
const { showSuccessToast, showApiErrorToast } = useToast();

/* 外部から受け取るデータ（Props） */
const props = defineProps<{
  email: string;
}>();

/* 外部に出すデータ（Emits） */
defineEmits(['close']);

/* フォーム・バリデーション関連（VeeValidate）*/
const {
  defineField, // 入力欄の値をバリデーションと紐付ける関数
  errors, // リアルタイムのエラーメッセージが詰まったオブジェクト
  handleSubmit, // 送信時にバリデーションを実行する門番関数
  meta, // フォーム全体の検証状態（エラーの有無など）を持つオブジェクト
} = useForm({
  validationSchema: passwordResetModalSchema, // Zodで定義した検証ルール
  initialValues: {
    // 画面表示時の初期値（空文字で初期化）
    secretCode: '',
    password: '',
    confirmPassword: '',
  },
});
const [secretCode, secretCodeProps] = defineField('secretCode');
const [
  // password用のVeeValidate処理一式を定義（値と裏方のイベント設定を取得）
  password, // v-modelで双方バインディグされた値
  passwordProps, // 入力欄が動くために必要な「裏方のイベント設定」が入ったオブジェクト
] = defineField('password');
const [confirmPassword, confirmPasswordProps] = defineField('confirmPassword');
/* 画面独自のリアクティブな状態（ref / computed） */
const passwordResetModalFailed = ref('');
const isFormValid = computed(() => meta.value.valid && meta.value.dirty); // VeeValidateの結果がvalidにbooleanで入る

/* 送信などのアクション（関数・イベントハンドラー） */
const onSubmit = handleSubmit(async (values) => {
  passwordResetModalFailed.value = '';
  // passwordReset API呼び出し
  try {
    const response = await passwordReset(props.email, values.secretCode, values.password);
    if (response.data) {
      showSuccessToast(TEXT.PASSWORD_RESET_MODAL.UPDATE_SUCCESS); // トーストで通知
      await sleep(TIME.SLEEP); // ページ遷移を少し待つ
      await navigateTo('/login'); // ログインページへ遷移
    }
  } catch (error) {
    passwordResetModalFailed.value = errorHandler(error);
    showApiErrorToast(error as CustomAxiosError);
    return;
  }
});
</script>
<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;

.password-reset-modal {
  @include form-style;
  &__title {
    text-align: center;
    margin-bottom: 2rem;
    color: $color-dark-gray;
    font-weight: 600;
  }
}
</style>
