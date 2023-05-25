<template>
  <div>
    <b-carousel
      id="carousel-1"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- 첫번째 슬라이드 -->
      <!-- Text slides with image -->
      <b-carousel-slide
        caption="Welcome, Commuication with Trip"
        text="여행과 대화하며 인생을 즐겁게 사는 상상을 펼쳐보아요."
        img-src="../assets/img/nature1.jpg"
      ></b-carousel-slide>

      <!-- 두번째 슬라이드 -->
      <!-- Slides with custom text -->
      <!-- <b-carousel-slide img-src="https://cdn.pixabay.com/photo/2021/08/02/16/22/beach-6517214_960_720.jpg">
        <h1>Hello world!</h1>
      </b-carousel-slide> -->
      <b-carousel-slide
        caption="Let's go Trip"
        text="체코의 아름다움은 경외감을 느끼게 합니다. 비행기를 타고 떠나 볼까요?"
        img-src="../assets/img/prague.jpg"
      ></b-carousel-slide>

      <!-- 세번째 슬라이드 -->
      <!-- Slides with image only -->
      <!-- <b-carousel-slide
        img-src="https://cdn.pixabay.com/photo/2022/05/03/10/29/prague-7171444_960_720.jpg"
      ></b-carousel-slide> -->
      <b-carousel-slide
        caption="Our Trip Time"
        text="우리의 여행 추억은 평생 남습니다. 추억을 쌓으러 가봅시다."
        img-src="../assets/img/hintersee.jpg"
      ></b-carousel-slide>
    </b-carousel>

    <!-- <p class="mt-4">
      Slide #: {{ slide }}<br />
      Sliding: {{ sliding }}
    </p> -->
    <br />
    <!-- 여행 검색창 -->
    <search-trip></search-trip>

    <!-- 실시간 Top 카드 보여주는 곳 -->

    <br />
    <main-card></main-card>

    <!-- 추천 카드 보여주는 곳 -->
    <br />
    <div v-if="this.isLogin">
      <div v-if="this.userInfo.type == 'notype'">
        <button class="border recom rounded" @click="movetypeselect">맞춤 타입 정하러 가기</button>
        <notype-prefer-card></notype-prefer-card>
        <h1>타입 결정하러 가세요!!</h1>
      </div>
      <div v-else>
        <prefertype-card></prefertype-card>
      </div>
    </div>
    <div v-else>
      <button class="border recom rounded" @click="movetypelogin">로그인 하기</button>
      <notype-prefer-card></notype-prefer-card>
      <h1>로그인을 하시면 맞춤 카드를 볼 수 있어요!</h1>
    </div>
  </div>
</template>

<script>
import MainCard from "@/components/card/MainCard.vue";
import SearchTrip from "@/components/Search/SearchTrip.vue";
import PrefertypeCard from "../components/card/PrefertypeCard.vue";
import { mapState } from "vuex";
import NotypePreferCard from "@/components/card/NotypePreferCard.vue";

const userStore = "userStore";
export default {
  components: { MainCard, SearchTrip, PrefertypeCard, NotypePreferCard },

  methods: {
    onSlideStart() {},
    onSlideEnd() {},

    movetypelogin() {
      this.$router.push({ name: "login" });
    },
    movetypeselect() {
      this.$router.push({ name: "prefer" });
    },
  },

  computed: {
    ...mapState(userStore, ["userInfo", "isLogin"]),
  },
};
</script>

///////////////////////////

<style scoped>
.recom {
  margin-left: auto;
  margin-right: auto;
  display: inline-block;
  margin-top: 10px;
  margin-bottom: 10px;
  padding: 5px;
  background-color: #a5e289;
  color: white;
  border-color: #a5e289;
}
/* .img-fluid {
  max-width: 100%;
  height: 700px;
} */
</style>
