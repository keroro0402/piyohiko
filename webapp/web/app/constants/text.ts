/**
 * アプリ内文言定義
 *
 * @rules
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
  ERROR: {
    ERROR401: 'ログインに失敗しました。IDとパスワードを確認してください。',
    ERROR403: 'アクセスが拒否されました。必要な権限がない可能性があります。',
    ERROR500: 'サーバーエラーが発生しました。しばらくしてから再度お試しください。',
    UNKNOWNERROR: '不明なエラーが発生しました。しばらくしてから再度お試しください。',
  },
} as const;
