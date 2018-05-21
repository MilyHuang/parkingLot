<template>
  <div class="admin-user">
  	<!-- 运营商、操作员列表 -->
	  <el-table ref="multipleTable" :data="adminUserTable" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
	      <el-table-column type="selection" width="55">
	      </el-table-column>
	      <el-table-column label="用户名" width="220">
	      	<template slot-scope="scope">{{ scope.row.username }}</template>
	      </el-table-column>
	      <el-table-column prop="usertype" label="角色" width="220">
	      </el-table-column>
	      <el-table-column label="操作" show-overflow-tooltip>
	      	<template slot-scope="scope">
	      		<a class="update-code" @click="upadteCodeDialog(scope.row.username,scope.row.id)">修改密码</a>
	      		<!-- <a class="update-code" @click="delDialog(scope.row.id)">删除</a> -->
	      	</template>
	      </el-table-column>
	  </el-table>

		<!-- 用户操作按钮 -->
	  <div class="admin-user-operate">
	    <el-button type="primary" @click="addUserVisible = true">增加用户</el-button>
	    <el-button type="primary" @click="delUserVisible=true">删除用户</el-button>
	  </div>
		
		<!-- 删除用户弾框 -->
	  <el-dialog title="提示" :visible.sync="delUserVisible"  width="30%">
	    <span>是否确定删除选择的用户？</span>
	    <span slot="footer" class="dialog-footer">
		    <el-button @click="delUserVisible = false">取 消</el-button>
		    <el-button type="primary" @click="delUser()">确 定</el-button>
			</span>
	  </el-dialog>

		<!-- 添加用户弾框 -->
	  <el-dialog class="dialog-width" title="添加用户" :visible.sync="addUserVisible" width="30%">
      用户名<el-input v-model="adduser.username" type="text" /> 
      密码<el-input v-model="adduser.password" type="password" /> 
      <el-radio-group v-model="radio" @change="userTypeChoose">
        <el-radio :label="1">运营商</el-radio>
        <el-radio :label="2">操作员</el-radio>
      </el-radio-group>
	    <div class="dialog-footer">
	      <el-button @click="addUserVisible = false">取 消</el-button>
	      <el-button type="primary" @click="addUser()">确 定</el-button>
	    </div>
	  </el-dialog>

		<!-- 修改用户密码弾框 -->
	  <el-dialog title="修改用户密码" :visible.sync="updateCodeVisible" width="30%">
	    <form method="post">
	      用户名<el-input v-model="updateCode.username" type="text" disabled/> 
	      重置密码<el-input v-model="updateCode.code" type="password" />
	      确认密码<el-input v-model="updateCode.surecode" type="password" />
	    </form>
	    <div class="dialog-footer">
	      <el-button @click="updateCodeVisible = false">取 消</el-button>
	      <el-button type="primary" @click="upadteCode(updatecodeId)">确 定</el-button>
	    </div>
	  </el-dialog>

  </div>
