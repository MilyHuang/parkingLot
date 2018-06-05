<template>
  <div class="create-card">
    
      <el-input v-model="searchNumber" placeholder="请输入手机号"></el-input>
      <el-button type="primary" @click="SelectUser()">查找</el-button>
      <div class="tip" v-show="isDisabled">今日办卡业务暂停</div>
      <div v-if="changeFlag">
      <el-form ref="cardInfo" :model="cardInfo" label-width="50px"  :disabled="isDisabled">
        <el-form-item>
          <span>用户名</span>
          <el-input v-model="cardInfo.name"></el-input>
        </el-form-item>
        <el-form-item>
          <span>电话</span>
          <el-input v-model="cardInfo.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item>
          <span>停车场编号</span>
          <el-input v-model="cardInfo.lotNumber"></el-input>
        </el-form-item>
        <el-form-item>
          <span>卡号</span>
          <el-input v-model="cardInfo.cardAccount"></el-input>
        </el-form-item>
        <el-form-item>
          <span>密码</span>
          <el-input type="password" v-model="cardInfo.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="deal-button" type="primary" @click="createNewCard()">新用户办理</el-button>
        </el-form-item>
      </el-form>
      </div>
      <div v-else="changeFlag">
       <el-form ref="oldInfo" :model="oldInfo" label-width="50px" :disabled="isDisabled">
        <el-form-item>
          <span>用户名</span>
          <el-input v-model="oldInfo.username" disabled></el-input>
        </el-form-item>
        <el-form-item>
          <span>电话</span>
          <el-input v-model="oldInfo.phone" disabled></el-input>
        </el-form-item>
        <el-form-item>
          <span>停车场编号</span>
          <el-input v-model="oldInfo.lotNumber"></el-input>
        </el-form-item>
        <el-form-item>
          <span>卡号</span>
          <el-input v-model="oldInfo.cardAccount"></el-input>
        </el-form-item>
        <div class="cardCount">该用户已办卡数：{{cardCount}}</div>
        <el-form-item>
          <a href="#" @click="changeFlag = true">为其他用户办理新卡</a>
          <el-button class="deal-button" type="primary" @click="createOldCard()">老用户办理</el-button>
        </el-form-item>
      </el-form>  
      </div>
  </div>
