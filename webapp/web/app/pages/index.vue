<template>
  <HomeSwiper />
</template>

<script setup lang="ts">
import { PAGE_TITLES } from '~/constants/pages';
import HomeSwiper from '~/components/HomeSwiper.vue';

definePageMeta({
  middleware: 'auth', // middleware/auth.ts で遷移を操作
});

const route = useRoute();
const pageKey = route.name?.toString() || '';
useHead({
  title: PAGE_TITLES[pageKey as keyof typeof PAGE_TITLES] ?? '',
});
</script>
