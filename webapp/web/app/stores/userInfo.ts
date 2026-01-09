import { defineStore } from 'pinia';

export const useUserInfoStore = defineStore('userInfo', {
  state: () => ({
    topics: 1,
  }),
  getters: {
    db: (state) => {
      return state.topics * 2;
    },
  },
  actions: {
    increment() {
      this.topics++;
    },
  },
});
