<template>
  <div>
    <b-container class="mt-5">
      <h2>여행 리스트</h2>
      <b-table striped hover :fields="fields" :items="triplist" @row-clicked="tripArticle" class="mt-4">
        <template #cell(first_image)="data">
          <b-img :src="data.item.first_image" width="100%" height="100%">{{ data.item.first_image }}</b-img>
        </template>

        <!-- true -->
        <template #cell(jjim)="data">
          <div v-if="data.item.jno" @click.stop class="h-100 d-flex align-items-center justify-content-center">
            <font-awesome-icon
              icon="fa-solid fa-heart"
              style="color: #f40b0b"
              size="2xl"
              @click="jjimDelete(data.item.jno)"
            />
          </div>
          <div v-else @click.stop class="h-100 d-flex align-items-center justify-content-center">
            <font-awesome-icon
              icon="fa-regular fa-heart"
              style="color: #f40b0b"
              size="2xl"
              @click="jjimInsert(data.item.content_id)"
            />
          </div>
        </template>
      </b-table>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';

const userStore = 'userStore'; // 그냥 경로로 import 해도 되는지?
export default {
  components: {},
  data() {
    return {
      fields: [
        { key: 'content_id', label: '번호', tdClass: 'tdClass' },
        { key: 'title', label: '제목', tdClass: 'tdSubject' },
        { key: 'addr1', label: '주소', tdClass: 'tdClass' },
        { key: 'first_image', label: '사진', tdClass: 'tdClass' },
        { key: 'jjim', label: '찜여부', tdClass: 'tdClass' },
      ],
      jjimInfo: {
        id: '',
        content_id: '',
      },
      triplist: [],
    };
  },

  created() {
    axios
      .get(
        `http://192.168.214.52/trip/list?content_type_id=${this.selected.typeselected}&sido_code=${this.selected.localselected}&title=${this.selected.searchselected}`
      )
      .then(({ data }) => {
        this.triplist = data;
      });
  },
  watch: {
    jjimInfo() {},
  },
  methods: {
    tripArticle(article) {
      console.log(article);

      localStorage.setItem('content_id', article.content_id);
      this.$router.push({
        name: 'tripdetail',
      });
    },

    async jjimInsert(content_id) {
      this.jjimInfo.content_id = content_id;
      this.jjimInfo.id = this.userInfo.id;
      console.log(this.jjimInfo.content_id);
      console.log(this.jjimInfo.id);
      await axios.post('http://192.168.214.52/jjim/write', this.jjimInfo);

      await axios
        .get(
          `http://192.168.214.52/trip/list?content_type_id=${this.selected.typeselected}&sido_code=${this.selected.localselected}&title=${this.selected.searchselected}`
        )
        .then(({ data }) => {
          this.triplist = data;
        });
    },

    async jjimDelete(jno) {
      await axios.delete('http://192.168.214.52/jjim/' + jno);

      await axios
        .get(
          `http://192.168.214.52/trip/list?content_type_id=${this.selected.typeselected}&sido_code=${this.selected.localselected}&title=${this.selected.searchselected}`
        )
        .then(({ data }) => {
          this.triplist = data;
        });
    },
  },
  computed: {
    ...mapState(['selected']),
    ...mapState(userStore, [('isLogin', 'userInfo')]),
  },
};
</script>

<style scoped></style>
