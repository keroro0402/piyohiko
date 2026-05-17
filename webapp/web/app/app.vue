<template>
  <NuxtLayout>
    <NuxtPage />
  </NuxtLayout>
</template>
<script setup lang="ts">
const visibility = useDocumentVisibility();
const userInfoStore = useUserInfoStore();
watch(visibility, (newValue) => {
  // ユーザー情報がない場合の処理は即リターン
  if (!userInfoStore.userName) {
    return;
  }
  // タブが非表示になったときの処理
  if (newValue === 'hidden') {
    console.log('Tab is now hidden');
    return;
  }
  // タブが表示されたときの処理
  console.log('ユーザがいるので、ここの処理を実行できます');
});
</script>
