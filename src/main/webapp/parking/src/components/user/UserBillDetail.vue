<template>
	<div class="user-bill">
		<div class="user-bill-tit">账单详情</div>
    <div class="user-bill-detail">
      <div><span>账单编号：</span>{{$route.params.billNum}}</div>
      <div><span>卡号：</span>{{billData.cardNum}}</div>
      <div><span>停车场：</span>{{billData.parkingName}}</div>
      <div><span>单价：</span>{{billData.price}}元/月</div>
      <div><span>总费用：</span>{{billData.account}}元</div>
      <div><span>计费时间段：</span>{{secondToDate(billData.firstDate) + "~" + secondToDate(billData.statementDate)}}</div>
      <div><span>出账日期：</span>{{secondToDate(billData.statementDate)}}</div>
      <div v-if="billData.flag==1">
        <div><span>支付账单编号：</span>{{billPayed.payNum}}</div>
        <div><span>支付手机号：</span>{{billPayed.payPhone}}</div>
        <div><span>支付收款方：</span>{{billPayed.payReceive}}</div>
        <div><span>支付金额：</span>{{billPayed.payPrice}}</div>
        <div><span>支付时间：</span>{{billPayed.payTime}}</div>
      </div>
      <el-button type="primary" @click="payingVisible = true; payState = 0;" v-if="billData.flag == 0 || billData.flag == 3">{{payArr[0]}}</el-button><!--  -->
      <el-button type="primary" v-else disabled> {{payArr[billData.flag]}}</el-button>
      <router-link to="/Userindex/UserBill"> 返回查看所有帐单</router-link>
    </div>
    <!-- 缴费弹窗 -->
    <el-dialog class="dialog-width" title="用户缴费" :visible.sync="payingVisible" width="30%">
      <div style="color: #f00;" v-if="payingVisible">停车场已满,缴费后停车卡无法激活!</div>
      <div v-if="payState == 0">
        <div><span>用户账号:</span>{{billPaying.userPhone}}</div>
        <div><span>账单卡号:</span>{{billPaying.cardNum}}</div>
        <div><span>账单编号:</span>{{billPaying.billNum}}</div>
        <div><span>账单金额:</span>{{billPaying.billPrice}}</div>
        <div class="dialog-footer">
          <el-button @click="payingVisible = false">取 消</el-button>
          <el-button type="primary" @click="payforBill()">确 定</el-button>
        </div>
      </div>
        <!-- 支付成功显示信息 -->
      <div v-if="payState == 1">
        <div><span>支付账单编号:</span>{{billPayed.payNum}}</div>
        <div><span>支付手机号:</span>{{billPayed.payPhone}}</div>
        <div><span>支付收款方:</span>{{billPayed.payReceive}}</div>
        <div><span>支付金额:</span>{{billPayed.payPrice}}</div>
        <div><span>支付时间:</span>{{billPayed.payTime}}</div>
        <div class="dialog-footer">
          <el-button type="primary" @click="payingVisible = false">确 定</el-button>
        </div>
      </div>
        <!-- 支付失败显示信息 -->
      <div v-if="payState == 2">
        <div><span>支付手机号:</span>{{billPayError.payPhone}}</div>
        <div><span>错误代码:</span>{{billPayError.payError}}</div>
        <div><span>错误提示:</span>{{billPayError.ErrorTip}}</div>
        <div><span>支付时间:</span>{{billPayError.payTime}}</div>
        <div class="dialog-footer">
          <el-button type="primary" @click="payingVisible = false">确 定</el-button>
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
        billPaying:{},
        billPayed:{
          payNum:20180514111111,
          payPhone:13012341234,
          payReceive:'manager',
          payPrice:6.67,
          payTime:`2018-6-11`
        },
        billPayError:{
          payPhone:13012341234,
          payError:`代码错误404`,
          ErrorTip:`支付失败`,
          payTime:`2018-6-11`
        },
        payingVisible:false,
        payState:0,
        //缴费样式数组
        payArr:[`缴费`,`已缴费`,`未出账`],
			}
		},
    mounted: function() {
      this.$nextTick(function () {
        this.billPaying.userPhone=sessionStorage.phone;
        this.billPaying.billNum=this.$route.params.billNum;
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
            console.log(res);
            this.billData = res.data.data;
            this.billPaying.billPrice=this.billData.account;
            this.billPaying.cardNum=this.billData.cardNum;
          })
          .catch(err => {
            console.log(err)
          })
      },
      //支付账单
      payforBill(){
        // this.$message({
        //   type: 'error',
        //   message: '停车场已满,缴费后直接注销停车卡!'
        // });
        // this.$message({
        //         type: 'error',
        //         message: '缴费失败!'
        //       });
        // this.payState=1;
        // this.$confirm('是否支付账单?', '提示', {
        //   confirmButtonText: '确定',
        //   cancelButtonText: '取消',
        // }).then(() => {
       
         /* this.axios.post(this.baseURI + '/parkingBill/payBill',{billNum: this.$route.params.billNum})
          .then( res => {
            if(res.data.message == "OK"){
              this.$message({
                type: 'success',
                message: '缴费成功!'
              });
              this.billData.flag=1;
              this.payState=1;
              this.billPayed=res.data.data;
              this.initBillDetail();
            }else{
              this.$message({
                type: 'error',
                message: '缴费失败!'
              });
              this.payState=2;
              this.billPayError=res.data.data;
            }
          })*/
          
        // })  
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