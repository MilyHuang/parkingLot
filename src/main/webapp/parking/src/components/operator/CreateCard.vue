<template>
  <div class="create-card">
    
      <el-input v-model="searchNumber" placeholder="请输入手机号"></el-input>
      <el-button type="primary" @click="SelectUser()">查找</el-button>
      <div v-if="changeFlag">
      <el-form ref="cardInfo" :model="cardInfo" label-width="50px">
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
          <el-button class="deal-button" type="primary" @click="createNewCard()">办理</el-button>
        </el-form-item>
      </el-form>
      </div>
      <div v-else="changeFlag">
       <el-form ref="oldInfo" :model="oldInfo" label-width="50px">
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
        <el-form-item>
          <el-button type="primary" @click="changeFlag = true">返回</el-button>
          <el-button class="deal-button" type="primary" @click="createOldCard()">办理</el-button>
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
      cardInfo: {
        name: `YongKang`,
        phoneNumber: `110`,
        lotNumber: `001`,
        cardAccount: `85280110`,
        password: `12345678`
      },
      formLabelAlign: {
        name: '123',
        region: '456',
        type: '789'
      }
    }
  },
  methods:{
    SelectUser(){
      if (this.searchNumber == '') {
        this.$notify({
          title: '提示信息',
          message: '请填写完整',
          type: 'error'
        });
        return false;
      } else {
        this.axios.post('http://10.65.35.180:8080/parkingLot/parkingCard/selectUserInfoByPhone', { "phone": this.searchNumber})
          .then(res => {
            console.log(res.data);
            var data = res.data;
            if (data.state == 1) {
              this.oldInfo = data.data;
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
      this.axios.post('http://10.65.35.180:8080/parkingLot/parkingCard/createNewParkingByOldUser', { "userId": this.oldInfo.id,"parkingNum": this.oldInfo.lotNumber,"cardNum": this.oldInfo.cardAccount})
        .then(res => {
          if(res.data.message == `OK`){
              this.$notify({
                    title: '提示',
                    message: '老用户注册成功',
                    type: 'success'
              });
          }
        })
        .catch(err => {
          console.log(err);
        })
    },
    //新用户注册
    createNewCard() {
      if (this.cardInfo.name && this.cardInfo.phoneNumber && this.cardInfo.lotNumber && this.cardInfo.cardAccount && this.cardInfo.password) {
        this.axios.post('http://10.65.35.180:8080/parkingLot/parkingCard/createNewParkingCard', { "username": this.cardInfo.name, "phone": this.cardInfo.phoneNumber, "password": this.cardInfo.password,"parkingNum": this.cardInfo.lotNumber,"cardNum": this.cardInfo.cardAccount})
          .then(res => {
            console.log(res);
            //判断为老用户
            if(res.data.data){
               this.oldInfo = res.data.data;
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
           
          })
          .catch(err => {
            console.log(err)
          })
        }else{
          this.$notify({
          title: '提示信息',
          message: '请填写完整',
          type: 'error'
        });
        return false;
      }
    },
  }
}

</script>
<style scoped>
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
</style>
