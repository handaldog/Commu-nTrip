<template>
  <b-container>
    <h3>글보기</h3>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <template v-if="user.id == article.writer">
        <b-col class="text-right">
          <b-button variant="outline-info" size="sm" @click="moveModifyArticle" class="mr-2">글수정</b-button>
          <b-button variant="outline-danger" size="sm" @click="deleteArticle">글삭제</b-button>
        </b-col>
      </template>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>
          ${article.subject}</h3><div><h6>${article.writer}</div><div>${article.registerTime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <template v-if="article.boardType == 2">
            <b-card-body class="text-center"> <div v-html="tradeData"></div> </b-card-body>
          </template>
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <template v-if="article.boardType == 2 && article.tradeState == '거래 중'">
      <b-row>
        <b-col cols="10">
          <b-form-input
            id="trade"
            v-model="trade.content"
            type="text"
            @keyup.enter="registAlert"
            required
          ></b-form-input>
        </b-col>
        <b-col cols="2">
          <b-button variant="outline-primary" size="sm" @click="registAlert">거래 희망</b-button>
        </b-col>
      </b-row>
    </template>
    <template v-else-if="article.boardType == 3 && article.totalPerson != article.personnelCount">
      <b-row>
        <b-col cols="10">
          <b-form-input
            id="comment"
            v-model="trade.content"
            type="text"
            @keyup.enter="registAlert"
            required
          ></b-form-input>
        </b-col>
        <b-col>
          <b-button variant="outline-primary" size="sm" @click="registAlert">함께 가요</b-button>
        </b-col>
      </b-row>
    </template>
    <template v-else-if="article.boardType != 2 && article.boardType != 3">
      <b-row>
        <ul style="display: block; width: 100%">
          <li style="display: flex; align-items: center" class="comm" v-for="(comment, index) in comments" :key="index">
            <b-col class="text-left" cols="10"> {{ comment.id }} : {{ comment.content }} </b-col>
            <b-col cols="2">
              <template v-if="user.id == comment.id">
                <!-- <b-button variant="outline-info" size="sm" @click="moveModifyComment" class="mr-2">댓글 수정</b-button> -->
                <b-button variant="outline-danger" size="sm" @click="deleteComment(comment.cno)">댓글 삭제</b-button>
              </template>
            </b-col>
          </li>
        </ul>
      </b-row>
      <b-row>
        <b-col cols="10">
          <b-form-input
            id="comment"
            v-model="comment.content"
            type="text"
            placeholder="댓글..."
            @keyup.enter="registComment"
            required
          ></b-form-input>
        </b-col>
        <b-col cols="2">
          <b-button variant="outline-primary" size="sm" @click="registComment">댓글 등록</b-button>
        </b-col>
      </b-row>
    </template>
  </b-container>
</template>

<script>
import axios from 'axios';
import { mapActions, mapState } from 'vuex';

const userStore = 'userStore';
export default {
  name: 'BoardDetail',
  // components: {},
  data() {
    return {
      article: {
        subject: '',
        writer: '',
        content: '',
        registerTime: '',
        boardType: '',
        tradeState: '',
        tradeName: '',
        tradePrice: '',
        totalPerson: '',
        personnelCount: '',
      },
      user: {
        id: '',
      },
      comments: [],
      comment: {
        cno: '',
        bno: '',
        id: '',
        content: '',
      },
      trade: {
        bno: '',
        writer: '',
        id: '',
        content: '',
        type: '',
      },
    };
  },
  computed: {
    tradeData() {
      return `<h5>물품명 : ${this.article.tradeName} | 가격 : ${this.article.tradePrice}</h5> <hr/>`;
    },
    message() {
      if (this.article.content) return this.article.content.split('\n').join('<br>');
      return '';
    },
    ...mapState(userStore, ['isLogin', 'isLoginError', 'userInfo']),
  },
  created() {
    this.comment.bno = localStorage.getItem('viewbno');
    this.trade.bno = localStorage.getItem('viewbno');

    let param = localStorage.getItem('viewbno');
    axios.get('http://localhost/board/' + param).then((resp) => {
      this.article = resp.data;
      console.log(this.article);
    });

    axios.get('http://localhost/board/comment/' + param).then((resp) => {
      this.comments = resp.data;
    });

    let userToken = sessionStorage.getItem('access-token');
    console.log(this.isLogin);
    if (this.isLogin) {
      this.getUserInfo({ token: userToken });
      console.log(this.userInfo);
      this.user = this.userInfo;
      this.comment.id = this.user.id;
    }
  },
  methods: {
    ...mapActions(userStore, ['getUserInfo']),
    moveModifyArticle() {
      localStorage.setItem('modibno', this.article.bno);
      this.$router.replace({
        name: 'boardModify',
      });
    },
    async deleteArticle() {
      if (confirm('정말로 삭제합니까?')) {
        await axios.delete('http://localhost/board/' + localStorage.getItem('viewbno')).then((resp) => {
          console.log(resp);
        });
        this.moveList();
      }
    },
    moveModifyComment() {
      // this.$router.replace({
      //   name: 'boardModify',
      //   params: { bno: this.article.bno },
      // });
    },
    deleteComment(cno) {
      axios.delete('http://localhost/board/comment/' + cno).then((resp) => {
        console.log(resp);
        this.$router.go(0);
      });
    },
    moveList() {
      if (this.article.boardType == 0) {
        this.$router.push({ name: 'free' });
      } else if (this.article.boardType == 1) {
        this.$router.push({ name: 'review' });
      } else if (this.article.boardType == 2) {
        this.$router.push({ name: 'trade' });
      } else if (this.article.boardType == 3) {
        this.$router.push({ name: 'together' });
      }
    },
    async registComment(event) {
      event.preventDefault();

      await axios.post('http://localhost/board/comment', this.comment).then((resp) => {
        console.log(resp.data);
      });

      this.comment.content = '';
      this.$router.go(0); // 새로고침
    },
    registAlert(event) {
      event.preventDefault();

      this.trade.id = this.user.id;
      this.trade.writer = this.article.writer;
      this.trade.type = this.article.boardType;

      axios.post('http://localhost/board/alert', this.trade).then((resp) => {
        console.log(resp.data);
      });

      this.trade.content = '';
      alert('알람을 보냈습니다!');
    },
  },
};
</script>

<style scoped>
.comm {
  text-align: left;
}

li {
  list-style-type: none;
}
</style>
