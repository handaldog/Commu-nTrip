<template>
  <div>
      <!-- <b-button variant="outline-primary" m-3>여행 검색하기</b-button> -->
      
      <div class="border search rounded" >여행 검색하기</div>
    
    <b-form inline mr-3 mt-3>
      <b-form-select v-model="typeselected" :options="typeoptions"></b-form-select>
      <b-form-select v-model="localselected" :options="localoptions"></b-form-select>

      <label class="sr-only" for="inline-form-input-search">search</label>
      <b-form-input
        id="inline-form-input-search"
        class="mb-2 mr-sm-2 mb-sm-0"
        placeholder="검색어"
        v-model="searchselected"
        @keydown.enter="searchtripList()"
      ></b-form-input>

      <b-button style="background-color: #a5e289; border-color: #a5e289" @click="searchtripList()">검색</b-button>
    </b-form>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  components: {},
  data() {
    return {
      searchselected: '',
      typeselected: 0,
      typeoptions: [
        { value: 0, text: '관광지 유형' },
        { value: 12, text: '관광지' },
        { value: 14, text: '문화시설' },
        { value: 15, text: '축제공연행사' },
        { value: 25, text: '여행코스' },
        { value: 28, text: '레포츠' },
        { value: 32, text: '숙박' },
        { value: 38, text: '쇼핑' },
        { value: 39, text: '음식점' },
      ],
      localselected: 0,
      localoptions: [
        { value: 0, text: '검색할 지역 선택' },
        { value: '1', text: '서울' },
        { value: '2', text: '인천' },
        { value: '3', text: '대전' },
        { value: '4', text: '대구' },
        { value: '5', text: '광주' },
        { value: '6', text: '부산' },
        { value: '7', text: '울산' },
        { value: '8', text: '세종특별자치시' },
        { value: '31', text: '경기도' },
        { value: '32', text: '강원도' },
        { value: '33', text: '충청북도' },
        { value: '34', text: '충청남도' },
        { value: '35', text: '경상북도' },
        { value: '36', text: '경상남도' },
        { value: '37', text: '전라북도' },
        { value: '38', text: '전라남도' },
        { value: '39', text: '제주도' },
      ],
    };
  },
  created() {},
  methods: {
    ...mapActions(['searchTripList']),
    searchtripList() {
      this.searchTripList({
        typeselected: this.typeselected,
        localselected: this.localselected,
        searchselected: this.searchselected,
      });
      this.movetriplist();
    },

    movetriplist() {
      this.$router.push({ name: 'triplist' });
    },
  },
};
</script>

<style scoped>
.form-inline {
  justify-content: center;
}

.search {
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
