import { TEXT } from './text';

/**
 * アプリ共通ナビゲーション定義
 *
 * @rules
 * - 並び順 = 表示順
 * - LINKS.NAVI の key は一意、label はリンクの表示テキスト、to は遷移先のルート
 * - LINKS.NAVI の key の value は複数単語の場合は キャメルケース で指定
 * - to は pages に存在するルートのみ
 */

const PATH = {
  TOP: '/',
  ABOUT: '/about',
  LOGIN: '/login',
  SIGNUP: '/signup',
  PASSWORD_RESET: '/password-reset',
};

export const LINKS = {
  NAVI: [
    { key: 'home', label: TEXT.COMMON.HOME, to: PATH.TOP },
    { key: 'about', label: TEXT.COMMON.ABOUT, to: PATH.ABOUT },
    { key: 'login', label: TEXT.COMMON.LOGIN, to: PATH.LOGIN },
    { key: 'signup', label: TEXT.COMMON.SIGNUP, to: PATH.SIGNUP },
    { key: 'passwordReset', label: TEXT.COMMON.PASSWORD_RESET, to: PATH.PASSWORD_RESET },
  ] as const,
  TEXT: PATH,
} as const;

/**
 * ページごとのタイトルマッピング
 *
 * @rules
 * - key = links.navi 配列の key 名（homeのみindexとする）
 * - 並び順 = links.navi 配列の要素順
 */
export const PAGE_TITLES = {
  index: LINKS.NAVI[0].label, // TOPページのroute.nameが index になるので TOP のキーは index
  about: LINKS.NAVI[1].label,
  login: LINKS.NAVI[2].label,
  signup: LINKS.NAVI[3].label,
  password_reset: LINKS.NAVI[4].label,
} as const;
