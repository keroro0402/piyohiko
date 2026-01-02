import type { ImageItem } from '~/types/image';

/**
 * イメージ定義
 *
 * @rules
 * - key は一意
 * - 並び順 = 表示順
 */
export const images: {
  main: readonly ImageItem[];
} = {
  main: [
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
