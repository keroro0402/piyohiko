<template>
  <div ref="swiperRef" class="swiper">
    <div class="swiper-wrapper">
      <div v-for="image in IMAGES.HOME" :key="image.key" class="swiper-slide">
        <img class="slide-img" :src="image.path" :alt="image.alt" :loading="image.loading" @dblclick="handleImageClick" />
      </div>
    </div>
    <div class="swiper-pagination" />
    <div class="swiper-button-prev" />
    <div class="swiper-button-next" />
    <div class="swiper-scrollbar" />
  </div>
</template>
<script setup lang="ts">
import { watch } from 'vue';
import { IMAGES } from '~/constants/images';

/* 外部データ・状態管理（Storeや共通コンポーザブルの呼び出し） */
const { swiperRef, currentIndex } = useSwiper();

/* Emits でcurrentIndex を親に伝搬 */
const emit = defineEmits<{
  (e: 'currentIndex', index: number): void;
}>();

watch(currentIndex, (newIndex) => {
  emit('currentIndex', newIndex);
});

const handleImageClick = () => {
  const targetIndex = currentIndex.value;
  // ここに画像をclickしたらおきるイベントを書く
  // ここに画像をclickしたらおきるイベントを書く
  // ここに画像をclickしたらおきるイベントを書く
  console.log(targetIndex);
};
</script>
<style lang="scss" scoped>
.swiper {
  width: 70%;
}
.swiper-slide {
  text-align: center;
}
</style>
