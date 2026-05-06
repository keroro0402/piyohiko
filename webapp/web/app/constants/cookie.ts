/**
 * Cookieの設定定義
 *
 * ルール:
 * - key は大文字
 */

export const COOKIE_EXPIRATION = {
  REMEMBER_ME: 30 * 24 * 60 * 60 * 1000, // 30日（ミリ秒）
  DEFAULT: 24 * 60 * 60 * 1000, // 1日（ミリ秒）
} as const;
