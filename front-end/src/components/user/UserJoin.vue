<template>
  <div>
    <b-container class="mt-5">
      <h2 style="color: #a5e289">Welcome ! Commu'nTrip</h2>

      <div class="empty"></div>
      <h2>회원가입</h2>
      <b-row>
        <b-col md="6" offset-md="3" class="mt-1 border rounded">
          <b-form class="p-3">
            <b-form-group
              class="mt-3"
              id="input-group-1"
              label-align="left"
              label-cols="2"
              label="아이디 "
              label-for="input-1"
              valid-feedback="사용 가능한 아이디 입니다."
              :invalid-feedback="invalidFeedback"
              :state="state"
            >
              <b-form-input
                id="input-1"
                v-model="user.id"
                type="text"
                placeholder="아이디 입력"
                :state="state"
                @keyup="idCheck"
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
              ></b-form-input>
            </b-form-group>

            <b-form-group id="input-group-3" label-align="left" label-cols="2" label="이름 " label-for="input-3">
              <b-form-input
                id="input-3"
                v-model="user.name"
                type="text"
                placeholder="이름 입력"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group id="input-group-4" label-align="left" label-cols="2" label="나이 " label-for="input-4">
              <b-form-input
                id="input-4"
                v-model="user.age"
                type="number"
                placeholder="나이 입력"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group id="input-group-5" label-align="left" label-cols="2" label="지역 " label-for="input-5">
              <b-form-select v-model="user.local" :options="options"></b-form-select>
            </b-form-group>
          </b-form>
          <b-button class="mb-3" style="background-color: #a5e289; border-color: #a5e289" @click="onSubmit"
            >회원 가입</b-button
          >
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'UserJoin',
  components: {},
  data() {
    return {
      options: [
        { value: '서울', text: '서울' },
        { value: '대전', text: '대전' },
        { value: '구미', text: '구미' },
        { value: '광주', text: '광주' },
        { value: '부울경', text: '부울경' },
      ],
      user: {
        id: '',
        pw: '',
        name: '',
        age: '',
        local: 'seoul',
        type: 'notype',
      },
      isIdCheck: false,
    };
  },
  created() {},
  methods: {
    onSubmit() {
      console.log(this.user);
      if (!this.user.id || !this.user.pw || !this.user.name || !this.user.age) {
        alert('정보를 전부 입력해주세요!');
      } else if (!this.isIdCheck) {
        alert('아이디 중복 검사를 해주세요!');
      } else {
        axios.post('http://localhost/user/join', this.user).then((data) => {
          console.log(data);
          localStorage.setItem('preferCheck', this.user.id);
          this.$router.push({ name: 'prefer' });
        });
      }
    },

    idCheck() {
      axios.post('http://localhost/user/check', this.user).then((resp) => {
        console.log(resp.data);
        if (resp.data == 0) {
          this.isIdCheck = true;
        } else {
          this.isIdCheck = false;
        }
      });
    },
  },
  computed: {
    state() {
      return this.isIdCheck && this.user.id.length > 0;
    },
    invalidFeedback() {
      if (this.user.id.length == 0) {
        return '적어도 한 글자 이상이어야 합니다.';
      }
      return '중복된 아이디가 존재합니다.';
    },
  },
};
</script>

<style scoped>
.empty {
  height: 60px;
}
</style>
