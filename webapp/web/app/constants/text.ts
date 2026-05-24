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
    REGISTER: 'REGISTER',
    LOGOUT: 'LOGOUT',
    USERNAME: 'ユーザー名',
    GUEST: 'ゲスト',
  },
  FORM: {
    LOGINID: 'ログインID',
    PASSWORD: 'パスワード',
    REQUIRED: '必須項目です',
    ERROR_REQUIRED_LOGIN_ID: 'ログインIDを入力してください',
    ERROR_REQUIRED_PASSWORD: 'パスワードを入力してください',
  },
  LOGIN: {
    LABEL: 'ログイン',
    REMEMBER_ME: 'ログイン状態を保持する',
    FORGOT_PASSWORD: 'パスワードを忘れた方はこちら',
  },
  REGISTER: {
    LABEL: '新規登録',
    CONFIRM_PASSWORD_LABEL: 'パスワード（確認）',
    ERROR_REQUIRED_CONFIRM_PASSWORD: '確認用パスワードを入力してください',
  },
  ERROR: {
    ERROR401: 'ログインに失敗しました。IDとパスワードを確認してください。',
    ERROR403: 'アクセスが拒否されました。必要な権限がない可能性があります。',
    ERROR500: 'サーバーエラーが発生しました。しばらくしてから再度お試しください。',
    UNKNOWNERROR: '不明なエラーが発生しました。しばらくしてから再度お試しください。',
  },
} as const;
