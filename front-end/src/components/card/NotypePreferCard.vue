<template>
  <div row row-col-lg-4 row-col-md-6>
    <b-card-group deck style="justify-content: center">
      <!-- 카드 -->
      <b-card
        v-for="(item, index) in notypepreferlists"
        :key="index"
        border-variant="Secondary"
        :img-src="require(`@/assets/img/물음표.jpg`)"
        img-alt="Image"
        img-top
        img-height="200rem"
        tag="article"
        style="max-width: 20rem"
        class="m-3 p-3"
        align="center"
      >
        <b-card-text> 나의 여행지는? </b-card-text>
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
import { mapState } from "vuex";
const userStore = "userStore";
export default {
  components: {},
  data() {
    return {
      notypepreferlists: [
        {
          image: "@/assets/물음표.jpg",
        },
        {
          image: "@/assets/물음표.jpg",
        },
        {
          image: "@/assets/물음표.jpg",
        },
        {
          image: "@/assets/물음표.jpg",
        },
        {
          image: "@/assets/물음표.jpg",
        },
      ],
    };
  },

  methods: {},

  computed: {
    ...mapState(userStore, ["userInfo"]),
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
  padding: 5px;
  background-color: #a5e289;
  color: white;
  border-color: #a5e289;
}
</style>
