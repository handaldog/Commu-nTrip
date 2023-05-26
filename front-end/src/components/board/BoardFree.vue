<template>
  <div>
    <h2>자유 게시판</h2>
    <div class="search">
      <b-button class="float-left mt-3 button" @click="mvRegist">글쓰기</b-button>
      <b-form inline class="float-right my-3">
        <b-form-select v-model="key" :options="options"></b-form-select>
        <b-form-input v-model="word" placeholder="검색어..."></b-form-input>
        <b-button class="button" @click="boardSearch">검색</b-button>
      </b-form>
    </div>
    <template v-if="items.length != 0">
      <b-table
        striped
        hover
        :fields="fields"
        :items="items"
        :per-page="perPage"
        :current-page="currentPage"
        @row-clicked="viewArticle"
      >
        <!-- <template #cell(subject)="data">
          <router-link :to="{ name: 'detail', params: { bno: data.item.bno } }">
            {{ data.item.subject }}
          </router-link>
        </template> -->
      </b-table>
      <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="my-table">
      </b-pagination>
    </template>
    <template v-else>
      <h3>게시글이 없습니다.</h3>
    </template>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'BoardFree',
  components: {},
  data() {
    return {
      key: '',
      word: '',
      items: [],
      perPage: 10,
      currentPage: 1,
      options: [
        {
          value: '',
          text: '검색 조건',
        },
        {
          value: '작성자',
          text: '작성자',
        },
        {
          value: '내용',
          text: '내용',
        },
        {
          value: '제목',
          text: '제목',
        },
      ],
      fields: [
        { key: 'bno', label: '글번호', tdClass: 'tdClass' },
        { key: 'subject', label: '제목', tdClass: 'tdSubject' },
        { key: 'writer', label: '작성자', tdClass: 'tdClass' },
        { key: 'registerTime', label: '작성일', tdClass: 'tdClass' },
      ],
    };
  },
  created() {
    axios.get(`http://192.168.214.52/board?boardType=0&key=${this.key}&word=${this.word}`).then((resp) => {
      this.items = resp.data;
    });
  },
  methods: {
    boardSearch() {
      axios.get(`http://192.168.214.52/board?boardType=0&key=${this.key}&word=${this.word}`).then((resp) => {
        this.items = resp.data;
      });
    },
    viewArticle(article) {
      localStorage.setItem('viewbno', article.bno);
      this.$router.push({
        name: 'boardDetail',
      });
    },
    mvRegist() {
      localStorage.setItem('registType', 0);
      this.$router.push({
        name: 'regist',
      });
    },
  },
  computed: {
    rows() {
      return this.items.length;
    },
  },
};
</script>

<style scoped>
@import url('@/assets/css/board.css');

.search {
  height: 80px;
}
.pagination {
  justify-content: center;
}

.button {
  background-color: #a5e289;
  border-color: #a5e289;
}
</style>
