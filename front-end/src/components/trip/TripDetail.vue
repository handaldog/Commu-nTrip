<template>
  <div>
    <!-- 여행 title -->
    <b-row>
      <b-col align-self="center" class="m-3"
        ><h3>{{ tripdetail.title }}</h3>
      </b-col>
    </b-row>
    <!-- class="bv-example-row bv-example-row-flex-cols" -->
    <b-container class="mt-3 border border-2 pb-3">
      <!-- <b-row>
        <b-col cols="4"><img :src="tripdetail.first_image" width="200" height="200" /></b-col>
        <b-col cols="4"><img :src="tripdetail.first_image" width="200" height="200" /></b-col>
      </b-row> -->

      <!-- <b-row align-v="stretch">
        <b-col><img :src="tripdetail.first_image" /></b-col>
        <b-col>One of three columns</b-col>
      </b-row> -->

      <!-- 여행 사진 두개 -->
      <b-row class="mt-3">
        <b-col cols="6">
          <img thumbnail :src="tripdetail.first_image" width="100%" height="100%" />
        </b-col>
        <b-col cols="6">
          <div id="map"></div>
        </b-col>
      </b-row>

      <!-- 댓글과 맵 -->
      <b-row class="mt-3 ml-2">
        <b-col class="border">
          <h5 class="comm mt-3">댓글</h5>
          <ul>
            <li class="comm" v-for="(comment, index) in comments" :key="index">
              {{ comment.id }} : {{ comment.content }}
            </li>
          </ul>
          <b-form-input
            class="mb-2"
            id="comment"
            v-model="comment.content"
            type="text"
            placeholder="댓글..."
            required
          ></b-form-input>
          <b-button
            class="mb-3"
            style="background-color: #a5e289; border-color: #a5e289"
            size="sm"
            @click="registComment"
            >댓글 등록</b-button
          >
        </b-col>

        <b-col cols="7">
          <trip-weather></trip-weather>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';
import { mapActions, mapState } from 'vuex';
import TripWeather from './TripWeather.vue';

const userStore = 'userStore';

export default {
  components: { TripWeather },
  data() {
    return {
      map: null,
      tripdetail: {
        latitude: '',
        longitude: '',
      },
      comments: [],
      comment: {
        content_id: '',
        id: '',
        content: '',
      },
      user: {
        id: '',
      },
    };
  },
  created() {
    let cid = localStorage.getItem('content_id');
    axios.get('http://192.168.214.52/trip/comment/' + cid).then((resp) => {
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
  mounted() {
    let cid = localStorage.getItem('content_id');
    this.comment.content_id = cid;

    axios
      .get(`http://192.168.214.52/trip/detail?content_id=${cid}`)
      .then((resp) => {
        console.log(resp.data);
        this.tripdetail = resp.data;
        localStorage.setItem('lat', this.tripdetail.latitude);
        localStorage.setItem('lon', this.tripdetail.longitude);
      })
      .then(() => {
        // api 스크립트 소스 불러오기 및 지도 출력
        if (window.kakao && window.kakao.maps) {
          this.loadMap();
        } else {
          this.loadScript();
        }
      });
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'isLoginError', 'userInfo']),
  },
  methods: {
    ...mapActions(userStore, ['getUserInfo']),
    // api 불러오기
    loadScript() {
      const script = document.createElement('script');
      ///* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?appkey=' + process.env.VUE_APP_KAKAO_MAP_API_KEY + '&autoload=false';

      document.head.appendChild(script);
    },
    // 맵 출력하기
    loadMap() {
      const container = document.getElementById('map');
      const options = {
        center: new window.kakao.maps.LatLng(this.tripdetail.latitude, this.tripdetail.longitude),
        level: 4,
      };

      this.map = new window.kakao.maps.Map(container, options);

      //   this.loadMaker();
      var makerPosition = new window.kakao.maps.LatLng(this.tripdetail.latitude, this.tripdetail.longitude);

      // 마커를 생성합니다.
      var marker = new window.kakao.maps.Marker({
        position: makerPosition,
      });

      marker.setMap(this.map);
    },
    async registComment(event) {
      event.preventDefault();

      if (this.comment.id) {
        await axios.post('http://192.168.214.52/trip/comment', this.comment).then((resp) => {
          console.log(resp);
        });

        this.comment.content = '';
        this.$router.go(0); // 새로고침
      } else {
        alert('댓글을 작성하려면 로그인을 해야합니다.');
      }
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}

.comm {
  text-align: left;
}

li {
  list-style-type: none;
}
</style>
