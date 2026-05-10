<template>
  <main>
    <h1>Index page</h1>
    <img v-for="image in IMAGES.MAIN" :key="image.key" :src="image.path" :loading="image.loading" />
  </main>
</template>

<script setup lang="ts">
import { IMAGES } from '~/constants/images';
import { PAGE_TITLES } from '~/constants/pages';

definePageMeta({
  middleware: 'auth',
});

const route = useRoute();

useHead({
  title: PAGE_TITLES[route.name?.toString() || ''] ?? '',
});
</script>
