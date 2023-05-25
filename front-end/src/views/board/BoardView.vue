<template>
  <div>
    <b-container class="mt-5">
      <b-row>
        <b-col cols="2" class="tabbar">
          <b-list-group>
            <b-list-group-item :to="{ name: 'free' }">자유 게시판</b-list-group-item>
            <b-list-group-item :to="{ name: 'review' }">여행 후기</b-list-group-item>
            <b-list-group-item :to="{ name: 'trade' }">거래 게시판</b-list-group-item>
            <b-list-group-item :to="{ name: 'together' }">함께 가요</b-list-group-item>
          </b-list-group>
        </b-col>
        <b-col cols="10">
          <router-view></router-view>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.tabbar {
  margin-top: 8rem;
}
</style>
