import type { ImageItem } from '~/types/image';

/**
 * イメージ定義
 *
 * @rules
 * - key は一意、alt は画像の内容を説明するテキスト、path は画像のパス、loading は画像の読み込み方法（eager または lazy）
 * - 並び順 = 表示順
 */
export const IMAGES: {
  MAIN: readonly ImageItem[];
} = {
  MAIN: [
    {
      key: 'image1',
      alt: 'ルフィ',
      path: '/images/image1.png',
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: 'image2',
      alt: 'ゾロ',
      path: '/images/image2.png',
      loading: 'eager', // 画像読み込みは標準
    },
  ],
  // 型アサーションで定数と明示する
} as const;
