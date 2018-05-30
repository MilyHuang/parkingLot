<template>
	<div class="user-bill">
		<div class="user-bill-tit">账单详情</div>
    <div class="uesr-bill-detail">
      <div><span>账单编号：</span>{{$route.params.billNum}}</div>
      <div><span>卡号：</span>{{billData.cardNum}}</div>
      <div><span>停车场：</span>{{billData.parkingName}}</div>
      <div><span>单价：</span>{{billData.price}}元/月</div>
      <div><span>总费用：</span>{{billData.account}}元</div>
      <div><span>计费时间段：</span>{{secondToDate(billData.firstDate) + "~" + secondToDate(billData.statementDate)}}</div>
      <div><span>出账日期：</span>{{secondToDate(billData.statementDate)}}</div>
      <el-button type="primary" v-if="billData.flag == 0" @click="payforBill()">{{payArr[billData.flag]}}</el-button>
      <el-button type="primary" v-else disabled>{{payArr[billData.flag]}}</el-button>
      <router-link to="/Userindex/UserBill"> 返回查看所有帐单</router-link>
    </div>
	</div>
</template>

<script>
	export default{
		name: `UserInfo`,
		data(){
			return{
				billData:{
          // billNum: 20180514111111,
          // cardNum: 88888888,
          // singlePrice: 100,
          // totalPrice: 300,
          // lotName:`A停车场`,
          // billTime:`2018-3-31~2018-6-30`,
          // outBillTime:`2018-6-30`,
          // state: `未缴费`
        },
         //缴费样式数组
        payArr:[`缴费`,`已缴费`,`未出账`],
			}
		},
    mounted: function() {
      this.$nextTick(function () {
        // if(!this.$route.params.billNum)
        //   this.$router.push("/Userindex/UserBill");
        this.initBillDetail();
      })
    },
    methods:{
      initBillDetail(){
        this.axios.post(this.baseURI + '/parkingBill/selectParkingBillByBillNum', { "billNum": this.$route.params.billNum})
          .then(res => {
            console.log(res);
            this.billData = res.data.data;
          })
          .catch(err => {
            console.log(err)
          })
      },
      payforBill(){
          console.log(交房租了);
      },
      //时间转换函数
      secondToDate(date){
          let res = new Date(date).toLocaleString();
          return res.slice(0,res.indexOf(' '));
      }
    }
	}
</script>

<style scoped>
	.user-bill{
    min-width: 900px;
	}
	.user-bill-tit{
		font-size: 25px;
		font-weight: bold;
		margin-top: 20px;
    margin-left: 50px;
		margin-bottom: 20px;
	}
  .uesr-bill-detail{
    margin-left: 50px;
    font-size: 18px;
    line-height: 36px;
  }
  .el-button{
    margin-top: 30px;
  }
  a{
    margin-left: 80px;
    font-size: 14px;
    vertical-align: -10px;
  }
</style>