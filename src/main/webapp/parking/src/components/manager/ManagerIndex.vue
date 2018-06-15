<template>
	<div class="parking-box">
    <parking-top></parking-top>
    <div class="clearfix">
      <div class="parking-nav">
        <router-link to="/ManagerIndex">停车场价格管理</router-link>
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
  
}
</script>

<style scoped>
	
</style>