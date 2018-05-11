<template>
  <div class="parking-box">
    <div class="parking-top">
      <span class="logo"><img src="../../assets/logo.png"></span>
      <span class="admin-icon" @click="showToggle">
            <img src="../../assets/login_logo.png" />
            <ul class="admin-operate" v-show="isShow">
                <li>修改密码</li>
                <li @click="LoginOut">注销</li>
            </ul>
            <span class="admin-down-triggle" v-show="isShow"></span>
      </span>
    </div>
    <div class="parking-nav">
      <router-link to="/AdminPaking">停车场管理</router-link>
      <router-link to="/AdminUser">用户管理</router-link>
    </div>
    <div class="parking-con">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      isShow: false
    }
  },

  methods: {
    showToggle: function() {
      this.isShow = !this.isShow
    },
    LoginOut: function() {
      this.$router.push({ path: '/' });
    }
  }
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.admin-icon {
  position: relative;
  float: right;
  width: 50px;
  height: 50px;
  margin-top: 20px;
  margin-right: 30px;
  border: 2px solid #98d0f2;
  border-radius: 50%;
  background-color: #fff;
}

.logo {
  display: inline-block;
  width: 310px;
  height: 52%;
  margin-top: 20px;
  overflow: hidden;
}

.logo img {
  height: 100%;
}

.admin-icon img {
  width: 70%;
  margin: 10px 7px;
}

.admin-operate {
  position: absolute;
  top: 60px;
  left: -27px;
  margin: 0;
  padding: 0;
  width: 100px;
  height: 100px;
  background-color: #fff;
  z-index: 1000;
}

.admin-operate li {
  height: 50%;
  width: 100px;
  line-height: 50px;
  text-align: center;
  background-color: #fff;
  border: 1px solid #98d0f2;
  cursor: pointer;
}

.admin-down-triggle {
  position: relative;
  top: -95px;
  left: 15px;
  height: 0px;
  width: 0px;
  border-top: 8px solid transparent;
  border-left: 8px solid transparent;
  border-right: 8px solid transparent;
  border-bottom: 8px solid white;
}

</style>
