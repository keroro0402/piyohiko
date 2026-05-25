import { TEXT } from './text';

/**
 * アプリ共通ナビゲーション定義
 *
 * @rules
 * - key は一意、label はリンクの表示テキスト、to は遷移先のルート
 * - 並び順 = 表示順
 * - to は pages に存在するルートのみ
 */
export const LINKS = {
  NAVI: [
    { key: 'home', label: TEXT.COMMON.HOME, to: '/' },
    { key: 'about', label: TEXT.COMMON.ABOUT, to: '/about' },
    { key: 'login', label: TEXT.COMMON.LOGIN, to: '/login' },
    { key: 'register', label: TEXT.COMMON.REGISTER, to: '/register' },
  ] as const,
  TEXT: {
    login: '/login',
    register: '/register',
  } as const,
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
  register: LINKS.NAVI[3].label,
} as const;
