<template>
  <main class="login-page">
    <section class="login-page__content">
      <h1>{{ TEXT.LOGIN.LOGINLABEL }}</h1>
      <form class="login-form" @submit.prevent="handleSubmit">
        <div>
          <label class="login-form__field">
            <span>{{ TEXT.LOGIN.MAILLABEL }}</span>
            <input v-model="email" type="email" required placeholder="example@mail.com" />
          </label>
        </div>
        <div>
          <label class="login-form__field">
            <span>{{ TEXT.LOGIN.PASSWORDLABEL }}</span>
            <input v-model="password" type="password" required minlength="1" placeholder="********" />
          </label>
        </div>
        <div>
          <button class="login-form__submit" type="submit">{{ TEXT.LOGIN.LOGINLABEL }}</button>
        </div>
      </form>
    </section>
  </main>
</template>

<script setup lang="ts">
import { TEXT } from '~/constants/text';
import { ref } from 'vue';
import apiClient from '~/api/apiClient';

definePageMeta({
  layout: 'blank',
});

useHead({
  title: 'LOGIN',
});

const email = ref('');
const password = ref('');

const handleSubmit = async () => {
  // TODO: 認証ロジックを実装
  const res = await apiClient.login.post('', { email: email.value, password: password.value });
  console.log(res.data);
  console.log('login submit', { email: email.value, password: password.value });
};
</script>

<style lang="scss" scoped>
@use '~/assets/styles/main.scss' as *;

.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: $color-orange;
  .login-form {
    background-color: $color-light-gray;
    padding: 2rem;
  }
}
</style>
