/**
 * アプリ内数字定義
 *
 * @rules
 * - key は大文字 (半角英数字)。2 単語以上は _ で繋ぐスネークケースにする。
 */

export const FORM_RULES = {
  EMAIL: {
    MIN: 1,
    MAX: 256,
  },
  PASSWORD: {
    MIN: 1,
    MAX: 64,
  },
  SECRETCODE_LENGTH: 3,
} as const;

export const TIME = {
  SLEEP: 2000,
} as const;
