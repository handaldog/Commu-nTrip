import Vue from 'vue';
import VueRouter from 'vue-router';
import MainView from '@/views/MainView';
import BoardView from '@/views/board/BoardView';
import MyPageView from '@/views/MyPageView';
import JjimView from '@/components/Mypage/JjimList';
import UserModify from '@/components/Mypage/UserModify';
import BoardFree from '@/components/board/BoardFree';
import BoardReview from '@/components/board/BoardReview';
import BoardRegist from '@/components/board/BoardRegist';
import BoardTrade from '@/components/board/BoardTrade';
import BoardTogether from '@/components/board/BoardTogether';
import BoardDetail from '@/components/board/BoardDetail';
import BoardModify from '@/components/board/BoardModify';
import Login from '@/components/user/UserLogin';
import Join from '@/components/user/UserJoin';
import TripListView from '@/components/trip/TripList';
import TripDetailView from '@/components/trip/TripDetail';
import TripView from '@/views/Trip/TripView';
import PreferAnalyze from '@/components/user/PreferAnalyze';
import UserInfo from '@/components/Mypage/UserInfo';
import BoardTogetherNotice from '@/components/Mypage/BoardTogetherNotice';
import BoardTradeNotice from '@/components/Mypage/BoardTradeNotice';

import store from '@/store';

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters['userStore/checkUserInfo'];
  const checkToken = store.getters['userStore/checkToken'];
  let token = sessionStorage.getItem('access-token');
  console.log('로그인 처리 전', checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log('토큰 유효성 체크하러 가자!!!!');
    await store.dispatch('userStore/getUserInfo', { token });
  }
  if (!checkToken || checkUserInfo === null) {
    alert('로그인이 필요한 페이지입니다..');
    // next({ name: "login" });
    router.push({ name: 'login' });
  } else {
    console.log('로그인 했다!!!!!!!!!!!!!.');
    next();
  }
};

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView,
  },
  {
    path: '/trip',
    name: 'trip',
    component: TripView,
    children: [
      {
        path: 'list',
        name: 'triplist',
        component: TripListView,
      },
      {
        path: 'detail',
        name: 'tripdetail',
        component: TripDetailView,
      },
    ],
  },

  {
    path: '/board',
    name: 'board',
    component: BoardView,
    children: [
      {
        path: 'free',
        name: 'free',
        component: BoardFree,
      },
      {
        path: 'review',
        name: 'review',
        component: BoardReview,
      },
      {
        path: 'regist',
        name: 'regist',
        beforeEnter: onlyAuthUser,
        component: BoardRegist,
      },
      {
        path: 'trade',
        name: 'trade',
        beforeEnter: onlyAuthUser,
        component: BoardTrade,
      },
      {
        path: 'together',
        name: 'together',
        beforeEnter: onlyAuthUser,
        component: BoardTogether,
      },
      {
        path: 'detail',
        name: 'boardDetail',
        beforeEnter: onlyAuthUser,
        component: BoardDetail,
      },
      {
        path: 'modify',
        name: 'boardModify',
        beforeEnter: onlyAuthUser,
        component: BoardModify,
      },
    ],
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: MyPageView,
    children: [
      {
        path: '/usermodify',
        name: 'usermodify',
        beforeEnter: onlyAuthUser,
        component: UserModify,
      },
      {
        path: '/jjim',
        name: 'jjim',
        beforeEnter: onlyAuthUser,
        component: JjimView,
      },
      {
        path: '/userinfo',
        name: 'userinfo',
        beforeEnter: onlyAuthUser,
        component: UserInfo,
      },
      {
        path: '/trnotice',
        name: 'tradenotice',
        beforeEnter: onlyAuthUser,
        component: BoardTradeNotice,
      },
      {
        path: '/tonotice',
        name: 'togethernotice',
        beforeEnter: onlyAuthUser,
        component: BoardTogetherNotice,
      },
    ],
  },

  {
    path: '/login',
    name: 'login',
    component: Login,
  },
  {
    path: '/join',
    name: 'join',
    component: Join,
  },

  {
    path: '/prefer',
    name: 'prefer',
    component: PreferAnalyze,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
