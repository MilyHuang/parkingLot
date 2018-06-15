<template>
  <div class="login-bg">
    <div class="login-box">
      <div class="login-logo"><img src="../../assets/login_logo.png" /></div>
      <div class="login">
        <form method="post">
          <el-input v-model="userInfo.phone" type="text" placeholder="手机号码" @keyup.13="doLogin"></el-input>
          <el-input v-model="userInfo.password" type="password" placeholder="密码" @keyup.13="doLogin"/>
          <el-button type="primary" @click="doLogin">登录</el-button>
        </form>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      userInfo: {
        phone: '',
        password: '',
      }
    };
  },
  methods: {
    doLogin() {
      if (this.userInfo.phone == '') {
        alert('手机号码不能为空');
        return false
      }
      if (this.userInfo.password == '') {
        alert('密码名不能为空');
        return false
      }else{
        this.axios.post(this.baseURI + '/userinfo/login',{"phone":this.userInfo.phone,"password":this.userInfo.password})
        .then(res => {
          if (res.status == 200) {
            var data=res.data.data
            if(data){
              this.$notify({
                title: '提示信息',
                message: '登录成功',
                type: 'success'
              });
              sessionStorage.setItem("phone",this.userInfo.phone);
              sessionStorage.setItem("token",this.userInfo.username);
              sessionStorage.setItem("role",3);
              this.$router.push({ path: '/UserIndex' });
            }
            else{
              this.$notify({
                title: '提示信息',
                message: '账号或密码错误',
                type: 'error'
              });
            }
          }else{
            this.$notify({
              title: '提示信息',
              message: '登录失败',
              type: 'error'
            });
          }
        })
        .catch(err => {
          console.log(err);
          this.$notify({
            title: '提示信息',
            message: '登录失败',
            type: 'error'
          });
        })
      }
    }
  }
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
a {
  color: #27A9E3;
  text-decoration: none;
  cursor: pointer;
}

img {
  border: none;
  cursor: pointer;
}

.login-bg {
  width: 100%;
  height: 100%;
  background-color: #98d0f2;
}

.login-box {
  position: absolute;
  top: 25%;
  left: 50%;
  width: 350px;
  height: 370px;
  margin-left: -170px;
  background-color: #fff;
  border-radius: 10px;
}

.login {
  width: 280px;
  height: 250px;
  margin: 0 auto;
  margin-top: 100px;
}

.login-logo {
  position: absolute;
  top: -60px;
  right: 110px;
  width: 120px;
  height: 120px;
  border: 5px solid #98d0f2;
  border-radius: 100px;
  background: #fff;
  text-align: center;
  line-height: 150px;
}

.el-radio {
  margin-top: 20px;
  margin-bottom: 25px;
  margin-left: 6px;
}

.el-button {
  width: 100%;
}

.el-input {
  margin: 10px 0;
}

.user-type {
  display: inline-block;
  width: auto;
  margin: 30px auto;
  color: #888;
}

</style>
