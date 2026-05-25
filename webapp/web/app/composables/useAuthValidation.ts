import { toTypedSchema } from '@vee-validate/zod';
import * as z from 'zod';
import { TEXT } from '~/constants/text';
import { FORM_RULES } from '~/constants/number';

export const useAuthValidation = () => {
  const loginSchema = toTypedSchema(
    z.object({
      loginId: z
        .string()
        .nonempty({ message: TEXT.FORM.ERROR_LOGIN_ID_REQUIRED }) // 必須チェック
        .min(FORM_RULES.LOGIN_ID.MIN, { message: TEXT.FORM.ERROR_LOGIN_ID_MIN(FORM_RULES.LOGIN_ID.MIN) }) // 最小文字数
        .max(FORM_RULES.LOGIN_ID.MAX, { message: TEXT.FORM.ERROR_LOGIN_ID_MAX(FORM_RULES.LOGIN_ID.MAX) }) // 最大文字数
        .regex(/^[a-zA-Z0-9]+$/, { message: TEXT.FORM.ERROR_LOGIN_ID_ALPHANUMERIC }), // 形式チェック

      password: z
        .string()
        .nonempty({ message: TEXT.FORM.ERROR_PASSWORD_REQUIRED }) // 必須チェック
        .min(FORM_RULES.PASSWORD.MIN, { message: TEXT.FORM.ERROR_PASSWORD_MIN(FORM_RULES.PASSWORD.MIN) }) // 最小文字数
        .max(FORM_RULES.PASSWORD.MAX, { message: TEXT.FORM.ERROR_PASSWORD_MAX(FORM_RULES.PASSWORD.MAX) }) // 最大文字数
        .regex(/^[a-zA-Z0-9]+$/, { message: TEXT.FORM.ERROR_PASSWORD_ALPHANUMERIC }), // 形式チェック
    }),
  );
  const registerSchema = toTypedSchema(
    z
      .object({
        loginId: z
          .string()
          .nonempty({ message: TEXT.FORM.ERROR_LOGIN_ID_REQUIRED }) // 必須チェック
          .min(FORM_RULES.LOGIN_ID.MIN, { message: TEXT.FORM.ERROR_LOGIN_ID_MIN(FORM_RULES.LOGIN_ID.MIN) }) // 最小文字数
          .max(FORM_RULES.LOGIN_ID.MAX, { message: TEXT.FORM.ERROR_LOGIN_ID_MAX(FORM_RULES.LOGIN_ID.MAX) }) // 最大文字数
          .regex(/^[a-zA-Z0-9]+$/, { message: TEXT.FORM.ERROR_LOGIN_ID_ALPHANUMERIC }), // 形式チェック

        password: z
          .string()
          .nonempty({ message: TEXT.FORM.ERROR_PASSWORD_REQUIRED }) // 必須チェック
          .min(FORM_RULES.PASSWORD.MIN, { message: TEXT.FORM.ERROR_PASSWORD_MIN(FORM_RULES.PASSWORD.MIN) }) // 最小文字数
          .max(FORM_RULES.PASSWORD.MAX, { message: TEXT.FORM.ERROR_PASSWORD_MAX(FORM_RULES.PASSWORD.MAX) }) // 最大文字数
          .regex(/^[a-zA-Z0-9]+$/, { message: TEXT.FORM.ERROR_PASSWORD_ALPHANUMERIC }), // 形式チェック

        confirmPassword: z
          .string()
          .nonempty({ message: TEXT.FORM.ERROR_PASSWORD_REQUIRED }) // 必須チェック
          .min(FORM_RULES.PASSWORD.MIN, { message: TEXT.FORM.ERROR_PASSWORD_MIN(FORM_RULES.PASSWORD.MIN) }) // 最小文字数
          .max(FORM_RULES.PASSWORD.MAX, { message: TEXT.FORM.ERROR_PASSWORD_MAX(FORM_RULES.PASSWORD.MAX) }) // 最大文字数
          .regex(/^[a-zA-Z0-9]+$/, { message: TEXT.FORM.ERROR_PASSWORD_ALPHANUMERIC }), // 形式チェック
      })
      .refine(
        (data) => data.password === data.confirmPassword, //  // エラー判定の条件式
        {
          message: TEXT.REGISTER.ERROR_PASSWORD_MISMATCH,
          path: ['confirmPassword'], // エラーをconfirmPasswordの欄に表示させる指定
        },
      ),
  );

  return {
    loginSchema,
    registerSchema,
  };
};
