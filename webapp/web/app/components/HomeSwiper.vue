<template>
  <div ref="swiperRef" class="swiper">
    <div class="swiper-wrapper">
      <div v-for="image in IMAGES.INDEX" :key="image.key" class="swiper-slide">
        <img class="slide-img" :src="image.path" :alt="image.alt" :loading="image.loading" />
      </div>
    </div>
    <div class="swiper-pagination" />
    <div class="swiper-button-prev" />
    <div class="swiper-button-next" />
    <div class="swiper-scrollbar" />
  </div>
</template>
<script setup lang="ts">
import { IMAGES } from '~/constants/images';
const { swiperRef } = useSwiper();
</script>
<style lang="scss" scoped>
.swiper {
  width: 70%;
}
.swiper-slide {
  text-align: center;
}
</style>
