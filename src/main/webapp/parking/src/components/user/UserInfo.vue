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
                	<span class="card-active">{{ scope.row.cardNum }}</span>
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
	</div>
</template>

<script>
	export default{
		name: `UserInfo`,
		data(){
			return{
				cardData:[
				   {
				   	num: 1,
				   	cardNum: 110,
					createTime: `2018-6-30`,
					lotName: `永康停车场`,
					state: `不可用`
				   },
				   {
				   	num: 2,
				   	cardNum: 111,
					createTime: `2018-6-39`,
					lotName: `永康停车场2`,
					state: `不可用`
				   }
				]
			}
		}
	}
</script>

<style scoped>
	.user-info{

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