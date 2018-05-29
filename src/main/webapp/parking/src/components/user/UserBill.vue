<template>
	<div class="user-info">
    <div class="user-card">
      <h3>我的账单</h3>
      <el-table
          :data="billData"
          style="width: 90%">
          <el-table-column
            label="账单编号"
            width="150"
            class="card-active">
            <template slot-scope="scope">
            	<router-link :to="{ name:'UserBillDetail', params: {billNum: scope.row.billNum} }" class="card-active" >{{ scope.row.billNum }} </router-link>
            </template>
          </el-table-column>
          <el-table-column
            prop="cardNum"
            label="卡号"
            width="130">
          </el-table-column>
          <el-table-column
            prop="singlePrice"
            label="单价（元/月）"
            width="120">
          </el-table-column>
          <el-table-column
            prop="totalPrice"
            label="总费用（元）"
            width="120">
          </el-table-column>
          <el-table-column
            prop="lotName"
            label="停车场"
            width="120">
          </el-table-column>
          <el-table-column
            prop="outBillTime"
            label="出账日期"
            width="120">
          </el-table-column>
           <el-table-column
            prop="state"
            label="状态">
          </el-table-column>
        </el-table>
    </div>
    <router-view></router-view>
	</div>
</template>

<script>
	export default{
		name: `UserInfo`,
		data(){
			return{
				billData:[
				  {
				   	billNum: 20180514111111,
				   	cardNum: 88888888,
						singlePrice: 100,
						totalPrice: 300,
						lotName:`A停车场`,
						outBillTime:`2018-6-30`,
						state: `未缴费`
				  },
				  {
				   	billNum: 20180514111111,
				   	cardNum: 22222222,
						singlePrice: 100,
						totalPrice: 100,
						lotName:`A停车场`,
						outBillTime:`2017-6-30`,
						state: `已缴费`
				  },
				  {
				   	billNum: 20180514111111,
				   	cardNum: 11111111,
						singlePrice: 100,
						totalPrice: 300,
						lotName:`B停车场`,
						outBillTime:`2018-9-30`,
						state: `未出账`
				  }
				]
			}
		},
    mounted: function() {
      this.$nextTick(function () {
        this.initBillList();
      })
    },
    methods:{
      initBillList(){
        this.axios.post('', { "cardNum": this.$route.params.cardNum})
          .then(res => {
            console.log(res);
          })
          .catch(err => {
            console.log(err)
          })
      },
    }
	}
</script>

<style scoped>
	.user-info{
       min-width: 900px;
	}
	.user-card h3{
		margin-top: 40px;
		margin-bottom: 30px;
		width: 80%;
		text-align: center;
	}
	.user-telephone{
		font-size: 1.3;
		font-weight: bold;
		margin-top: 20px;
		margin-bottom: 20px;
	}
	.card-active{
        cursor: pointer;
        color: #98d0f2;
	}
</style>