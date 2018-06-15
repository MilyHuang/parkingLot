<template>
	<div class="sell-manage">
	   <router-link to="/ManagerIndex">返回</router-link>
	   <h3>停车场：{{$route.params.lotName}}</h3>
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
            prop="cardNum"
            label="卡号"
            width="280">
          </el-table-column>
          <el-table-column
            prop="count"
            label="使用次数"
            width="280">
          </el-table-column>
      </el-table>
  </div>

</template>

<script>
	export default{
		name: `SellManage`,
    mounted(){
        this.$nextTick( () =>{
          console.log(this.$route.params.lotId);
          this.loadUseReport();
        })
    },
		data(){
			return{
				month:'',
        benefitData: [
          // {
          //   rend: 100,
          //   totalBenefit: 2000,
          //   expectBenefit: 20000
          // }, {
          //   rend: 100,
          //   totalBenefit: 2000,
          //   expectBenefit: 20000
          // }, {
          //   rend: 100,
          //   totalBenefit: 2000,
          //   expectBenefit: 20000
          // }, {
          //   rend: 100,
          //   totalBenefit: 2000,
          //   expectBenefit: 20000
          // }
          ],
				useData: []
			}
		},
		methods:{
      //改变月份重新查看报表
			changeMonth(){
          console.log(new Date(this.month).getTime());
          console.log(this.$route.params.lotId);
          this.loadUseReport();
			},
      //查看使用率报表
      loadUseReport(){

           this.axios.post( this.baseURI + `/parkingRecord/usageOfParkingCard`,{
               id: this.$route.params.lotId,
               firstDate: this.month
           })
           .then( res =>{
               console.log(res);
               this.useData = res.data.data;
           })
           .catch( err =>{
               console.log(err);
           })
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