<template>
  <div>
    <b-container fruid class="p-4 bg-white">
      <div class="empty"><h2>원하는 취향을 선택해주세요.</h2></div>

      <b-row>
        <b-col cols="5" class="choice1" @click="clickOne()">
          <b-img thumbnail :src="require(`@/assets/img/${imglist[choice].src1}.jpg`)" />
        </b-col>
        <b-col cols="2"></b-col>
        <b-col cols="5" class="choice2" @click="clickTwo()">
          <b-img thumbnail :src="require(`@/assets/img/${imglist[choice].src2}.jpg`)" />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';
import { mapActions, mapState } from 'vuex';

const userStore = 'userStore';
export default {
  name: 'PreferAnalyze',
  components: {},
  data() {
    return {
      choice: 0,
      imglist: [
        { src1: 'prenature0', src2: 'city0', value: 0 },
        { src1: 'trees1', src2: 'water1', value: 1 },
        { src1: 'timesquare2', src2: 'architecture2', value: 2 },
        { src1: '한사랑산악회', src2: '피톤치드중독자', value: 3 },
        { src1: '심해어', src2: '물아일체', value: 4 },
        { src1: '게으른베짱이', src2: 'MZ세대', value: 5 },
        { src1: '애국자', src2: '인싸이드', value: 6 },
      ],
      user: {
        id: '',
        type: '',
      },
    };
  },
  created() {
    if (this.userInfo) {
      this.user.id = this.userInfo.id;
    } else {
      this.user.id = localStorage.getItem('preferCheck');
    }
  },
  methods: {
    ...mapActions(userStore, ['getUserInfo']),
    clickOne() {
      let c = this.choice;
      this.choice = this.choice * 2 + 1;
      if (this.choice > 6) {
        alert(`당신은 ${this.imglist[c].src1}형 입니다.`);
        this.user.type = this.imglist[c].src1;
        axios.put('http://localhost/user/type', this.user).then((resp) => {
          console.log(resp.data);
          if (this.isLogin) {
            this.updateUser();
          } else {
            this.$router.push({ name: 'main' });
          }
        });
      }
    },
    clickTwo() {
      let c = this.choice;
      this.choice = this.choice * 2 + 2;
      if (this.choice > 6) {
        alert(`당신은 ${this.imglist[c].src2}형 입니다.`);
        this.user.type = this.imglist[c].src2;

        axios.put('http://localhost/user/type', this.user).then((resp) => {
          console.log(resp.data);
          if (this.isLogin) {
            this.updateUser();
          } else {
            this.$router.push({ name: 'main' });
          }
        });
      }
    },
    async updateUser() {
      let token = sessionStorage.getItem('access-token');
      await this.getUserInfo({ token });
      await this.getUserInfo({
        token: token,
        after: () => {
          this.$router.push({ name: 'main' });
        },
      });
    },
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'isLoginError', 'userInfo']),
  },
};
</script>

<style scoped>
/* .choice1 {
  width: 100%;
  height: 100%;
} */

.empty {
  height: 150px;
}
</style>
