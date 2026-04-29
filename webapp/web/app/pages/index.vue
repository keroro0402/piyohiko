<template>
  <main>
    <h1>Index page</h1>
    <img v-for="image in images.main" :key="image.key" :src="image.path" :loading="image.loading" />
  </main>
</template>

<script setup lang="ts">
import { images } from '~/constants/images';
import { pageTitles } from '~/constants/pages';

definePageMeta({
  middleware: 'auth',
});

const route = useRoute();

useHead({
  title: pageTitles[route.name?.toString() || ''] ?? '',
});
</script>
