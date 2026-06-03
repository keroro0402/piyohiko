import { TEXT } from './text';

/**
 * アプリ共通ナビゲーション定義
 *
 * @rules
 * - key は一意、label はリンクの表示テキスト、to は遷移先のルート
 * - 並び順 = 表示順
 * - to は pages に存在するルートのみ
 */

const PATH = {
  TOP: '/',
  ABOUT: '/about',
  LOGIN: '/login',
  SIGNUP: '/signup',
};

export const LINKS = {
  NAVI: [
    { key: 'home', label: TEXT.COMMON.HOME, to: PATH.TOP },
    { key: 'about', label: TEXT.COMMON.ABOUT, to: PATH.ABOUT },
    { key: 'login', label: TEXT.COMMON.LOGIN, to: PATH.LOGIN },
    { key: 'signup', label: TEXT.COMMON.SIGNUP, to: PATH.SIGNUP },
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
  index: LINKS.NAVI[0].label,
  about: LINKS.NAVI[1].label,
  login: LINKS.NAVI[2].label,
  signup: LINKS.NAVI[3].label,
} as const;
