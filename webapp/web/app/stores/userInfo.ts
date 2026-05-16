import { defineStore } from 'pinia';
import { TEXT } from '~/constants/text';
import type { UserInfo } from '~/types/userInfo';

const useUserInfoStore = defineStore('userInfo', {
  state: (): UserInfo => ({
    userName: null,
    userRole: null,
  }),
  persist: true,
  getters: {
    displayName: (state) => {
      return state.userName || TEXT.COMMON.GUEST;
    },
  },
  actions: {
    setUserName(name: string) {
      this.userName = name;
    },
    setUserRole(role: string) {
      this.userRole = role;
    },
  },
});

export { useUserInfoStore };
