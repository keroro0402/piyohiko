/**
 * アプリ内数字定義
 *
 * @rules
 * - key は大文字 (半角英数字)。2 単語以上は _ で繋ぐスネークケースにする。
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
  EMAIL: {
    MIN: 1,
    MAX: 256,
  },
} as const;

export const TIME = {
  SLEEP: 2000,
} as const;
