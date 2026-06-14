import type { ContentItem } from '~/types/swiperContetnts';
import { TEXT } from '~/constants/text';
import { IMAGES } from '~/constants/images';
/**
 * swiperコンテンツ定義
 *
 * @rules
 * - key は大文字
 */
export const SWIPER_CONTENTS: {
  HOME: readonly ContentItem[];
} = {
  HOME: [
    {
      id: 0,
      title: TEXT.SWIPER_CONTENTS.TITLE,
      category: TEXT.SWIPER_CONTENTS.CATEGORY,
      image: {
        level: IMAGES.HOME_DISCRIPTION[3],
      },
      description: TEXT.SWIPER_CONTENTS.DESCRIPTION,
    },
  ],
} as const;
