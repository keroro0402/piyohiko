<template>
  <div>
    <Header />
    <main>
      <!--
      <slot />
      親コンポーネント（app.vue）で<NuxtLayout>の中にあるコンポーネントを表示させるタグ
      -->
      <slot />
    </main>
  </div>
</template>

<script setup>
import Header from '~/components/Header.vue';
</script>
