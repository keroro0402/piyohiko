/**
 * イメージ定義
 * 型エイリアスで定義
 */
export type ImageItem = {
  /** キー用（英小文字・変更不可） */
  key: string;

  /** alt用（半角文字・変更不可） */
  alt: string;

  /** 画像のパス用（/images/... 形式・変更不可） */
  path: string;

  /** 画像の遅延読み込み用（lazy:遅延、eager:標準） */
  loading: 'lazy' | 'eager';
};
