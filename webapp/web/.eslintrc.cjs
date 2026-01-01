module.exports = {
  root: true,
  env: {
    browser: true,
    node: true,
    es2021: true,
  },
  parser: 'vue-eslint-parser', // Vue用パーサ
  parserOptions: {
    parser: '@typescript-eslint/parser', // TypeScript用パーサ
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  extends: [
    'eslint:recommended', // 基本ルール
    'plugin:vue/vue3-recommended', // Vue 3 向けルール
    'plugin:@typescript-eslint/recommended', // TypeScript ルール
    'plugin:prettier/recommended', // Prettier と統合
  ],
  rules: {
    'vue/multi-word-component-names': 'off',
    '@typescript-eslint/no-unused-vars': ['warn'],
  },
};
