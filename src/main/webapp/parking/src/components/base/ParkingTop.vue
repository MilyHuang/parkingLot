<template>
	<div class="parking-top">
		<span class="logo"><img src="../../assets/logo.png"></span>
		<span class="admin-icon" @click="showToggle">
			<img src="../../assets/login_logo.png" />
			<ul class="admin-operate"  v-show="isShow">
				<!-- <li @click="updateCodeVisible = true">修改密码</li> -->
				<li @click="LoginOut">注销</li>
			</ul>
			<span class="admin-down-triggle" v-show="isShow"></span>
		</span>
    <!-- <el-dialog title="修改密码" :visible.sync="updateCodeVisible" width="30%">
        <form method="post">
          用户名<el-input v-model="updateCode.name" type="text" /> 
          重置密码<el-input v-model="updateCode.code" type="text" />
          确认密码<el-input v-model="updateCode.surecode" type="text" />
        </form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="updateCodeVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateCodeVisible = false">确 定</el-button>
        </span>
      </el-dialog> -->
	</div>
</template>

<script>
	export default {
    mounted: function() {
        // 路由拦截
      this.$nextTick(function () {
        // if (sessionStorage.token==undefined) { 
        //     this.$router.push({ path: '/' });
        // }
      })
    },
    data() {
      return {
        updateCode:{
          name:'',
          code:'',
          surecode:''
        },
        isShow: false,
        // updateCodeVisible:false
      }
    },
    methods: {
      showToggle: function() {
        this.isShow = !this.isShow
      },
      //登出
      LoginOut: function() {
        if(sessionStorage.phone!=undefined){
          this.$router.push({ path: '/UserLogin' });
        }else{
          this.$router.push({ path: '/' });
        }
        sessionStorage.removeItem("phone");
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("role");
      }
    }
}
</script>

<style scoped>
.admin-icon {
  position: relative;
  float: right;
  width: 50px;
  height: 50px;
  margin-top: 10px;
  margin-right: 30px;
  border: 2px solid #98d0f2;
  border-radius: 50%;
  cursor: pointer;
  background-color: #fff;
}
/* logo定高 */
.logo {
  display: inline-block;
  width: 310px;
  height: 30px;
  line-height: 90px;
  vertical-align: 20px;
  /*overflow: hidden;*/
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
  top: 63px;
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
1
.admin-down-triggle {
  position: relative;
  top: -24px;
  left: 5px;
  height: 0px;
  width: 0px;
  border-top: 18px solid transparent;
  border-left: 18px solid transparent;
  border-right: 18px solid transparent;
  border-bottom: 8px solid white;
}

</style>