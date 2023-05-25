<template>
  <div>
    <b-container class="mt-5">
      <div class="empty"></div>
     <h2 style="color:#a5e289;">
     
      Hello! Commu'nTrip
     </h2> 
     <br>
      <h2>로그인</h2>
      <b-row>
        <b-col md="6" offset-md="3" class="mt-3 border rounded">
          <b-form class="px-3 pt-3">
            <b-form-group
              class="mt-3"
              id="input-group-1"
              label-align="left"
              label-cols="2"
              label="아이디 "
              label-for="input-1"
            >
              <b-form-input
                id="input-1"
                v-model="user.id"
                type="text"
                placeholder="아이디 입력"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group id="input-group-2" label-align="left" label-cols="2" label="비밀번호 " label-for="input-2">
              <b-form-input
                id="input-1"
                v-model="user.pw"
                type="password"
                placeholder="비밀번호 입력"
                required
                @keyup.enter="onLogin"
              ></b-form-input>
            </b-form-group>
          </b-form>
          <b-button style="background-color: #a5e289; border-color: #a5e289" @click="onLogin" class="m-3"
            >로그인</b-button
          >
          <b-button style="background-color: #a5e289; border-color: #a5e289" @click="moveJoin" class="m-3"
            >회원가입</b-button
          >
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';

const userStore = 'userStore';

export default {
  name: 'UserLogin',
  components: {},
  data() {
    return {
      user: {
        id: '',
        pw: '',
      },
    };
  },
  created() {},
  computed: {
    ...mapState(userStore, ['isLogin', 'isLoginError', 'userInfo']),
  },
  methods: {
    ...mapActions(userStore, ['userConfirm', 'getUserInfo']),
    async onLogin() {
      console.log(this.user);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem('access-token');
      if (this.isLogin) {
        await this.getUserInfo({ token });
        console.log('4. confirm() userInfo :: ', this.userInfo);
        this.$router.push({ name: 'main' });
      } else {
        alert('아이디나 비밀번호를 확인하세요.');
      }
    },
    moveJoin() {
      this.$router.push({ name: 'join' });
    },
  },
};
</script>

<style scoped>
.empty {
  height: 100px;
}
</style>
