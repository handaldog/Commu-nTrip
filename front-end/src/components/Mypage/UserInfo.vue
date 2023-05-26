<template>
  <b-container>
    <h3 class="m-3">내 정보</h3>
    <!-- <b-row class="my-1" v-for="type in types" :key="type">
      <b-col sm="3">
        <label :for="`type-${type}`">
          <code>{{ type }}</code
          >:</label
        >
      </b-col>
      <b-col sm="9">
        <b-form-input :id="`type-${type}`" :type="type"></b-form-input>
      </b-col>
    </b-row> -->
    <b-row>
      <b-col md="6" offset-md="3" class="mt-3 border rounded">
        <b-form class="p-3">
          <b-form-group
            class="mt-3"
            id="input-group-1"
            label-align="left"
            label-cols="2"
            label="아이디 "
            label-for="input-1"
          >
            <b-form-input
              disabled
              id="input-1"
              v-model="user.id"
              type="text"
              placeholder="아이디 입력"
              required
            ></b-form-input>
          </b-form-group>

          <!-- <b-form-group id="input-group-2" label-align="left" label-cols="2" label="비밀번호 :" label-for="input-2">
              <b-form-input
                id="input-1"
                v-model="user.pw"
                type="password"
                placeholder="비밀번호 입력"
                required
              ></b-form-input>
            </b-form-group> -->

          <b-form-group id="input-group-3" label-align="left" label-cols="2" label="이름 " label-for="input-3">
            <b-form-input
              disabled
              id="input-3"
              v-model="user.name"
              type="text"
              placeholder="이름 입력"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-4" label-align="left" label-cols="2" label="나이 " label-for="input-4">
            <b-form-input
              disabled
              id="input-4"
              v-model="user.age"
              type="number"
              placeholder="나이 입력"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-5" label-align="left" label-cols="2" label="지역 " label-for="input-5">
            <b-form-input disabled id="input-3" v-model="user.local" type="text" required></b-form-input>
          </b-form-group>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from 'axios';

import { mapState } from 'vuex';

const userStore = 'userStore';

export default {
  data() {
    return {
      local: '',

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
        local: '',
      },
    };
  },
  computed: {
    ...mapState(userStore, ['userInfo']),
  },

  created() {
    this.user.id = this.userInfo.id;
    this.user.pw = this.userInfo.pw;
    this.user.name = this.userInfo.name;
    this.user.age = this.userInfo.age;
    this.user.local = this.userInfo.local;

    this.local = this.userInfo.local;
  },

  methods: {
    onSubmit() {
      //  const password = prompt('비밀번호를 입력하세요.');
      //  this.user.pw = password;
      axios
        .put(`http://192.168.214.52/user`, this.user)
        .then((response) => {
          console.log(response);
          alert('수정이 완료되었습니다.');
        })
        .catch((error) => {
          console.log(error);
          alert('수정 실패');
        });
    },
  },
};
</script>

<style scoped></style>
