import { defineStore } from 'pinia';
import type { UserInfo } from '~/types/userInfo';

const useUserInfoStore = defineStore('userInfo', {
  state: (): UserInfo => ({
    userName: null,
  }),
  getters: {
    displayName: (state) => {
      return state.userName || 'Guest';
    },
  },
  actions: {
    setUserName(name: string) {
      this.userName = name;
    },
  },
});

export { useUserInfoStore };
