<template>
  <h1>About page</h1>
</template>
<script setup lang="ts">
import { pageTitles } from '~/constants/pages';

definePageMeta({
  middleware: 'auth',
});

const route = useRoute();

useHead({
  title: pageTitles[route.name?.toString() || ''] ?? '',
});
</script>
