<template>
<div class="search-card">
	<el-input v-model.trim="searchNumber" placeholder="请输入手机号查询停车卡"></el-input>
	<el-button type="primary" @click="searchCard()">查找</el-button>
	<el-table ref="singleTable"  
	highlight-current-row 
	:data="UserCardTable" 
	tooltip-effect="dark" 
	style="width: 100%" 
	@current-change="handleCurrentChange">
	    <el-table-column label="卡号" width="220">
	    	<template slot-scope="scope">{{ scope.row.cardNum }}</template>
	    </el-table-column>
	    <el-table-column label="办理时间" width="220">
	    	<template slot-scope="scope">{{ secondToDate(scope.row.createdTime) }}</template>
	    </el-table-column>
	    <el-table-column label="状态" show-overflow-tooltip>
			<template slot-scope="scope">{{ cardState[scope.row.state] }}</template>
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
				cardState:[`正常`,`禁用`,`已注销`],
				UserCardTable:[],
			}
		},
		methods:{
			searchCard(){
				if(this.InputLimit())
				this.axios.post(this.baseURI + '/parkingCard/selectUserCardsForList', { "phone": this.searchNumber})
				.then( res => {
                    console.log(res);
                    this.UserCardTable = res.data.data;
				})
				.catch( err => {
					console.log(err);
				})
			},
			handleCurrentChange(val) {
				console.log(val);
			},
            //时间转换函数
            secondToDate(date){
            	let res = new Date(date).toLocaleString();
            	return res.slice(0,res.indexOf(' '));
            },
            InputLimit(){
                //进入输入判断
                	if(!this.searchNumber){
                		this.$notify({
                			title: '提示信息',
                			message: '请填写完整',
                			type: 'error'
                		});
                	}
                	else if(!(/^[0-9]*$/.test(this.searchNumber))){
                		this.$notify({
                			title: '提示信息',
                			message: '卡号必须为数字',
                			type: 'error'
                		});
                	}else{ 
                		return true;
                	}
                    return false;
  }
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