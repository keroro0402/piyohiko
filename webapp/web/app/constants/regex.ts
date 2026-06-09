/**
 * アプリ内正規表現定義
 *
 * @rules
 * - key は大文字 (半角英数字)。2 単語以上は _ で繋ぐスネークケースにする。
 */

export const REGEX = {
  EMAIL: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
  PASSWORD: /^[a-zA-Z0-9]+$/,
  SECRETCODE: /^[0-9]{3}$/,
} as const;
