<template>
	<div class="user-bill">
		<div class="user-bill-tit">账单详情</div>
    <div class="uesr-bill-detail">
      <div><span>账单编号：</span>{{billData.billNum}}</div>
      <div><span>卡号：</span>{{billData.cardNum}}</div>
      <div><span>停车场：</span>{{billData.lotName}}</div>
      <div><span>单价：</span>{{billData.singlePrice}}元/月</div>
      <div><span>总费用：</span>{{billData.totalPrice}}元</div>
      <div><span>计费时间段：</span>{{billData.billTime}}</div>
      <div><span>出账日期：</span>{{billData.outBillTime}}</div>
      <el-button type="primary" >{{billData.state}}</el-button>
    </div>
	</div>
</template>

<script>
	export default{
		name: `UserInfo`,
		data(){
			return{
				billData:{
          billNum: 20180514111111,
          cardNum: 88888888,
          singlePrice: 100,
          totalPrice: 300,
          lotName:`A停车场`,
          billTime:`2018-3-31~2018-6-30`,
          outBillTime:`2018-6-30`,
          state: `未缴费`
        }
			}
		},
    mounted: function() {
      this.$nextTick(function () {
        this.initBillDetail();
      })
    },
    methods:{
      initBillDetail(){
        this.axios.post('', { "billNum": this.$route.params.billNum})
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
</style>