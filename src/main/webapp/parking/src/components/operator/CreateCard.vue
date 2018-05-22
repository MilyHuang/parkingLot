<template>
  <div class="create-card">
    <div>
      <el-input v-model="searchNumber" placeholder="请输入手机号"></el-input>
      <el-button type="primary" @click="SelectUser()">查找</el-button>
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
          <el-button class="deal-button" type="primary" @click="CreateCard()">办理</el-button>
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
      searchNumber: ``,
      cardInfo: {
        name: `chris`,
        phoneNumber: `123456`,
        lotNumber: `p111111`,
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
        return false
      } else {
        this.axios.post(baseURI + '/parkingCard/selectUserInfoByPhone', { "phone": this.searchNumber})
          .then(res => {
            console.log(res.data);
            var data = res.data;
            if (data.state == 1) {
              this.cardInfo.username=data.username;
              this.cardInfo.phoneNumber=data.phone;
            }
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    createOldCard(id){
      this.axios.post(baseURI + '/parkingCard/createNewParkingByOldUser', { "userId":id,"parkingNum": this.cardInfo.usertype,"cardNum": this.cardInfo.usertype })
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err)
        })
    },
    createNewCard() {
      if (this.cardInfo.name && this.cardInfo.phoneNumber && this.cardInfo.lotNumber && this.cardInfo.cardAccount && this.cardInfo.password) {
        this.axios.post(baseURI + '/parkingCard/createNewParkingCard', { "username": this.cardInfo.username, "phone": this.cardInfo.password, "password": this.cardInfo.usertype,"parkingNum": this.cardInfo.usertype,"cardNum": this.cardInfo.usertype })
          .then(res => {
            console.log(res);
            this.createOldCard(id);
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
        return false
      }
    },
  }
}

</script>
<style scoped>
.create-card {
  overflow: hidden;
  text-align: center;
}

.el-input {
  display: inline-block;
  width: 270px;
  margin-bottom: 30px;
  margin-left: 40px;
  padding: 0;
}

.deal-button {
  margin-left: 20%;
  width: 140px;
}

</style>
