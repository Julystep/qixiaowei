import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      userId: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).userId,
      loginName: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).loginName,
      userName: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).userName,
      telephone: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).telephone,
      sex: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).sex,
      gender: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).gender,
      idCard: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).idCard,
      email: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).email,
      homeplace: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).homeplace,
      workplace: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).workplace,
      houseid: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).houseid,
      limitid: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).limitid,
      avator: window.localStorage.getItem("user" || "[]") == null ? "" : JSON.parse(window.localStorage.getItem('user' || '[]')).avator,
    },
    host: "http://localhost",
    port: 8882
  },

  mutations: {
    login(state, user) {
      state.user = user;
      window.localStorage.setItem('user', JSON.stringify(user));
    }
  }
})
