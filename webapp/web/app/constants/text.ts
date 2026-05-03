/**
 * アプリ内文言定義
 *
 * ルール:
 * - key は大文字
 */

export const TEXT = {
  COMMON: {
    HOME: 'HOME',
    ABOUT: 'ABOUT',
    LOGIN: 'LOGIN',
    LOGOUT: 'LOGOUT',
    USERNAME: 'ユーザー名',
  },
  LOGIN: {
    LOGINLABEL: 'ログイン',
    LOGINIDLABEL: 'ログインID',
    PASSWORDLABEL: 'パスワード',
    REMEMBERME: 'ログイン状態を保持する',
    FORGOTPASSWORD: 'パスワードを忘れた方はこちら',
  },
} as const;
