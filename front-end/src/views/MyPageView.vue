<template>
  <div>
    <b-container class="mt-3">
      <b-row>
        <b-col cols="2" class="mt-5 tabbar">
          <div class="mb-5"></div>
          <b-list-group>
            <b-list-group-item :to="{ name: 'jjim' }">찜 리스트</b-list-group-item>
            <b-list-group-item :to="{ name: 'usermodify' }">회원 수정</b-list-group-item>
            <b-list-group-item href="#" @click="userdelete">회원 탈퇴</b-list-group-item>
            <b-list-group-item v-if="true" :to="{ name: 'prefer' }">취향 분석 하기</b-list-group-item>
            <b-list-group-item :to="{ name: 'tradenotice' }">거래 게시판 알림</b-list-group-item>
            <b-list-group-item :to="{ name: 'togethernotice' }">함께 가요 알림</b-list-group-item>
          </b-list-group>
        </b-col>
        <b-col cols="10" class="mt-1">
          <router-view></router-view>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';

const userStore = 'userStore';
export default {
  components: {},
  data() {
    return {
      message: '',
    };
  },
  created() {},
  computed: {
    ...mapState(userStore, ['userInfo']),
  },
  methods: {
    userdelete() {
      if (confirm('정말로 탈퇴하시겠습니까?')) {
        axios.delete('http://192.168.214.52/user/delete/' + this.userInfo.id).then((resp) => {
          if (resp.data) {
            console.log('탈퇴함.');
            alert('탈퇴되었습니다.');
          }
        });
      } else {
        console.log('취소함');
        this.$router.push({ name: 'userinfo' });
      }
    },
  },
};
</script>

<style scoped>
.tabbar {
  margin-top: 8rem;
}
</style>
