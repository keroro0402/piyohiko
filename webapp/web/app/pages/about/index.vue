<template>
  <h1>About page</h1>
</template>
<script setup lang="ts">
import { PAGE_TITLES } from '~/constants/pages';

definePageMeta({
  middleware: 'auth',
});

const route = useRoute();

useHead({
  title: PAGE_TITLES[route.name?.toString() || ''] ?? '',
});
</script>
