<template>
  <div>
    <h2>글 수정</h2>
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
    <b-button @click="onSubmit" variant="primary">Submit</b-button>
  </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';

const userStore = 'userStore';
export default {
  name: 'BoardModify',
  components: {},
  data() {
    return {
      article: {
        writer: '',
        subject: '',
        content: '',
        boardType: '',
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
    axios.get('http://localhost/board/' + localStorage.getItem('modibno')).then((resp) => {
      this.article = resp.data;
    });
  },
  methods: {
    async onSubmit() {
      await axios.put('http://localhost/board', this.article).then((data) => {
        console.log(data);
      });

      localStorage.setItem('viewbno', this.article.bno);
      this.$router.push({
        name: 'boardDetail',
      });
    },
  },
};
</script>

<style scoped></style>
