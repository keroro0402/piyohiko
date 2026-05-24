<template>
  <div :class="`${block}__group`">
    <label :class="`${block}__label`" :for="id">
      {{ text }}
    </label>
    <span v-if="props.required" :class="`${block}__required`">{{ TEXT.FORM.REQUIRED }}</span>
    <input :id="props.id" :value="props.modelValue" :class="`${props.block}__input`" :required="props.required" :placeholder="props.placeholder" :autocomplete="props.autocomplete" v-bind="$attrs" @input="updateValue" />
    <p v-if="errors[id]" class="error">{{ errors[id] }}</p>
  </div>
</template>

<script setup lang="ts">
import { TEXT } from '~/constants/text';
/**
 * 親コンポーネントから渡された属性を子コンポーネントに渡すための設定
 * v-bind="$attrs" と併用して、親コンポーネントから渡された属性を子コンポーネントのinput要素にバインドする
 */
defineOptions({ inheritAttrs: false });

/* 外部から受け取るデータ（Props） */
const props = withDefaults(
  defineProps<{
    block: string;
    text: string;
    placeholder?: string;
    id: string;
    autocomplete?: string;
    required?: boolean;
    modelValue: string | undefined;
    errors: Record<string, string | undefined>;
  }>(),
  {
    placeholder: '',
    autocomplete: 'off',
    required: false,
  },
);

/* 外部に出すデータ（Emits） */
const emit = defineEmits(['update:modelValue']);

/* inputイベントで親コンポーネントに値を渡す関数 */
const updateValue = (event: Event) => {
  const target = event.target as HTMLInputElement;
  emit('update:modelValue', target.value);
};
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;

.register-form,
.login-form {
  @include form-style;
}
</style>
