/**
 * イメージ定義
 *
 * @rules
 * - key は一意で使用ページ名、alt は画像の内容を説明するテキスト、path は画像のパス、loading は画像の読み込み方法（eager または lazy）
 * - INDEX : 表示順 = 配列順
 */

const imageInfo = {
  INDEX: {
    swipe1: 'image1',
    swipe2: 'image2',
    swipe3: 'image3',
    swipe4: 'image4',
    swipe5: 'image5',
  },
  INDEX_DISCRIPTION: {
    level0: 'star0',
    level1: 'star1',
    level2: 'star2',
    level3: 'star3',
    level4: 'star4',
    level5: 'star5',
  },
} as const;

export const IMAGES = {
  INDEX: [
    {
      key: imageInfo.INDEX.swipe1,
      alt: 'ルフィ',
      path: `/images/${imageInfo.INDEX.swipe1}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.INDEX.swipe2,
      alt: 'ゾロ',
      path: `/images/${imageInfo.INDEX.swipe2}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.INDEX.swipe3,
      alt: 'クロコダイル',
      path: `/images/${imageInfo.INDEX.swipe3}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.INDEX.swipe4,
      alt: 'ナミ',
      path: `/images/${imageInfo.INDEX.swipe4}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.INDEX.swipe5,
      alt: 'ティーチ',
      path: `/images/${imageInfo.INDEX.swipe5}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
  ],
  INDEX_DISCRIPTION: [
    {
      key: imageInfo.INDEX_DISCRIPTION.level0,
      alt: '星0',
      path: `/images/${imageInfo.INDEX_DISCRIPTION.level0}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.INDEX_DISCRIPTION.level1,
      alt: '星1',
      path: `/images/${imageInfo.INDEX_DISCRIPTION.level1}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.INDEX_DISCRIPTION.level2,
      alt: '星2',
      path: `/images/${imageInfo.INDEX_DISCRIPTION.level2}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.INDEX_DISCRIPTION.level3,
      alt: '星3',
      path: `/images/${imageInfo.INDEX_DISCRIPTION.level3}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.INDEX_DISCRIPTION.level4,
      alt: '星4',
      path: `/images/${imageInfo.INDEX_DISCRIPTION.level4}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
    {
      key: imageInfo.INDEX_DISCRIPTION.level5,
      alt: '星5',
      path: `/images/${imageInfo.INDEX_DISCRIPTION.level5}.png`,
      loading: 'eager', // 画像読み込みは標準
    },
  ],
} as const;
