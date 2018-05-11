<template>
  <div class="login-bg">
    <div class="login_box">
      <div class="login_logo"><a href="#"><img src="../../assets/login_logo.png" /></a></div>
      <div class="login">
        <form method="post">
          <el-input v-model="userInfo.username" type="text" placeholder="用户名" @keyup.13="doLogin"></el-input>
          <el-input v-model="userInfo.password" type="password" placeholder="密码" @keyup.13="doLogin" />
          <el-radio-group v-model="radio" @change="userTypeChoose">
            <el-radio :label="1">管理员</el-radio>
            <el-radio :label="2">运营商</el-radio>
            <el-radio :label="3">操作员</el-radio>
            <el-radio :label="4">用户</el-radio>
          </el-radio-group>
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
      radio: 1,
      userInfo: {
        username: '',
        password: '',
        usertype: '1'
      }
    };
  },
  methods: {
    userTypeChoose(value) {
      this.userInfo.usertype = value;
      console.log(this.userInfo.usertype);
    },
    doLogin() {
      if (this.userInfo.username == '') {
        alert('用户名不能为空');
        return false
      }
      if (this.userInfo.password == '') {
        alert('密码名不能为空');
        return false
      }else{
        this.$router.push({ path: '/AdminIndex' });
      }
      
      // axios.post('/login', JSON.stringify(this.userInfo))
      //   .then(res => {
      //     console.log(res)
      //     if (res.status == 200) {
      //       this.$store.commit('setToken', res.data);
      //       localStorage.userName = this.userInfo.userName;
      //       localStorage.token_expire = res.data.expire;
      //       localStorage.token = res.data.token;
      //       this.$notify({
      //         title: '提示信息',
      //         message: '登录成功',
      //         type: 'success'
      //       });
      //       this.$router.push({ path: '/AdminIndex' })
      //     } else {
      //       this.$notify({
      //         title: '提示信息',
      //         message: '账号或密码错误',
      //         type: 'error'
      //       });
      //     }
      //   })
      //   .catch(err => {
      //     console.log(err)
      //   })
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
}

.login-bg {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  background-color: #98d0f2;
}

.login_box {
  position: absolute;
  top: 200px;
  left: 50%;
  width: 350px;
  height: 370px;
  margin-left: -200px;
  background-color: #fff;
  border-radius: 10px;
}

.login {
  width: 280px;
  height: 250px;
  margin: 0 auto;
  margin-top: 100px;
}

.login_logo {
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
