<template>
  <nav>
    <NuxtLink v-for="link in links.navi" :key="link.key" :to="link.to">{{ link.label }}</NuxtLink>
  </nav>
</template>

<script setup>
import { links } from '~/constants/links.ts';
</script>
