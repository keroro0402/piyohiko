<template>
  <main class="login-page">
    <section class="login-page__content">
      <h1>{{ TEXT.LOGIN.LOGINLABEL }}</h1>
      <form class="login-form" @submit.prevent="handleSubmit">
        <div class="login-form__group">
          <label class="login-form__label" for="email">
            {{ TEXT.LOGIN.MAILLABEL }}
          </label>
          <input id="email" class="login-form__input" type="email" required placeholder="example@mail.com" />
        </div>
        <div class="login-form__group">
          <label class="login-form__label" for="password">
            {{ TEXT.LOGIN.PASSWORDLABEL }}
          </label>
          <input id="password" v-model="password" class="login-form__input" type="password" minlength="1" placeholder="********" />
        </div>
        <div class="login-form__check">
          <input id="rememberMe" v-model="rememberMe" class="login-form__checkbox" type="checkbox" />
          <label for="rememberMe">
            {{ TEXT.LOGIN.REMEMBERME }}
          </label>
        </div>

        <button class="login-form__submit" type="submit">{{ TEXT.LOGIN.LOGINLABEL }}</button>
        <div class="login-form__forgot-password">
          <a href="#">{{ TEXT.LOGIN.FORGOTPASSWORD }}</a>
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
const rememberMe = ref(false);

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
  background-color: $color-dark-brown;
  .login-page__content {
    max-width: 400px;
    margin: 0 auto;
    padding: 2rem;
    background: $color-light-gray;
    border-radius: 12px;
    box-shadow: 0 10px 30px $color-thin-black;
    h1 {
      text-align: center;
      margin-bottom: 2rem;
      color: $color-dark-gray;
      font-weight: 600;
    }
    .login-form {
      .login-form__group {
        margin-bottom: 1.5rem;
        .login-form__label {
          display: block;
          margin-bottom: 0.5rem;
          color: $color-dark-gray;
        }
        .login-form__input {
          width: 100%;
          box-sizing: border-box;
          padding: 0.5rem;
          border: 1px solid $color-white;
          border-radius: 4px;
        }
      }
      .login-form__check {
        display: flex;
        align-items: center;
        margin-bottom: 1.5rem;
        .login-form__checkbox {
          margin-right: 0.5rem;
        }
      }
      .login-form__submit {
        width: 100%;
        padding: 0.75rem;
        background-color: $color-warm-orange;
        color: $color-white;
        border: none;
        border-radius: 4px;
        font-size: 1rem;
        cursor: pointer;
        transition: background-color 0.3s ease;
        &:hover {
          background-color: darken($color-warm-orange, 10%);
        }
      }
      .login-form__forgot-password {
        text-align: center;
        margin-top: 1rem;
        a {
          color: $color-warm-orange;
          text-decoration: none;
          &:hover {
            text-decoration: underline;
          }
        }
      }
    }
  }
}
</style>
