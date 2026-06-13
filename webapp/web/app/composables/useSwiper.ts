import { ref, onMounted } from 'vue';
import { Swiper } from 'swiper';
import { Navigation, Pagination, EffectCoverflow } from 'swiper/modules';
import type { SwiperOptions } from 'swiper/types';

import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';

export const useSwiper = (options: SwiperOptions = {}) => {
  const swiperRef = ref<HTMLElement | null>(null);
  const swiperInstance = ref<Swiper | null>(null);
  const currentIndex = ref(1);

  onMounted(() => {
    if (swiperRef.value) {
      swiperInstance.value = new Swiper(swiperRef.value, {
        modules: [Navigation, Pagination, EffectCoverflow],
        pagination: { el: '.swiper-pagination' },
        effect: 'coverflow',
        direction: 'horizontal',
        centeredSlides: true,
        loop: true,
        slidesPerView: 2,
        initialSlide: 0,
        coverflowEffect: {
          rotate: 0,
          stretch: 100,
          depth: 40,
          modifier: 1,
          slideShadows: false,
          scale: 0.5,
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
        ...options,
      });
    }
  });

  watch(swiperInstance, (newSwiper) => {
    if (!newSwiper) return;
    newSwiper.on('slideChange', () => {
      currentIndex.value = newSwiper.realIndex;
    });
  });

  return {
    swiperRef,
    currentIndex,
    swiper: swiperInstance,
  };
};
