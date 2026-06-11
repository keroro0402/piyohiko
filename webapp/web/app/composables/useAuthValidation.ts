import { toTypedSchema } from '@vee-validate/zod';
import * as z from 'zod';
import { TEXT } from '~/constants/text';
import { FORM_RULES } from '~/constants/number';
import { REGEX } from '~/constants/regex';

export const useAuthValidation = () => {
  const loginSchema = toTypedSchema(
    z.object({
      email: z
        .string()
        .nonempty({ message: TEXT.FORM.ERROR_EMAIL_REQUIRED }) // 必須チェック
        .min(FORM_RULES.EMAIL.MIN, { message: TEXT.FORM.ERROR_EMAIL_MIN(FORM_RULES.EMAIL.MIN) }) // 最小文字数
        .max(FORM_RULES.EMAIL.MAX, { message: TEXT.FORM.ERROR_EMAIL_MAX(FORM_RULES.EMAIL.MAX) }) // 最大文字数
        .regex(REGEX.EMAIL, { message: TEXT.FORM.ERROR_EMAIL_FORMAT }), // 形式チェック

      password: z
        .string()
        .nonempty({ message: TEXT.FORM.ERROR_PASSWORD_REQUIRED }) // 必須チェック
        .min(FORM_RULES.PASSWORD.MIN, { message: TEXT.FORM.ERROR_PASSWORD_MIN(FORM_RULES.PASSWORD.MIN) }) // 最小文字数
        .max(FORM_RULES.PASSWORD.MAX, { message: TEXT.FORM.ERROR_PASSWORD_MAX(FORM_RULES.PASSWORD.MAX) }) // 最大文字数
        .regex(REGEX.PASSWORD, { message: TEXT.FORM.ERROR_PASSWORD_ALPHANUMERIC }), // 形式チェック
    }),
  );
  const signupSchema = toTypedSchema(
    z
      .object({
        email: z
          .string()
          .nonempty({ message: TEXT.FORM.ERROR_EMAIL_REQUIRED }) // 必須チェック
          .min(FORM_RULES.EMAIL.MIN, { message: TEXT.FORM.ERROR_EMAIL_MIN(FORM_RULES.EMAIL.MIN) }) // 最小文字数
          .max(FORM_RULES.EMAIL.MAX, { message: TEXT.FORM.ERROR_EMAIL_MAX(FORM_RULES.EMAIL.MAX) }) // 最大文字数
          .regex(REGEX.EMAIL, { message: TEXT.FORM.ERROR_EMAIL_FORMAT }), // 形式チェック

        password: z
          .string()
          .nonempty({ message: TEXT.FORM.ERROR_PASSWORD_REQUIRED }) // 必須チェック
          .min(FORM_RULES.PASSWORD.MIN, { message: TEXT.FORM.ERROR_PASSWORD_MIN(FORM_RULES.PASSWORD.MIN) }) // 最小文字数
          .max(FORM_RULES.PASSWORD.MAX, { message: TEXT.FORM.ERROR_PASSWORD_MAX(FORM_RULES.PASSWORD.MAX) }) // 最大文字数
          .regex(REGEX.PASSWORD, { message: TEXT.FORM.ERROR_PASSWORD_ALPHANUMERIC }), // 形式チェック

        confirmPassword: z
          .string()
          .nonempty({ message: TEXT.FORM.ERROR_REQUIRED_CONFIRM_PASSWORD }) // 必須チェック
          .min(FORM_RULES.PASSWORD.MIN, { message: TEXT.FORM.ERROR_PASSWORD_MIN(FORM_RULES.PASSWORD.MIN) }) // 最小文字数
          .max(FORM_RULES.PASSWORD.MAX, { message: TEXT.FORM.ERROR_PASSWORD_MAX(FORM_RULES.PASSWORD.MAX) }) // 最大文字数
          .regex(REGEX.PASSWORD, { message: TEXT.FORM.ERROR_PASSWORD_ALPHANUMERIC }), // 形式チェック

        securityPhrase: z.string().optional(),
      })
      .refine(
        (data) => data.password === data.confirmPassword, //  // エラー判定の条件式
        {
          message: TEXT.FORM.ERROR_PASSWORD_MISMATCH,
          path: ['confirmPassword'], // エラーをconfirmPasswordの欄に表示させる指定
        },
      ),
  );

  const passwordResetSchema = toTypedSchema(
    z.object({
      email: z
        .string()
        .nonempty({ message: TEXT.FORM.ERROR_EMAIL_REQUIRED }) // 必須チェック
        .min(FORM_RULES.EMAIL.MIN, { message: TEXT.FORM.ERROR_EMAIL_MIN(FORM_RULES.EMAIL.MIN) }) // 最小文字数
        .max(FORM_RULES.EMAIL.MAX, { message: TEXT.FORM.ERROR_EMAIL_MAX(FORM_RULES.EMAIL.MAX) }) // 最大文字数
        .regex(REGEX.EMAIL, { message: TEXT.FORM.ERROR_EMAIL_FORMAT }), // 形式チェック
    }),
  );

  const passwordResetModalSchema = toTypedSchema(
    z
      .object({
        secretCode: z
          .string()
          .nonempty({ message: TEXT.PASSWORD_RESET_MODAL.ERROR_SECRETCODE_REQUIRED }) // 必須チェック
          .regex(REGEX.SECRETCODE, { message: TEXT.PASSWORD_RESET_MODAL.ERROR_SECRETCODE(FORM_RULES.SECRETCODE_LENGTH) }), // 形式チェック

        password: z
          .string()
          .nonempty({ message: TEXT.FORM.ERROR_PASSWORD_REQUIRED }) // 必須チェック
          .min(FORM_RULES.PASSWORD.MIN, { message: TEXT.FORM.ERROR_PASSWORD_MIN(FORM_RULES.PASSWORD.MIN) }) // 最小文字数
          .max(FORM_RULES.PASSWORD.MAX, { message: TEXT.FORM.ERROR_PASSWORD_MAX(FORM_RULES.PASSWORD.MAX) }) // 最大文字数
          .regex(REGEX.PASSWORD, { message: TEXT.FORM.ERROR_PASSWORD_ALPHANUMERIC }), // 形式チェック

        confirmPassword: z
          .string()
          .nonempty({ message: TEXT.FORM.ERROR_REQUIRED_CONFIRM_PASSWORD }) // 必須チェック
          .min(FORM_RULES.PASSWORD.MIN, { message: TEXT.FORM.ERROR_PASSWORD_MIN(FORM_RULES.PASSWORD.MIN) }) // 最小文字数
          .max(FORM_RULES.PASSWORD.MAX, { message: TEXT.FORM.ERROR_PASSWORD_MAX(FORM_RULES.PASSWORD.MAX) }) // 最大文字数
          .regex(REGEX.PASSWORD, { message: TEXT.FORM.ERROR_PASSWORD_ALPHANUMERIC }), // 形式チェック
      })
      .refine(
        (data) => data.password === data.confirmPassword, //  // エラー判定の条件式
        {
          message: TEXT.FORM.ERROR_PASSWORD_MISMATCH,
          path: ['confirmPassword'], // エラーをconfirmPasswordの欄に表示させる指定
        },
      ),
  );

  return {
    loginSchema,
    signupSchema,
    passwordResetSchema,
    passwordResetModalSchema,
  };
};
