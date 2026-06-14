<template>
  <button :class="[`${block}__submit`, { disabled: !isFormValid }]" type="submit" :disabled="!isFormValid">
    {{ text }}
  </button>
</template>

<script setup lang="ts">
/* 外部から受け取るデータ（Props） */
const { block, text, isFormValid } = defineProps<{
  block: string;
  text: string;
  isFormValid: boolean;
}>();
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;

.login-form,
.signup-form,
.password-reset-form,
.password-reset-modal {
  @include form-style;
}
</style>
