<template>
  <div class="main-inner">
    <HomeSwiper @current-index="activeIndex = $event" />
    <PageDescription :active-index="activeIndex" />
  </div>
</template>

<script setup lang="ts">
import { PAGE_TITLES } from '~/constants/pages';
import HomeSwiper from '~/components/HomeSwiper.vue';
import PageDescription from '~/components/PageDescription.vue';

definePageMeta({
  middleware: 'auth', // middleware/auth.ts で遷移を操作
});

const activeIndex = ref(0);

const route = useRoute();
const pageKey = route.name?.toString() || '';
useHead({
  title: PAGE_TITLES[pageKey as keyof typeof PAGE_TITLES] ?? '',
});
</script>
<style lang="scss" scoped>
.main-inner {
  display: flex;
}
</style>
