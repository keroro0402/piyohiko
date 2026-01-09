import type { NaviItem } from '~/types/navi';

/**
 * アプリ共通ナビゲーション定義
 *
 * ルール:
 * - key は一意
 * - 並び順 = 表示順
 * - to は pages に存在するルートのみ
 */
export const links: {
  navi: readonly NaviItem[];
} = {
  navi: [
    {
      key: 'home',
      label: 'HOME',
      to: '/',
    },
    {
      key: 'about',
      label: 'ABOUT',
      to: '/about',
    },
  ],
  // 型アサーションで定数と明示する
} as const;

/**
 * ページごとのタイトルマッピング
 *
 * ルール:
 * - key = links.navi 配列の key 名（homeのみindexとする）
 * - 並び順 = links.navi 配列の要素順
 */
export const pageTitles: Record<string, string> = {
  index: 'HOME',
  about: 'ABOUT',
};
