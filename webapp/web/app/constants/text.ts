/**
 * アプリ内文言定義
 *
 * @rules
 * - key は大文字 (半角英数字)。2 単語以上は _ で繋ぐスネークケースにする。
 * - 値 は半角英数字記号 (例:() ⭕️, （）❌ / 1 ⭕️, １ ❌)
 * - 階層 は 2 階層まで (参照時に . は 2 つまで)
 * - 句読点 は 「付けない」
 * - 改行 は 2 文以上の時は「\n」で改行する
 */

export const SECURITY_SUBJECT = '好きな食べ物';

export const TEXT = {
  COMMON: {
    HOME: 'HOME',
    ABOUT: 'ABOUT',
    LOGIN: 'LOGIN',
    SIGNUP: 'SIGNUP',
    PASSWORD_RESET: 'PASSWORD_RESET',
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
    ERROR_EMAIL_REQUIRED: 'メールアドレスを入力してください',
    ERROR_SECURITY_PHRASE_REQUIRED: `${SECURITY_SUBJECT}を入力してください`,
    ERROR_LOGIN_ID_MIN: (loginId_min: number) => `ログインIDは${loginId_min}文字以上で入力してください`,
    ERROR_LOGIN_ID_MAX: (loginId_max: number) => `ログインIDは${loginId_max}文字以内で入力してください`,
    ERROR_PASSWORD_MIN: (password_min: number) => `パスワードは${password_min}文字以上で入力してください`,
    ERROR_PASSWORD_MAX: (password_max: number) => `パスワードは${password_max}文字以内で入力してください`,
    ERROR_EMAIL_MIN: (email_min: number) => `メールアドレスは${email_min}文字以上で入力してください`,
    ERROR_EMAIL_MAX: (email_max: number) => `メールアドレスは${email_max}文字以内で入力してください`,
    ERROR_LOGIN_ID_ALPHANUMERIC: 'ログインIDは半角英数字のみで入力してください',
    ERROR_PASSWORD_ALPHANUMERIC: 'パスワードは半角英数字のみで入力してください',
    ERROR_EMAIL_FORMAT: 'メールアドレスの形式が正しくありません',
  },
  LOGIN: {
    LABEL: 'ログイン',
    REMEMBER_ME: 'ログイン状態を保持する',
    FORGOT_PASSWORD: 'パスワードを忘れた方はこちら',
    NEW_USER: 'アカウントをお持ちでない方はこちら',
  },
  SIGNUP: {
    LABEL: '新規登録',
    CONFIRM_PASSWORD_LABEL: 'パスワード（確認）',
    ERROR_REQUIRED_CONFIRM_PASSWORD: '確認用パスワードを入力してください',
    ERROR_PASSWORD_MISMATCH: 'パスワードが一致しません',
    EXISTING_USER: 'すでにアカウントをお持ちの方はこちら',
    SUCCESS_SIGNUP: '新規ユーザ登録が完了しました\nログイン画面に遷移します',
  },
  PASSWORD_RESET: {
    LABEL: 'パスワードの再設定',
    DESCRIPTION: 'ご登録済みのメールアドレスを入力してください\nパスワード再設定用のメールをお送りします',
    EMAIL_LABEL: 'メールアドレス',
    EMAIL_PLACEHOLDER: 'example@domain.com',
    BUTTON_SEND: 'PW再設定用メールを送信',
    SEND_SUCCESS: 'パスワード再設定メールを送信しました\nメールに記載されたURLから手続きを完了してください',
    NEW_PASSWORD_LABEL: '新しいパスワード',
    NEW_PASSWORD_CONFIRM_LABEL: '新しいパスワード（確認）',
    BUTTON_UPDATE: 'パスワードを更新',
    UPDATE_SUCCESS: 'パスワードの更新が完了しました',
    BACK_TO_LOGIN: 'ログイン画面へ戻る',
  },
  ERROR: {
    ERROR400: 'リクエストを処理できませんでした\n入力内容が正しいかご確認ください',
    ERROR401: 'ログインに失敗しました\nIDとパスワードを確認してください',
    ERROR403: 'アクセスが拒否されました\n必要な権限がない可能性があります',
    ERROR409: '登録済みのユーザーが存在します\n別のログインIDを使用してください',
    ERROR500: 'サーバーエラーが発生しました\nしばらくしてから再度お試しください',
    UNKNOWNERROR: '不明なエラーが発生しました\nしばらくしてから再度お試しください',
  },
} as const;
