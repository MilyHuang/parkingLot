<template>
	<div class="user-info">
		<h3><span>用户名：</span><span>YongKang</span></h3>
		<div class="user-telephone">
		   	<span>电话号码：</span><span>110</span>
		</div>
		<div class="user-card">
      <h3>我的停车卡</h3>
      <el-table
          :data="cardData"
          style="width: 90%">
          <el-table-column
            type="index"
            label="序号"
            width="150">
          </el-table-column>
          <el-table-column
            label="卡号"
            width="150"
            class="card-active">
            <template slot-scope="scope">
            	<!-- 停车卡跳转用户账单 -->
            	<router-link :to="{ name:'UserBill', params: {cardNum: scope.row.cardNum}}" class="card-active">{{ scope.row.cardNum }}</router-link>
            </template>
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="办卡时间"
            width="200">
          </el-table-column>
          <el-table-column
            prop="lotName"
            label="停车场"
            width="200">
          </el-table-column>
           <el-table-column
            prop="state"
            label="状态">
          </el-table-column>
        </el-table>
    </div>
    <router-view></router-view>
	</div>
</template>

<script>
	export default{
		name: `UserInfo`,
		data(){
			return{
				cardData:[{
			   	num: 1,
			   	cardNum: 88888888,
					createTime: `2018-6-30`,
					lotName: `A停车场`,
					state: `不可用`
				}]
			}
		},
		mounted: function() {
	    this.$nextTick(function () {
	    	this.initCardList();
	    })
	  },
		methods:{
	    initCardList(){
	      this.axios.post('')
          .then(res => {
            console.log(res);
          })
          .catch(err => {
            console.log(err)
          })
	    },
		}
	}
</script>

<style scoped>
	.user-info{
       min-width: 900px;
	}
	.user-card h3{
		margin-top: 40px;
		margin-bottom: 30px;
		width: 80%;
		text-align: center;
	}
	.user-telephone{
		font-size: 1.3;
		font-weight: bold;
		margin-top: 20px;
		margin-bottom: 20px;
	}
	.card-active{
        cursor: pointer;
        color: #98d0f2;
	}
</style>