</template>
<script>
export default {
  name: `CreateCard`,
  data() {
    return {
      // 新老用户
      changeFlag: true,
      //输入框禁用
      isDisabled:false,
      isPass:false,
      // 老用户信息
      oldInfo: {
        id: ``,
        username: ``,
        phone: ``,
        lotNumber: ``,
        cardAccount: ``
      },
      //搜索信息
      searchNumber: ``,
      //老用户卡数
      cardCount: ``,
      cardInfo: {
        name: ``,
        phoneNumber: ``,
        lotNumber: ``,
        cardAccount: ``,
        password: ``
      },
      formLabelAlign: {
        name: '123',
        region: '456',
        type: '789'
      }
    }
  },
  mounted: function() {
    //token路由判断角色跳转
    this.$nextTick(function () {
      this.inputDisabled();
      this.spaceLimit();
    })
  },
  methods:{
    spaceLimit(){
      var input=document.getElementsByTagName("input");
      console.log(input);
      for(var i=0;i<input.length;i++){
        input[i].onkeyup=function(){
          this.value=this.value.replace(/^ +| +$/g,'');
        }
      }
    },
    inputDisabled(){
      var date=new Date();
      var nowDate=date.getMonth()+1+'/'+date.getDate();
      if(nowDate=='3/31'/*nowDate=='5/25'*/||nowDate=='6/30'||nowDate=='9/30'||nowDate=='12/31'){
        console.log(nowDate);
        this.isDisabled=true;
      }
    },
    newInputLimit(info){
      if(!(info.name && info.phoneNumber && info.lotNumber && info.cardAccount && info.password)){
        this.$notify({
          title: '提示信息',
          message: '请填写完整',
          type: 'error'
        });
      }
      else if(!(/^1[34578]\d{9}$/.test(info.phoneNumber))){
        this.$notify({
          title: '提示信息',
          message: '手机号码格式错误',
          type: 'error'
        });
      }else if(!(/^[0-9]*$/.test(info.cardAccount))){
        this.$notify({
          title: '提示信息',
          message: '卡号必须为数字',
          type: 'error'
        });
      }else{
        this.isPass=true;
      }
    },
    oldInputLimit(info){
      if(!(info.lotNumber && info.cardAccount)){
        this.$notify({
          title: '提示信息',
          message: '请填写完整',
          type: 'error'
        });
      }else if(!(/^[0-9]*$/.test(info.cardAccount))){
        this.$notify({
          title: '提示信息',
          message: '卡号必须为数字',
          type: 'error'
        });
      }else{
        this.isPass=true;
      }
    },
    SelectUser(){
      if (this.searchNumber == '') {
        this.$notify({
          title: '提示信息',
          message: '请填写完整',
          type: 'error'
        });
        return false;
      } else {
        this.axios.post(this.baseURI + '/parkingCard/selectUserInfoByPhone', { "phone": this.searchNumber})
          .then(res => {
            console.log(res.data);
            var data = res.data;
            if (data.state == 1) {
              this.oldInfo = data.data.user;
              this.cardCount = data.data.count;
              this.changeFlag = false;
            }
            else{
             this.$notify({
              title: '提示信息',
              message: data.message,
              type: 'error'
            });
            }
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    //老用户注册
    createOldCard(){
      //表单检验
      this.oldInputLimit(this.oldInfo);
      if(this.isPass)
      this.axios.post(this.baseURI +'/parkingCard/createNewParkingByOldUser', { "userId": this.oldInfo.id,"parkingNum": this.oldInfo.lotNumber,"cardNum": this.oldInfo.cardAccount})
        .then(res => {
          console.log(res);
          if(res.data.message == `OK`){
              this.$notify({
                    title: '提示',
                    message: '老用户办卡成功',
                    type: 'success'
              });
              //更新卡数
              this.cardCount = res.data.data;
              this.isPass=false;
          }else if(res.data.state == 0){
            this.$notify({
                    title: '提示',
                    message: res.data.message,
                    type: 'error'
              });
          }
        })
        .catch(err => {
          console.log(err);
        })
    },
    //新用户注册
    createNewCard() {
      //表单检验
      this.newInputLimit(this.cardInfo);
      if(this.isPass)
        this.axios.post(this.baseURI + '/parkingCard/createNewParkingCard', { "username": this.cardInfo.name, "phone": this.cardInfo.phoneNumber, "password": this.cardInfo.password,"parkingNum": this.cardInfo.lotNumber,"cardNum": this.cardInfo.cardAccount})
          .then(res => {
            console.log(res);
            //判断为老用户
            if(res.data.data.user.id){
               this.oldInfo = res.data.data.user;
               this.cardCount = res.data.data.count;
               //显示老用户界面
               this.changeFlag = false;
               //加载老用户信息
               
            }
            else if(res.data.state == 0){
              this.$notify({
                    title: '提示',
                    message: res.data.message,
                    type: 'error'
              });
            }
            //新用户注册成功
            else{
              this.$notify({
                    title: '提示',
                    message: '新用户注册成功',
                    type: 'success'
              });

            }
           this.isPass=false;
          })
          .catch(err => {
            console.log(err)
          })
    },
  }
}

</script>
<style scoped>
.tip{
  margin-bottom: 20px;
  color: #f00;
}
.create-card {
  overflow: hidden;
  text-align: center;
  min-width: 700px;
}

/* 文字对齐 */
.el-form span{
  position: absolute;
}

/* 文本框对齐 */
.el-input {
  display: inline-block;
  width: 290px;
  margin-bottom: 30px;
  margin-left: 105px;
  padding: 0;
}

.deal-button {
  margin-left: 20%;
  width: 140px;
}

.user-radio{
  margin-top: 10px;
  margin-bottom: 30px;
}

.cardCount{
  color: red;
  margin-bottom: 30px;
}
</style>
