<template>
  <nav :class="block">
    <NuxtLink v-for="link in links.navi" :key="link.key" :to="link.to" :class="{ current: isCurrent(link) }">{{ link.label }}</NuxtLink>
  </nav>
</template>

<script setup lang="ts">
import { links } from '~/constants/links';
import { useRoute } from 'vue-router';
import type { NaviItem } from '~/types/navi';

/* props 定義*/
const { block } = defineProps<{
  block: string;
}>();

/* route */
const route = useRoute();

/**
 * 現在のページかどうかを判定
 * @param {NaviItem} link
 * @returns {boolean}
 */
const isCurrent = (link: NaviItem): boolean => {
  if (link.key === 'home') return route.name === 'index';
  return route.name === link.key;
};
</script>
