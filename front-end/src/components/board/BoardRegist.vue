<template>
  <div>
    <h2>글 작성</h2>
    <b-form>
      <b-form-group id="input-group-1" label="제목 :" label-for="input-1">
        <b-form-input id="input-1" v-model="article.subject" type="text" placeholder="제목" required></b-form-input>
      </b-form-group>

      <template v-if="article.boardType == 2">
        <b-form-group id="input-group-2" label="물품명 :" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="article.tradeName"
            type="text"
            placeholder="물품명"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-3" label="가격 :" label-for="input-3">
          <b-form-input
            id="input-3"
            v-model="article.tradePrice"
            type="number"
            placeholder="가격"
            required
          ></b-form-input>
        </b-form-group>
      </template>

      <template v-if="article.boardType == 3">
        <b-form-group id="input-group-5" label="전체 인원 :" label-for="input-5">
          <b-form-input
            id="input-5"
            v-model="article.totalPerson"
            type="number"
            placeholder="전체 인원 수"
            required
          ></b-form-input>
        </b-form-group>
      </template>

      <b-form-group id="input-group-4" label="내용 :" label-for="input-4">
        <b-form-textarea
          id="input-4"
          v-model="article.content"
          placeholder="내용"
          rows="10"
          max-rows="10"
          required
        ></b-form-textarea>
      </b-form-group>
    </b-form>
    <b-button @click="onSubmit" style="background-color: #a5e289; border-color: #a5e289; margin-right: 5px"
      >작성</b-button
    >
    <b-button @click="onReset" variant="danger">초기화</b-button>
  </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';

const userStore = 'userStore';
export default {
  name: 'BoardRegist',
  components: {},
  data() {
    return {
      article: {
        writer: '',
        subject: '',
        content: '',
        boardType: '',
        tradeState: '',
        tradeName: '',
        tradePrice: '',
        totalPerson: '',
        personnelCount: '',
      },
    };
  },
  computed: {
    ...mapState(userStore, ['userInfo']),
  },
  created() {
    let type = localStorage.getItem('registType');
    this.article.boardType = type;

    this.article.writer = this.userInfo.id;
  },
  methods: {
    async onSubmit() {
      console.log(this.article.boardType);
      if (this.article.boardType == 2) {
        this.article.tradeState = '거래 중';
      }
      if (this.article.boardType == 3) {
        this.article.personnelCount = 1;
      }
      await axios.post('http://192.168.214.52/board', this.article).then((data) => {
        console.log(data);
      });
      let type = localStorage.getItem('registType');
      if (type == 0) {
        this.$router.push({ name: 'free' });
      } else if (type == 1) {
        this.$router.push({ name: 'review' });
      } else if (type == 2) {
        this.$router.push({ name: 'trade' });
      } else if (type == 3) {
        this.$router.push({ name: 'together' });
      }
    },
    onReset() {
      this.article.subject = '';
      this.article.writer = '';
      this.article.content = '';
      this.article.tradeName = '';
      this.article.tradePrice = '';
      this.totalPerson = '';
    },
  },
};
</script>

<style scoped></style>
