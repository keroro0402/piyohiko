/**
 * アプリ内文言定義
 *
 * @rules
 * - key は大文字 (半角英数字)。2 単語以上は _ で繋ぐスネークケースにする。
 * - 値 は半角英数字記号 (例:() ⭕️, （）❌ / 1 ⭕️, １ ❌)
 * - 階層 は 2 階層まで (参照時に . は 2 つまで)
 * - 句読点 は 1 文の時は「付けない」、2 文以上の時は「付ける」
 */

export const SECURITY_SUBJECT = '好きな食べ物';

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
    ERROR_LOGIN_ID_REQUIRED: 'ログインIDを入力してください',
    ERROR_PASSWORD_REQUIRED: 'パスワードを入力してください',
    ERROR_SECURITY_PHRASE_REQUIRED: `${SECURITY_SUBJECT}を入力してください`,
    ERROR_LOGIN_ID_MIN: (loginId_min: number) => `ログインIDは${loginId_min}文字以上で入力してください`,
    ERROR_LOGIN_ID_MAX: (loginId_max: number) => `ログインIDは${loginId_max}文字以内で入力してください`,
    ERROR_PASSWORD_MIN: (password_min: number) => `パスワードは${password_min}文字以上で入力してください`,
    ERROR_PASSWORD_MAX: (password_max: number) => `パスワードは${password_max}文字以内で入力してください`,
    ERROR_LOGIN_ID_ALPHANUMERIC: 'ログインIDは半角英数字のみで入力してください',
    ERROR_PASSWORD_ALPHANUMERIC: 'パスワードは半角英数字のみで入力してください',
  },
  LOGIN: {
    LABEL: 'ログイン',
    REMEMBER_ME: 'ログイン状態を保持する',
    FORGOT_PASSWORD: 'パスワードを忘れた方はこちら',
    NEW_USER: 'アカウントをお持ちでない方はこちら',
  },
  REGISTER: {
    LABEL: '新規登録',
    CONFIRM_PASSWORD_LABEL: 'パスワード（確認）',
    ERROR_REQUIRED_CONFIRM_PASSWORD: '確認用パスワードを入力してください',
    ERROR_PASSWORD_MISMATCH: 'パスワードが一致しません',
    EXISTING_USER: 'すでにアカウントをお持ちの方はこちら',
  },
  ERROR: {
    ERROR400: 'リクエストを処理できませんでした。入力内容が正しいかご確認ください。',
    ERROR401: 'ログインに失敗しました。IDとパスワードを確認してください。',
    ERROR403: 'アクセスが拒否されました。必要な権限がない可能性があります。',
    ERROR409: '登録済みのユーザーが存在します。別のログインIDを使用してください。',
    ERROR500: 'サーバーエラーが発生しました。しばらくしてから再度お試しください。',
    UNKNOWNERROR: '不明なエラーが発生しました。しばらくしてから再度お試しください。',
  },
} as const;
