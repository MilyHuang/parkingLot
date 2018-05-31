<template>
	<div class="search-card">
		<el-input v-model="searchNumber" placeholder="请输入手机号"></el-input>
		<el-button type="primary">查找</el-button>
		<el-table ref="multipleTable" :data="UserCardTable" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
	      <el-table-column type="selection" width="55">
	      </el-table-column>
	      <el-table-column label="卡号" width="220">
	        <template slot-scope="scope">{{ scope.row.cardNum }}</template>
	      </el-table-column>
	      <el-table-column prop="createTime" label="办理时间" width="220">
	      </el-table-column>
	      <el-table-column prop="state" label="状态" show-overflow-tooltip>
	      </el-table-column>
    </el-table>
    <!-- 操作员操作停车卡按钮 -->
    <div class="operator-card">
      <el-button type="primary" @click="upadteCard = true">补办</el-button>
      <el-button type="primary" @click="deleteCard = true">注销</el-button>
    </div>
	</div>
</template>

<script>
	export default{
		name: `SearchCard`,
		data(){
			return{
				searchNumber: ``,
				upadteCard:false,
				deleteCard:false,
				cardState:[`正常`,`已注销`,`禁用`],
				UserCardTable:[{
					cardNum:`201803`,
					createTime:`2018-03-10`,
					state:`正常`
				},
				{
					cardNum:`201706`,
					createTime:`2017-06-03`,
					state:`已注销`
				},
				{
					cardNum:`201709`,
					createTime:`2017-09-25`,
					state:`禁用`
				}]
			}
		},
		method:{
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
	      this.delUserId = [];
	      this.multipleSelection = val;
	      for (var i = 0; i < this.multipleSelection.length; i++) {
	        // this.delUserId.push(this.multipleSelection[i].id);
	      }
	    },
		}
	}
</script>

<style scoped>
	.search-card{
		width: 980px;
	}
	.el-input {
		display: inline-block;
		width: 270px;
		margin-bottom: 30px;
	}
	.operator-card {
  margin-top: 30px;
  text-align: right;
}
</style>