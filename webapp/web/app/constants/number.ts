/**
 * アプリ内数字定義
 *
 * @rules
 * - key は大文字
 */

export const FORM_RULES = {
  LOGIN_ID: {
    MIN: 4,
    MAX: 20,
  },
  PASSWORD: {
    MIN: 8,
    MAX: 64,
  },
} as const;
