<template>
  <div class="parking-box">
    <parking-top></parking-top>
    <div class="clearfix">
      <div class="parking-nav">
        <router-link to="/AdminIndex/AdminPaking">停车场管理</router-link>
        <router-link to="/AdminIndex/AdminUser">用户管理</router-link>
      </div>
      <div class="parking-con">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
import ParkingTop from '../base/ParkingTop'
export default {
  components: {
     ParkingTop
  },
  data() {
    return {
      isShow: false
    }
  },
  methods: {
    showToggle: function() {
      this.isShow = !this.isShow
    },
    LoginOut: function() {
      this.$router.push({ path: '/' });
    }
  }
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
