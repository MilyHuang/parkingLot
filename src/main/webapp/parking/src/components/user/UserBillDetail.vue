<template>
	<div class="user-bill">
		<div class="user-bill-tit">账单详情</div>
    <div class="user-bill-detail">
      <div style="color: #f00;" v-if="iParkingFull"><span>提示 : </span>停车场已满,停车卡无法续用,可前往办卡点注销!</div>
      <div><span>账单编号：</span>{{$route.params.billNum}}</div>
      <div><span>卡号：</span>{{billData.cardNum}}</div>
      <div><span>停车场：</span>{{billData.parkingName}}</div>
      <div><span>单价：</span>{{billData.price}}元/月</div>
      <div><span>总费用：</span>{{billData.account}}元</div>
      <div><span>计费时间段：</span>{{secondToDate(billData.firstDate) + "~" + secondToDate(billData.statementDate)}}</div>
      <div><span>出账日期：</span>{{secondToDate(billData.statementDate)}}</div>

      <el-button type="primary" @click="payingVisible=true" v-if="billData.flag == 0 || billData.flag == 3" :disabled="iParkingFull">{{payArr[0]}}</el-button><!--  -->
      <el-button type="primary" v-else disabled> {{payArr[billData.flag]}}</el-button>

      <router-link to="/Userindex/UserBill"> 返回查看所有帐单</router-link>
    </div>
    <!-- 缴费弹窗 -->
    <el-dialog class="dialog-width" title="用户缴费" :visible.sync="payingVisible" width="30%">
      <div>
        <div><span>账单编号 : </span>{{$route.params.billNum}}</div>
        <div>
          <span>银行支付码 : </span>
          <el-input v-model="payCode"></el-input>
        </div>
        <div class="dialog-footer">
          <el-button @click="payingVisible = false">取 消</el-button>
          <el-button type="primary" @click="payforBill()">确 定</el-button>
        </div>
      </div>
    </el-dialog>
	</div>
</template>

<script>
	export default{
		name: `UserInfo`,
		data(){
			return{
				billData:{},
        payCode:null,
        payingVisible:false,
        iParkingFull:false,
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
      //加载账单
      initBillDetail(){
        this.axios.post(this.baseURI + '/parkingBill/selectParkingBillByBillNum', { "billNum": this.$route.params.billNum})
          .then(res => {
            //账单详情页面显示
            this.billData = res.data.data;
            this.parkingFull();
          })
          .catch(err => {
            console.log(err)
          })
      },
      parkingFull(){
        if(this.billData.flag==3){
          this.axios.post(this.baseURI + '/parkingBill/isParkingLotFull',{billNum: this.$route.params.billNum})
          .then( res => {
            if(res.data.data){
              this.iParkingFull=true;
            }
          });
        }
      },
      //支付账单
      payforBill(){
        if(!this.payCode){
          this.$notify({
            title: '提示信息',
            message: '请填写完整!',
            type: 'error'
          });
        }else{
          this.axios.post(this.baseURI + '/parkingBill/payBill',{billNum: this.$route.params.billNum,paymentCode:this.payCode})
          .then( res => {
            console.log(res.data.data);
            if(res.data.data.redeemResultCode==0){
              this.$message({
                type: 'success',
                message: '缴费成功!'
              });
              this.payingVisible = false;
              this.billData.flag=1;
              this.initBillDetail();
            }else{
              this.$message({
                type: 'error',
                message: '缴费失败'
              });
            }
          })
        }
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
  .user-bill-detail{
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