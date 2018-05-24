<template>
  <div class="login-bg">
    <div class="login_box">
      <div class="login_logo"><img src="../../assets/login_logo.png" /></div>
      <div class="login">
        <form method="post">
          <el-input v-model="userInfo.username" type="text" placeholder="用户名" @keyup.13="doLogin"></el-input>
          <el-input v-model="userInfo.password" type="password" placeholder="密码" @keyup.13="doLogin" />
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
        username: '',
        password: '',
      }
    };
  },
  mounted: function() {
    //token路由判断角色跳转
    this.$nextTick(function () {
      if (!sessionStorage.token) { 
        this.RoleChange(sessionStorage.token.role);
      }
    })
  },
  methods: {
    //登录角色跳转
    RoleChange(role){
      switch(role)
      {
        case 0:
          this.$router.push({ path: '/AdminIndex' });
          break;
        case 1:
          this.$router.push({ path: '/ManagerIndex'});
          break;
        case 2:
          this.$router.push({ path: '/OperatorIndex'});
          break;
        default:
          console.log('error');
      }
    },
    doLogin() {
      if (this.userInfo.username == ''||this.userInfo.password == '') {
        this.$notify({
          title: '提示信息',
          message:'请填写完整',
          type: 'error'
        });
        return false
      }else{
        //登录接口
        this.axios.post(this.baseURI+'/admin/selectUserByLogin',{"username":this.userInfo.username,"password":this.userInfo.password})
        .then(res => {
          console.log(res);
          if (res.status == 200) {
            var data=res.data.data;
            if(data){

              var user={};
              user.name=data.username;
              user.role=data.role;
              sessionStorage.setItem("token",user);  

              this.$notify({
                title: '提示信息',
                message: '登录成功',
                type: 'success'
              });
              this.RoleChange(data.role);
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
          console.log(err)
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

.login_box {
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
