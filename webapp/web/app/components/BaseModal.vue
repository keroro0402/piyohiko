<template>
  <div class="base-modal">
    <div class="base-modal__overlay" @click="$emit('close')" />
    <div class="base-modal__content">
      <button class="base-modal__close-btn" @click="$emit('close')">×</button>
      <slot />
    </div>
  </div>
</template>
<script setup lang="ts">
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
