<template>
  <div row row-col-lg-4 row-col-md-6>

 <div class="border recom rounded"  >실시간 여행지 TOP5</div>
    <b-card-group deck style="justify-content: center">
      <!-- 카드 -->
      <b-card
        v-for="(item, index) in lists"
        :key="index"
        border-variant="Secondary"
        :title="item.title"
        :img-src="item.first_image"
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

        <b-button style="background-color: #a5e289; border-color: #a5e289" @click="movetripdetail(item.content_id)"
          >자세히 보기</b-button
        >
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  components: {},
  data() {
    return {
      lists: [],
    };
  },
  created() {
    axios.get('http://localhost/trip/toplist').then((resp) => {
      this.lists = resp.data;
      console.log(this.lists);
      // console.log(this.lists[0].title);
    });
  },
  methods: {
    movetripdetail(content_id) {
      console.log(content_id);
      localStorage.setItem('content_id', content_id);
      this.$router.push({ name: 'tripdetail' });
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
  background-color: white;
  color:  #a5e289;
  border-color: #a5e289;
}
</style>
