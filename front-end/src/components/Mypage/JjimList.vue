<template>
  <div>
    <b-container class="mt-5">
      <h2>찜 리스트</h2>
      <b-table striped hover :fields="fields" :items="jjimlists" class="mt-4">
        <template #cell(first_image)="data">
          <b-img :src="data.item.first_image" width="100%" height="100%">{{ data.item.first_image }}</b-img>
        </template>

        <template #cell(jjim)="data">
          <div @click.stop class="h-100 d-flex align-items-center justify-content-center">
            <font-awesome-icon
              icon="fa-solid fa-heart"
              style="color: #f40b0b"
              size="2xl"
              @click="jjimDelete(data.item.jno)"
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
const userstore = 'userStore';

export default {
  components: {},
  data() {
    return {
      fields: [
        { key: 'jno', label: '번호', tdClass: 'tdClass' },
        { key: 'content_id', label: '아이디', tdClass: 'tdClass' },
        { key: 'title', label: '제목', tdClass: 'tdSubject' },
        { key: 'addr1', label: '주소', tdClass: 'tdClass' },
        { key: 'first_image', label: '사진', tdClass: 'tdClass' },
        { key: 'jjim', label: '찜여부', tdClass: 'tdClass' },
      ],
      // id: "",
      jjimlists: [],
    };
  },
  computed: {
    ...mapState(userstore, ['userInfo']),
  },
  created() {
    console.log(this.userInfo);
    axios.get(`http://localhost/jjim/list?id=${this.userInfo.id}`).then((resp) => {
      console.log(resp.data);
      this.jjimlists = resp.data;
    });
  },
  methods: {
    async jjimDelete(jno) {
      await axios.delete('http://localhost/jjim/' + jno);

      axios.get(`http://localhost/jjim/list?id=${this.userInfo.id}`).then((resp) => {
        console.log(resp.data);
        this.jjimlists = resp.data;
      });
    },
  },
};
</script>

<style scoped></style>
