import type { ContentItem } from '~/types/swiperContetnts';
import { TEXT } from '~/constants/text';
import { IMAGES } from '~/constants/images';
/**
 * swiperコンテンツ定義
 *
 * @rules
 * - key は大文字
 */
export const DESCRIPTION_CONTENTS = {
  HOME: [
    {
      title: TEXT.SPRING_CONTENTS.TITLE,
      category: TEXT.SPRING_CONTENTS.CATEGORY,
      description: TEXT.SPRING_CONTENTS.DESCRIPTION,
      image: {
        level: IMAGES.PAGE_DISCRIPTION[3],
      },
    },
    {
      title: TEXT.NUXT_CONTENTS.TITLE,
      category: TEXT.NUXT_CONTENTS.CATEGORY,
      description: TEXT.NUXT_CONTENTS.DESCRIPTION,
      image: {
        level: IMAGES.PAGE_DISCRIPTION[4],
      },
    },
  ],
} as const satisfies {
  [key: string]: readonly ContentItem[];
};
