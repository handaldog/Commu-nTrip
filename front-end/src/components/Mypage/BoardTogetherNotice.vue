<template>
  <div>
    <b-container class="mt-5">
      <h2>함께 가요 알림</h2>
      <template v-if="items.length != 0">
        <b-table
          striped
          hover
          :fields="fields"
          :items="items"
          :per-page="perPage"
          :current-page="currentPage"
          class="mt-4"
        >
          <template #cell(accept)="data">
            <font-awesome-icon
              :icon="['fas', 'check']"
              style="color: #26f72a"
              size="lg"
              @click="acceptAlert(data.item.bno), deleteAlert(data.item.tno)"
            />
          </template>
          <template #cell(delete)="data">
            <font-awesome-icon
              :icon="['far', 'circle-xmark']"
              style="color: #ff0505"
              size="lg"
              @click="deleteAlert(data.item.tno)"
            />
          </template>
        </b-table>
        <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="my-table">
        </b-pagination>
      </template>
      <template v-else>
        <h3>알림이 없습니다.</h3>
      </template>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';

const userStore = 'userStore';
export default {
  name: 'TogetherNotice',
  components: {},
  data() {
    return {
      items: [],
      perPage: 10,
      currentPage: 1,
      fields: [
        { key: 'bno', label: '글번호', tdClass: 'tdClass' },
        { key: 'id', label: '참여자', tdClass: 'tdClass' },
        { key: 'content', label: '내용', tdClass: 'tdClass' },
        { key: 'registerTime', label: '작성일', tdClass: 'tdClass' },
        { key: 'accept', label: '수락', tdClass: 'tdClass' },
        { key: 'delete', label: '삭제', tdClass: 'tdClass' },
      ],
    };
  },
  computed: {
    ...mapState(userStore, ['userInfo']),
    rows() {
      return this.items.length;
    },
  },
  created() {
    console.log(this.userInfo.id);
    axios.get(`http://192.168.214.52/board/alert/toget/${this.userInfo.id}`).then((resp) => {
      this.items = resp.data;
    });
  },
  methods: {
    deleteAlert(tno) {
      console.log('tno', tno);
      axios
        .delete(`http://192.168.214.52/board/alert/${tno}`)
        .then(({ data }) => {
          console.log(data);
        })
        .then(() => {
          this.$router.go(0);
        });
    },
    acceptAlert(bno) {
      console.log('bno', bno);
      axios
        .put(`http://192.168.214.52/board/count/${bno}`)
        .then(({ data }) => {
          console.log(data);
        })
        .then(() => {
          this.$router.go(0);
        });
    },
  },
};
</script>

<style scoped>
.pagination {
  justify-content: center;
}
</style>
