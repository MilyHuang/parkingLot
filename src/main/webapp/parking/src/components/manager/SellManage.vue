<template>
	<div class="sell-manage">
	   <router-link to="/ManagerIndex">返回</router-link>
	   <h3>{{$route.params.lotName}}</h3>
	   <div class="month">
	   	<el-date-picker
	   	v-model="month"
	   	type="month"
	   	placeholder="选择月份"
	   	@change="changeMonth()">
	       </el-date-picker>
	   </div>
	   <h4>销售额报表</h4>
	   <el-table
          :data="benefitData"
          style="width: 80%">
          <el-table-column
            prop="rend"
            label="租金"
            width="200">
          </el-table-column>
          <el-table-column
            prop="totalBenefit"
            label="收入总额"
            width="200">
          </el-table-column>
          <el-table-column
            prop="expectBenefit"
            label="期望盈利">
          </el-table-column>
      </el-table>
      <h4>使用率报表</h4>
	   <el-table
          :data="useData"
          style="width: 80%">
          <el-table-column
            prop="date"
            label="卡号"
            width="280">
          </el-table-column>
          <el-table-column
            prop="name"
            label="使用次数"
            width="280">
          </el-table-column>
          <!-- <el-table-column
            prop="address"
            label="地址">
          </el-table-column> -->
      </el-table>
  </div>

</template>

<script>
	export default{
		name: `SellManage`,
		data(){
			return{
				month:'',
        benefitData: [{
            rend: 100,
            totalBenefit: 2000,
            expectBenefit: 20000
          }, {
            rend: 100,
            totalBenefit: 2000,
            expectBenefit: 20000
          }, {
            rend: 100,
            totalBenefit: 2000,
            expectBenefit: 20000
          }, {
            rend: 100,
            totalBenefit: 2000,
            expectBenefit: 20000
          }]
				// benefitData:{
    //        rend: 100,
    //        totalBenefit: 3000,
    //        expectBenefit: 30000
				// },
				// useData:{
				// }
			}
		},
		methods:{
			changeMonth(){
			}
		}
	}
</script>

<style scoped>
	.sell-manage{

	}
	a{
		text-decoration: none;
	}
</style>