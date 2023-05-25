<template>
  <div id="app">
    <main-nav></main-nav>
    <router-view />
  </div>
</template>

<script>
import MainNav from '@/components//Nav/MainNav.vue';
export default {
  components: { MainNav },
};
</script>

<style>
#app {
  font-family: nanum;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

@font-face {
  font-family: 'nanum';
  src: url('@/assets/font/NanumSquareRoundB.ttf') format('truetype');
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #a5e289;
}

nav a.router-link-exact-active {
  color: #a5e289;
}

nav a:-webkit-any-link {
  text-decoration: none;
  color: #a5e289;
}
</style>
