import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import userStore from "@/store/modules/userStore";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    selected: {},
    },
  getters: {},
  mutations: {
    SEARCH_TRIP_LIST(state, payload) {
      state.selected = payload;
    },
  },
  actions: {
    searchTripList({ commit }, { typeselected, localselected, searchselected }) {
      console.log(typeselected);
      console.log(localselected);
      console.log(searchselected);

      commit("SEARCH_TRIP_LIST",{ typeselected, localselected, searchselected });
    },
  },

  modules: {
    userStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
