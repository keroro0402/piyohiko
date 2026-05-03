<template>
  <div :class="block">
    <p class="user-name">{{ TEXT.COMMON.USERNAME }}：{{ userName }}</p>
  </div>
  <button class="logout-button" @click="handleLogout">{{ TEXT.COMMON.LOGOUT }}</button>
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
