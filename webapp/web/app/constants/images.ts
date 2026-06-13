import type { ImageItem } from '~/types/image';

/**
 * イメージ定義
 *
 * @rules
 * - key は一意で使用ページ名、alt は画像の内容を説明するテキスト、path は画像のパス、loading は画像の読み込み方法（eager または lazy）
 * - INDEX : 表示順 = 配列順
 */

const imageInfo = {
  image1: 'image1',
  image2: 'image2',
  image3: 'image3',
  image4: 'image4',
  image5: 'image5',
} as const;

export const IMAGES: {
  INDEX: readonly ImageItem[];
} = {
  INDEX: [
    {
      key: imageInfo.image1,
      alt: 'ルフィ',
      path: `/images/${imageInfo.image1}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.image2,
      alt: 'ゾロ',
      path: `/images/${imageInfo.image2}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.image3,
      alt: 'クロコダイル',
      path: `/images/${imageInfo.image3}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.image4,
      alt: 'ナミ',
      path: `/images/${imageInfo.image4}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.image5,
      alt: 'ティーチ',
      path: `/images/${imageInfo.image5}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
  ],
  // 型アサーションで定数と明示する
} as const;
