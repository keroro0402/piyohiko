<template>
  <div :class="block">
    <p :class="`${block}__user-name`">{{ userName }}</p>
    <button :class="`${block}__logout-button`" @click="handleLogout">{{ TEXT.COMMON.LOGOUT }}</button>
  </div>
</template>

<script setup lang="ts">
import { TEXT } from '~/constants/text';
import { useUserInfoStore } from '#imports';

const { block } = defineProps<{
  block: string;
}>();

const userInfoStore = useUserInfoStore();
const userName = computed(() => userInfoStore.userName || 'ゲスト');

const handleLogout = () => {
  const cookie = useCookie('accessToken');
  cookie.value = null; // クッキーを削除
  navigateTo('/login'); // ログインページへリダイレクト
};
</script>
<style lang="scss" scoped>
.header-user-info {
  &__user-name {
    margin: 0;
  }
  &__logout-button {
    margin-left: 10px;
    padding: 5px 10px;
    background-color: #f44336;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;

    &:hover {
      background-color: #d32f2f;
    }
  }
}
</style>
