<template>
  <nav>
    <NuxtLink v-for="link in links.navi" :key="link.key" :to="link.to">{{ link.label }}</NuxtLink>
  </nav>
</template>

<script setup lang="ts">
import { links } from '~/constants/links';
</script>
