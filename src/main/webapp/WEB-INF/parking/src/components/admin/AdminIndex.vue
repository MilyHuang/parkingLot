<template>

  <div class="parking-box">
    <div class="parking-top"></div>
    <div class="parking-nav">

        <router-link to="/AdminPaking">停车场管理</router-link>
        <router-link to="/AdminUser">用户管理</router-link>
    </div>
    <div class="parking-con">
        <router-view></router-view>
    </div>
  </div>
</template>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