</template>
<script>
export default {
	data() {
    return {
	    radio: 1,
	    updatecodeId:null,

	    delUserVisible: false,
	    addUserVisible: false,
	    updateCodeVisible:false,

			multipleSelection: [],
			delUserId:[],
			usertype:['运营商','操作员'],
	    adminUserTable: [],

	    adduser: {
	        username: '',
	        password:'',
	        usertype:1,
	    },

	    updateCode:{
	        username: '',
	        code:'',
	        surecode:''
	    }
		}
	},

	mounted: function() {
    this.$nextTick(function () {
      this.init();
    })
  },

  methods: {
  	// 初始化列表信息
  	init(){
  		this.axios.post('http://10.65.35.190:8080/parkingLot/admin/selectUserForList')
        .then(res => {
        	this.adminUserTable=[];
        	var data=res.data.data;
        	for(var i=0;i<data.length;i++){
        		if(data[i].role==1){
        			data[i].usertype=this.usertype[0];
        		}else{
        			data[i].usertype=this.usertype[1];
        		}
        		this.adminUserTable.push(data[i]);
        	}
        	console.log(this.adminUserTable);
        })
        .catch(err => {
          console.log(err);
        })
  	},
  	// 单选角色
  	userTypeChoose(value){
  		this.adduser.usertype=value;
  	},
  	// 反选
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    // 多选列表
    handleSelectionChange(val) {
    	this.delUserId=[];
      this.multipleSelection = val;
      for(var i=0;i<this.multipleSelection.length;i++){
				this.delUserId.push(this.multipleSelection[i].id);
      }
    },
    // 添加用户
    addUser(){
    	if (this.adduser.username == ''||this.adduser.password == '') {
    		this.$notify({
          title: '提示信息',
          message:'请填写完整',
          type: 'error'
        });
        return false
	    }else{
        this.axios.post('http://10.65.35.190:8080/parkingLot/admin/insertAdminUser',{"username":this.adduser.username,"password":this.adduser.password,"role":this.adduser.usertype})
        .then(res => {
        	console.log(res);
          var data=res.data;
          if(data.state==1){
						this.$notify({
              title: '提示信息',
              message: '添加成功',
              type: 'success'
          	});
						this.init();
          }else{
          	this.$notify({
              title: '提示信息',
              message: data.message,
              type: 'error'
          	});
          }
        	this.addUserVisible = false;
        })
        .catch(err => {
          console.log(err)
        })
	    }
		},
		// 删除用户
		delUser(){
			console.log(this.delUserId[0]);
			// var qs = require('qs');
			// var data=this.delUserId.join(',');
			this.axios.post('http://10.65.35.190:8080/parkingLot/admin/deleteAdminUser',{"datas":this.delUserId})
		        .then(res => {
		        	console.log(res);
		        	if(res.data.state==1){
		        		this.$notify({
				            title: '提示信息',
				            message: '删除成功',
				            type: 'success'
			          	});
		        	}else{
		        		this.$notify({
			                title: '提示信息',
			                message: res.data.message,
			                type: 'error'
		          		});
		        	}
		        	this.delUserVisible = false;
		        })
		        .catch(err => {
		          console.log(err)
		        })
		},
		// 修改密码
		upadteCode(id){
			if (this.updateCode.code==''|| this.updateCode.surecode=='') {
				this.$notify({
		          title: '提示信息',
		          message: '请填写完整！',
		          type: 'success'
		        });
		        return false
		    }else if (this.updateCode.code!=this.updateCode.surecode) {
		        this.$notify({
		          title: '提示信息',
		          message: '输入密码不一致',
		          type: 'error'
		        });
		        return false
		    }else{
		      this.axios.post('http://10.65.35.190:8080/parkingLot/admin/updatePasswordById',{"id":id,"password":this.updateCode.code})
		        .then(res => {
		        	console.log(res);
		        	this.updateCodeVisible = false;
		        	if(res.data.state==1){
		        		this.$notify({
				            title: '提示信息',
				            message: '修改成功',
				            type: 'success'
			          	});
		        	}else{
		        		this.$notify({
			                title: '提示信息',
			                message: res.data.message,
			                type: 'error'
		          		});
		        	}
		        })
		        .catch(err => {
		          console.log(err)
		        })
		    }
		},
		// 弹出修改密码弹窗
		upadteCodeDialog(username,id){
			this.updateCode.username=username;
			this.updatecodeId=id;
			this.updateCodeVisible = true;
		}
	}
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
a {
  text-decoration: none;
}

.admin-user {
  width: 980px;
  height: 90%;
}

.admin-user-search {
  float: right;
}

.admin-user-operate {
  margin-top: 30px;
  text-align: right;
}

.el-radio-group{
	width: 180px;
	margin-left: 20%;
 	margin-top: 40px;
}

.el-table {
  margin-top: 20px;
}

.dialog-footer{
	width: 100%;
	margin-top: 30px;
	text-align: center;
}

.update-code{
	color: #f00;
	cursor: pointer;
}
</style>
