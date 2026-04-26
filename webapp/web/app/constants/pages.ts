/**
 * アプリ共通ナビゲーション定義
 *
 * ルール:
 * - key は一意
 * - 並び順 = 表示順
 * - to は pages に存在するルートのみ
 */
export const links = {
  navi: [
    { key: 'home', label: 'HOME', to: '/' },
    { key: 'about', label: 'ABOUT', to: '/about' },
    { key: 'login', label: 'LOGIN', to: '/login' },
  ] as const,
} as const;

/**
 * ページごとのタイトルマッピング
 *
 * ルール:
 * - key = links.navi 配列の key 名（homeのみindexとする）
 * - 並び順 = links.navi 配列の要素順
 */
export const pageTitles: Record<string, string> = {
  index: links.navi[0].label,
  about: links.navi[1].label,
  login: links.navi[2].label,
};
