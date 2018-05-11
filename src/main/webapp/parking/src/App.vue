<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    <router-view/>
  </div>
</template>
<script>
export default {
  name: 'App'
}

</script>
<style>
#app {
  width: 100%;
  height: 100%; 
}

body,
html {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

ul li {
  list-style: none;
}

.parking-box {
  width: 100%;
  height: 100%;
  padding: 0;
  margin: 0;
  font-size: 16px;
  font-family: '微软雅黑';
  color: #777;
}

.parking-top {
  width: 100%;
  height: 10%;
  background-color: #98d0f2;
}

.parking-nav {
  float: left;
  width: 200px;
  height: 90%;
  background-color: #ededed;
}

.parking-nav a {
  display: block;
  width: 100%;
  height: 60px;
  text-align: center;
  text-decoration: none;
  color: #777;
  line-height: 60px;
  border-bottom: 1px solid #fff;
}
.parking-con{
    float: left;
    width: calc(100% -200);
    height:90%; 
    padding: 70px;
}

</style>
