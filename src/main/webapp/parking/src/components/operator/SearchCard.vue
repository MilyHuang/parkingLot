<template>
	<div class="search-card">
		<el-input v-model="searchNumber" placeholder="请输入手机号"></el-input>
		<el-button type="primary">查找</el-button>
		<el-table
          :data="benefitData"
          style="width: 80%">
          <el-table-column
            prop="rend"
            label="租金"
            width="200">
          </el-table-column>
          <el-table-column
            prop="totalBenefit"
            label="收入总额"
            width="200">
          </el-table-column>
          <el-table-column
            prop="expectBenefit"
            label="期望盈利">
          </el-table-column>
        </el-table>
	</div>
</template>

<script>
	export default{
		name: `SearchCard`,
		data(){
			return{
				searchNumber: ``,
			}
		}
	}
</script>

<style scoped>
	.search-card{

	}
	.el-input {
		display: inline-block;
		width: 270px;
		margin-bottom: 30px;
	}
</style>