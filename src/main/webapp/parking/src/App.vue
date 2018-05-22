<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    <router-view/>
  </div>
</template>
<script>
export default {
  name: 'App',
  mounted: function() {
    this.$nextTick(function() {
      global.baseURI='http://10.65.35.180:8080/parkingLot';
    })
  }
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
  overflow-y: hidden;
}

ul li {
  list-style: none;
}

.clearfix{
  overflow: hidden;
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
/* top改成定值 */
.parking-top {
  width: 100%;
  min-width: 400px;
  /*height: 10%;*/
  /*line-height: 90px;*/
  background-color: #98d0f2;
}
/* 导航内容等高 */
.parking-nav {
  float: left;
  margin-bottom: -2500px;
  padding-bottom: 2500px; 
  width: 200px;
  min-height: 900px;
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


/* .parking-nav a:link{
  color: #98d0f2;
} */

.parking-con {
  margin-left: 201px;
  width: calc(100% -200);
  height: 90%;
  padding-left: 50px;
  padding-top: 50px;
}

.router-link-active {
  color: #98d0f2;
}

</style>
