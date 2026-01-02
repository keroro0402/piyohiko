/**
 * ナビゲーションリンク定義
 * 型エイリアスで定義
 */
export type NaviItem = {
  /** 一意なキー（英小文字・変更不可） */
  key: string;

  /** 表示用ラベル（英小文字・変更不可） */
  label: string;

  /** NuxtLink 用のパス（英小文字・変更不可） */
  to: string;
};
