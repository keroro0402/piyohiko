<template>
  <button :class="[`${block}__submit`, { disabled: !isFormValid }]" type="submit" :disabled="!isFormValid">
    {{ text }}
  </button>
</template>

<script setup lang="ts">
// 外部から受け取るデータ（Props）
const { block, text, isFormValid } = defineProps<{
  block: string;
  text: string;
  isFormValid: boolean;
}>();
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;
@use 'sass:color';

.login-form {
  &__submit {
    width: 100%;
    padding: 0.75rem;
    background-color: $color-warm-orange;
    color: $color-white;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
    &:hover {
      background-color: color.adjust($color-warm-orange, $lightness: -10%);
    }
    &.disabled {
      background-color: color.adjust($color-warm-orange, $lightness: 30%, $saturation: -40%);
      cursor: not-allowed;
    }
  }
  &__forgot-password {
    text-align: center;
    margin-top: 1rem;
  }
}
.register-form {
  &__submit {
    width: 100%;
    padding: 0.75rem;
    background-color: $color-warm-orange;
    color: $color-white;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
    &:hover {
      background-color: color.adjust($color-warm-orange, $lightness: -10%);
    }
    &.disabled {
      background-color: color.adjust($color-warm-orange, $lightness: 30%, $saturation: -40%);
      cursor: not-allowed;
    }
  }
  &__forgot-password {
    text-align: center;
    margin-top: 1rem;
  }
}
</style>
