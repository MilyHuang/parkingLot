<template>
	<div class="user-info">
    <div class="user-card">
      <h3>我的账单</h3>
      <el-table
          :data="billData"
          style="width: 95%">
          <el-table-column
            label="账单编号"
            width="120"
            class="card-active">
            <template slot-scope="scope">
              <!-- 未出帐禁用点击 -->
              <!-- <span v-if="scope.row.flag == 2">{{ scope.row.billNum }}</span> -->
              <!-- 查看帐单 -->
              <router-link :to="{ name:'UserBillDetail', params: {billNum: scope.row.billNum}}" class="card-active">{{ scope.row.billNum }} </router-link> 
            </template>
          </el-table-column>
          <el-table-column
            prop="cardNum"
            label="卡号"
            width="125">
          </el-table-column>
          <el-table-column
            prop="price"
            label="单价（元/月）"
            width="120">
          </el-table-column>
          <el-table-column
            prop="account"
            label="总费用（元）"
            width="110">
          </el-table-column>
          <el-table-column
            prop="parkingName"
            label="停车场"
            width="115">
          </el-table-column>
          <el-table-column
            label="出账日期"
            width="115">
             <template slot-scope="scope">
              <span>{{secondToDate(scope.row.statementDate)}}</span>
             </template>
          </el-table-column>
          <el-table-column
            label="状态">
            <template slot-scope="scope">
              <!-- 显示未缴费状态 -->
              <span v-if="scope.row.flag == 0 || scope.row.flag == 3" :class="{unpaid:scope.row.flag == 0 || scope.row.flag == 3}">{{ payArr[0] }}</span>
              <!-- 显示未出账和已交费 -->
              <span v-else>{{ payArr[scope.row.flag] }}</span>
              <!-- 显示提示信息 -->
              <span v-if="scope.row.flag == 0 || scope.row.flag == 3"  class="tip">{{ '（' + scope.row.tis + '）'}}</span>
            </template>
          </el-table-column>
           <!-- <el-table-column>
            <template slot-scope="scope">
              <span class="tip">{{ scope.row.tip }}</span>
            </template>
          </el-table-column> -->
        </el-table>
    </div>
    <router-view></router-view>
	</div>
</template>

<script>
	export default{
		name: `UserBill`,
		data(){
			return{
				billData:[
				  // {
				  //  	billNum: 201805,
				  //  	cardNum: 88888888,
						// singlePrice: 100,
						// totalPrice: 300,
						// lotName:`A停车场`,
						// outBillTime:`2018-6-30`,
      //       //0为未缴费
						// state: 0,
      //       tip: `请在2018/12/30前缴费`
				  // },
				  // {
				  //  	billNum: 201804,
				  //  	cardNum: 22222222,
						// singlePrice: 100,
						// totalPrice: 100,
						// lotName:`A停车场`,
						// outBillTime:`2017-6-30`,
      //       //1为已缴费
						// state: 1,
      //       tip: ``
				  // },
				  // {
				  //  	billNum: 201806,
				  //  	cardNum: 11111111,
						// singlePrice: 100,
						// totalPrice: 300,
						// lotName:`B停车场`,
						// outBillTime:`2018-9-30`,
      //       //2为未出账
						// state: 2,
      //       tip: ``
				  // }
				],
        //缴费样式数组
        payArr:[`未缴费`,`已缴费`,`未出账`,`欠费`],
			}
		},
    mounted: function() {
      this.$nextTick(function () {
        this.initBillList();
      })
    },
    methods:{
      //加载账单
      initBillList(){
        this.axios.post(this.baseURI + '/parkingBill/selectAllParkingBillEntity', { "phone": sessionStorage.getItem("phone")})
          .then(res => {
            // sessionStorage.getItem("phone")
            console.log(res);
            this.billData = res.data.data;
          })
          .catch(err => {
            console.log(err)
          })
      },
      //时间转换函数
      secondToDate(date){
          let res = new Date(date).toLocaleString();
          return res.slice(0,res.indexOf(' '));
      }
    },
    
	}
</script>

<style scoped>
.user-info{
 min-width: 1000px;
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
.tip{
  color: red;
  font-weight: bold;
}
/* 未缴费 */
.unpaid{
  color: blue;
  font-weight: bold;
}
</style>