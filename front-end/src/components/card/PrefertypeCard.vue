<template>
  <div row row-col-lg-4 row-col-md-6>

 <div class="border recom rounded"  >{{ userInfo.type }} 타입에게 어울리는 여행지 추천</div>

    <b-card-group deck style="justify-content: center">
      <!-- 카드 -->
      <b-card
        v-for="(item, index) in preferlists"
        :key="index"
        border-variant="Secondary"
        :title="item.title"
        :img-src="item.image"
        img-alt="Image"
        img-top
        img-height="200rem"
        tag="article"
        style="max-width: 20rem"
        class="m-3 p-3"
        align="center"
      >
        <b-card-text>
          {{ item.addr1 }}
        </b-card-text>

        <!-- content_id 넣기  -->
        <b-button style="background-color: #a5e289; border-color: #a5e289" @click="movetripdetail(item.title)"
          >자세히 보기</b-button
        >
      </b-card>
    </b-card-group>
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
      preferlists: [],
      user: {
        type: '',
      },
    };
  },

  computed: {
    ...mapState(userStore, ['userInfo']),
  },

  watch: {},
  created() {
    console.log(this.userInfo.type);
    axios.get('http://localhost/user/type/' + this.userInfo.type).then((resp) => {
      console.log(resp.data);
      this.preferlists = resp.data;
      console.log(this.preferlists);
      // console.log(this.lists[0].title);
    });
  },
  methods: {
    movetripdetail(title) {
      console.log(title);
      axios.get('http://localhost/trip/preferdetail/' + title).then((resp) => {
        console.log(resp.data);
        localStorage.setItem('content_id', resp.data);
        this.$router.push({ name: 'tripdetail' });
      });
    },
  },
};
</script>

<style scoped>
.recom {
  margin-left: auto;
  margin-right: auto;
  display: inline-block;
  margin-top: 10px;
  margin-bottom: 10px;
  padding : 5px;
  background-color: #a5e289;
  color: white;
  border-color: #a5e289;
}
</style>
