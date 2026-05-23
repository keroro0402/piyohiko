<template>
  <div :class="block">
    <p :class="`${block}__user-name`">{{ userName }}</p>
    <button :class="`${block}__logout-button`" @click="logout">{{ TEXT.COMMON.LOGOUT }}</button>
  </div>
</template>

<script setup lang="ts">
import { useAuth } from '~/composables/useAuth';
import { TEXT } from '~/constants/text';

/* 外部から受け取るデータ（Props） */
const { block } = defineProps<{
  block: string;
}>();

/* 外部データ・状態管理（Storeや共通コンポーザブルの呼び出し） */
const userInfoStore = useUserInfoStore();
const { logout } = useAuth();

/* 画面で使う具体的な変数・状態（データの抽出や加工） */
const userName = userInfoStore.displayName;
</script>

<style lang="scss" scoped>
.header-user-info {
  &__user-name {
    margin: 0;
  }
  &__logout-button {
    margin-left: 10px;
    padding: 5px 10px;
    background-color: #f44336;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;

    &:hover {
      background-color: #d32f2f;
    }
  }
}
</style>
