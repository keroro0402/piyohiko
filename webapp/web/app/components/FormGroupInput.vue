<template>
  <div :class="`${block}__group`">
    <label :class="`${block}__label`" :for="id">
      {{ text }}
    </label>
    <input :id="props.id" :value="props.modelValue" :class="`${props.block}__input`" :required="props.required" :placeholder="props.placeholder" :autocomplete="props.autocomplete" v-bind="$attrs" @input="updateValue" />
  </div>
</template>

<script setup lang="ts">
/**
 * 親コンポーネントから渡された属性を子コンポーネントに渡すための設定
 * v-bind="$attrs" と併用して、親コンポーネントから渡された属性を子コンポーネントのinput要素にバインドする
 */
defineOptions({ inheritAttrs: false });

/* emit定義 */
const emit = defineEmits(['update:modelValue']);
/* inputイベントで親コンポーネントに値を渡す関数 */
const updateValue = (event: Event) => {
  const target = event.target as HTMLInputElement;
  emit('update:modelValue', target.value);
};

/* props 定義*/
const props = withDefaults(
  defineProps<{
    block: string;
    text: string;
    placeholder?: string;
    id: string;
    autocomplete?: string;
    required?: boolean;
    modelValue: string;
  }>(),
  {
    placeholder: '',
    autocomplete: 'off',
    required: false,
  },
);
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;
@use 'sass:color';

.register-form {
  &__group {
    margin-bottom: 1.5rem;
  }
  &__label {
    display: block;
    margin-bottom: 0.5rem;
    color: $color-dark-gray;
  }
  &__input {
    width: 100%;
    box-sizing: border-box;
    padding: 0.5rem;
    border: 1px solid $color-white;
    border-radius: 4px;
  }
  &__check {
    display: flex;
    align-items: center;
    margin-bottom: 1.5rem;
  }
  &__checkbox {
    margin-right: 0.5rem;
  }
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
  }
  &__forgot-password {
    text-align: center;
    margin-top: 1rem;
  }
}
.login-form {
  &__group {
    margin-bottom: 1.5rem;
  }
  &__label {
    display: block;
    margin-bottom: 0.5rem;
    color: $color-dark-gray;
  }
  &__input {
    width: 100%;
    box-sizing: border-box;
    padding: 0.5rem;
    border: 1px solid $color-white;
    border-radius: 4px;
  }
  &__check {
    display: flex;
    align-items: center;
    margin-bottom: 1.5rem;
  }
  &__checkbox {
    margin-right: 0.5rem;
  }
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
