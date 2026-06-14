import type { ImageItem } from './image';

/**
 * swiperコンテンツ定義
 * 型エイリアスで定義
 */
export type ContentItem = {
  /** title用（文字列・変更不可） */
  title: string;

  /** category用（文字列・変更不可） */
  category: string;

  /** 画像のパス用（/images/... 形式・変更不可） */
  image: Record<string, ImageItem>;

  /** 主文用（文字列・変更不可） */
  description: string;
};
