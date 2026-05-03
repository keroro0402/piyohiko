<template>
  <header class="header">
    <UserInfo block="header-user-info" />
    <Navi block="header-nav" />
  </header>
</template>

<script setup lang="ts">
import Navi from '~/components/Navi.vue';
import UserInfo from '~/components/UserInfo.vue';
</script>
<style lang="scss" scoped>
.header {
  text-align: end;
}
</style>
