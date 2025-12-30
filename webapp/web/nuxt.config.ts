// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
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
});
