<template>
  <!--
  <NuxtLayout>
    layouts/ のレイアウトコンポーネント を呼び出すタグ
    name属性の指定がない、name属性指定のvueがない場合は layouts/default.vue を呼び出す
  -->
  <NuxtLayout>
    <!--
    <NuxtPage />
    pages/ のページコンポーネント を表示するタグ
    / ならpages/index.vue が表示され、/about なら pages/about.vue が表示される 
    -->
    <NuxtPage />
  </NuxtLayout>
</template>
