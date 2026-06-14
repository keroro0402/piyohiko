<template>
  <div class="page-description">
    <div class="page-description__header">
      <span class="page-description__label">{{ TEXT.DESCRIPTION.TITLE }}</span>
      <h3 class="page-description__title">{{ activeContent.title }}</h3>
    </div>
    <div class="page-description__content">
      <span class="page-description__label">{{ TEXT.DESCRIPTION.CATEGORY }}</span>
      <p class="page-description__category">{{ activeContent.category }}</p>
    </div>
    <div class="page-description__content">
      <span class="page-description__label">{{ TEXT.DESCRIPTION.LEVEL }}</span>
      <p class="page-description__level">
        <img :src="activeContent.image.level.path" :alt="activeContent.image.level.alt" :loading="activeContent.image.level.loading" />
      </p>
    </div>
    <div class="page-description__content">
      <span class="page-description__label">{{ TEXT.DESCRIPTION.DESCRIPTION }}</span>
      <p class="page-description__description">{{ activeContent.description }}</p>
    </div>
  </div>
</template>
<script setup lang="ts">
import { computed } from 'vue';
import { DESCRIPTION_CONTENTS } from '~/constants/descreption-contents';
import { TEXT } from '~/constants/text';

/* 外部から受け取るデータ（Props） */
const props = defineProps<{
  activeIndex: number;
}>();

const activeContent = computed(() => {
  return (
    DESCRIPTION_CONTENTS.HOME[props.activeIndex] ?? {
      title: '',
      category: '',
      description: '',
      image: {
        level: {
          key: '',
          alt: '',
          path: '',
          loading: 'eager' as const,
        },
      },
    }
  );
});
</script>
<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;
.page-description {
  width: 30%;
  display: flex;
  flex-direction: column;
  gap: 1.5rem; // 各ブロック（行）の間の余白
  padding: 1.5rem;
  background-color: $color-light-gray;
  border-radius: 8px;

  &__header,
  &__content {
    display: flex;
    gap: 0.5rem;
    flex-direction: row;
    align-items: flex-start; // 上揃え
    gap: 0rem; // ラベルと中身の間の余白
  }

  &__label {
    font-size: 0.875rem;
    font-weight: bold;
    color: $color-dark-gray;
    width: 80px;
    flex-shrink: 0; // 画面が狭くなってもラベルが潰れないように固定
    padding-top: 0.25rem; // 横のテキストと高さを合わせる微調整
  }

  &__title {
    font-size: 1.5rem;
    font-weight: 700;
    color: $color-dark-black;
    margin: 0;
  }

  &__category {
    font-size: 1rem;
    color: $color-dark-black;
    margin: 0;
  }

  &__level {
    margin: 0;
    display: inline-flex;
    align-items: center;

    img {
      height: 24px;
      width: auto;
      object-fit: contain;
    }
  }

  &__description {
    font-size: 1rem;
    line-height: 1.6;
    color: $color-dark-black;
    margin: 0;
    white-space: pre-wrap;
  }
}
</style>
