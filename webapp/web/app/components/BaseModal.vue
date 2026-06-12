<template>
  <div class="base-modal">
    <div class="base-modal__overlay" @click="disableOverlayClick ? null : $emit('close')" />
    <div class="base-modal__content">
      <button class="base-modal__close-btn" @click="$emit('close')">×</button>
      <slot />
    </div>
  </div>
</template>
<script setup lang="ts">
defineProps({
  disableOverlayClick: {
    // モーダルのオーバレイクリックでモーダル開閉制御用（操作は子側のdisable-overlay-clickで行う。true : 閉じない、false: 閉じる）
    type: Boolean,
    default: false, // デフォルトは「画面外クリックで閉じる」
  },
});

/* 外部に出すデータ（Emits） */
defineEmits(['close']);
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;
@use 'sass:color';

.base-modal {
  @include modal-layout($color-white);
}
</style>
