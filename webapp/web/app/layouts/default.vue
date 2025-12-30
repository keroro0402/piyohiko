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
    <Footer />
  </div>
</template>

<script setup>
import Header from '~/components/Header.vue';
import Footer from '~/components/Footer.vue';
</script>
