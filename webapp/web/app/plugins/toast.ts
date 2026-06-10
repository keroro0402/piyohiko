import { defineNuxtPlugin } from '#app';
import Toast, { type PluginOptions, POSITION } from 'vue-toastification';
// 🎨 トーストの綺麗な見た目（CSS）を読み込む
import 'vue-toastification/dist/index.css';

export default defineNuxtPlugin((nuxtApp) => {
  // トーストの全体的な基本設定（デフォルト値）
  const options: PluginOptions = {
    // 画面のどこに出すか（今回は右上。CENTER や BOTTOM_RIGHT も選べる）
    position: POSITION.TOP_RIGHT,
    // トーストをクリックしたら閉じる
    closeOnClick: true,
    // バツボタンを表示する
    closeButton: 'button',
  };

  // Nuxtにトーストライブラリ使用を通知する
  nuxtApp.vueApp.use(Toast, options);
});
