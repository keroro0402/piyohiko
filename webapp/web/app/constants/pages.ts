/**
 * アプリ共通ナビゲーション定義
 *
 * ルール:
 * - key は一意、label はリンクの表示テキスト、to は遷移先のルート
 * - 並び順 = 表示順
 * - to は pages に存在するルートのみ
 */
import { TEXT } from './text';
export const LINKS = {
  NAVI: [
    { key: 'home', label: TEXT.COMMON.HOME, to: '/' },
    { key: 'about', label: TEXT.COMMON.ABOUT, to: '/about' },
    { key: 'login', label: TEXT.COMMON.LOGIN, to: '/login' },
  ] as const,
} as const;

/**
 * ページごとのタイトルマッピング
 *
 * ルール:
 * - key = links.navi 配列の key 名（homeのみindexとする）
 * - 並び順 = links.navi 配列の要素順
 */
export const PAGE_TITLES = {
  index: LINKS.NAVI[0].label,
  about: LINKS.NAVI[1].label,
  login: LINKS.NAVI[2].label,
} as const;
