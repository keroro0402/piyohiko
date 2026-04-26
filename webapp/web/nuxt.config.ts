// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  runtimeConfig: {
    public: {
      apiUrl: process.env.API_URL || 'http://localhost:8080',
    },
  },
  app: {
    head: {
      /**
       * ページタイトルの固定値を設定
       * %s の部分が各ページ中の useHead(title : xxx) のxxxと入れ替わって表示される
       */
      titleTemplate: `%s | piyohiko`,
    },
  },
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },
  css: ['~/assets/styles/main.scss'],
  /**
   * ホットリロードがうまく機能しない時の解決策
   * OSからの変更通知する設定を100ms毎にviteがファイルを確認する設定に変更する
   *  */
  vite: {
    server: {
      watch: {
        usePolling: true,
        interval: 100,
      },
    },
  },
  modules: ['@nuxt/eslint', '@pinia/nuxt'],
});
