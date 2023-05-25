<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="light">
      <b-navbar-brand><router-link to="/">Commu'nTrip</router-link></b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <!-- 로그인 O -->
          <div v-if="isLogin === true">
            <b-navbar-brand><router-link :to="{ name: 'free' }">Board</router-link></b-navbar-brand>
            <b-navbar-brand
              ><router-link :to="{ name: 'userinfo' }">MyPage</router-link></b-navbar-brand
            >
            <b-navbar-brand><a href="#" @click="logout">Logout</a></b-navbar-brand>
            <b-navbar-brand
              ><router-link :to="{ name: 'jjim' }"
                ><font-awesome-icon
                  icon="fa-solid fa-heart"
                  style="color: #a5e289"
                  size="lg" /></router-link
            ></b-navbar-brand>
          </div>
          <!-- 로그인 X -->
          <div v-if="isLogin === false">
            <b-navbar-brand><router-link :to="{ name: 'free' }">Board</router-link></b-navbar-brand>
            <b-navbar-brand
              ><router-link :to="{ name: 'login' }">Login</router-link></b-navbar-brand
            >
            <b-navbar-brand><router-link :to="{ name: 'join' }">Join</router-link></b-navbar-brand>
          </div>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <!-- 검색 하는 거 넣고 검색 누를시 trip/triplist 가 나오게 -->
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';

const userStore = 'userStore';

export default {
  components: {},
  data() {
    return {
      mystyle: {
        color: '#a5e289',
      },
    };
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'userInfo']),
    // ...mapGetters(["checkUserInfo"]),
  },
  created() {},
  methods: {
    ...mapActions(userStore, ['userLogout']),
    logout() {
      this.userLogout(this.userInfo.id);
      sessionStorage.removeItem('access-token'); //저장된 토큰 없애기
      sessionStorage.removeItem('refresh-token'); //저장된 토큰 없애기
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
    // changeline(){
    //   this.mystyle.color = "#a5e289";
    // },
    // originalline(){
    //   this.mystyle.color = "red";
    // },
  },
};
</script>

<style scoped></style>
