<template>
	<div class="parking-box">
    <parking-top></parking-top>
    <div class="clearfix">
      <div class="parking-nav">
        <router-link to="/OperatorIndex/CreateCard">办理停车卡</router-link>
        <router-link to="/OperatorIndex/SearchCard">停车卡查询</router-link>
        <router-link to="/OperatorIndex/SearchBill">账单查询</router-link>
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