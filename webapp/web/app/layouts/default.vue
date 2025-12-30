<template>
  <div>
    <Header />
    <main>
      <slot />
    </main>
    <!-- <Footer /> 必要なら追加 -->
  </div>
</template>

<script setup>
import Header from '~/components/Header.vue';
</script>
