import { TEXT } from './text';

/**
 * アプリ共通ナビゲーション定義
 *
 * @rules
 * - 並び順 = 表示順
 * - LINKS.NAVI の key は一意、label はリンクの表示テキスト、to は遷移先のルート
 * - LINKS.NAVI の key の value は複数単語の場合は キャメルケース で指定
 * - to は pages に存在するルートのみ
 * - PATH[key]、LINKS.NAVI[].key、PAGE_TITLES[key] は 一致させること（例: PATH.TOP, LINKS.NAVI[0].key, PAGE_TITLES.index）
 */

const PATH = {
  TOP: '/',
  ABOUT: '/about',
  LOGIN: '/login',
  SIGNUP: '/signup',
  PASSWORD_RESET: '/password-reset',
} as const;

export const LINKS = {
  NAVI: [
    { key: 'home', label: TEXT.COMMON.HOME, to: PATH.TOP },
    { key: 'about', label: TEXT.COMMON.ABOUT, to: PATH.ABOUT },
    { key: 'login', label: TEXT.COMMON.LOGIN, to: PATH.LOGIN },
    { key: 'signup', label: TEXT.COMMON.SIGNUP, to: PATH.SIGNUP },
    { key: 'password-reset', label: TEXT.COMMON.PASSWORD_RESET, to: PATH.PASSWORD_RESET },
  ] as const,
  TEXT: PATH,
} as const;

/**
 * ページごとのタイトルマッピング
 *
 * @rules
 * - key = LINKS.NAVI 配列の key 名（Nuxtの route.name と一致させる。homeのみ index とする例外あり）
 * - 各定数の紐づき = URL、LINKS.NAVIのkey、PAGE_TITLESのkey を揃えること
 * （例: '/about' に対して about / about）
 * （例: '/password-reset' に対して 'password-reset' / 'password-reset'）
 * - 並び順 = links.navi 配列の要素順
 */
export const PAGE_TITLES = {
  index: LINKS.NAVI[0].label, // TOPページのroute.nameが index になるので TOP のキーは index
  about: LINKS.NAVI[1].label,
  login: LINKS.NAVI[2].label,
  signup: LINKS.NAVI[3].label,
  'password-reset': LINKS.NAVI[4].label,
} as const;
