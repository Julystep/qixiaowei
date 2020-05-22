<template>
  <div id="app">
    <!-- 路由占位符 -->
    <router-view></router-view>
    body {background-image:url('/src/img/city.jpg') no-repeat;}
  </div>
</template>

<script>
export default {
  name: "App"
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>