<!-- <template>
	<div class="search-card">
		<el-input v-model="searchNumber" placeholder="请输入手机号"></el-input>
		<el-button type="primary">查找</el-button>
		<el-table
      :data="UserBillTable"
      style="width: 100%">
      <el-table-column
        prop="billNum"
        label="帐单编号"
        width="200">
        <router-link :to="{ name:'UserBillDetail', params: {billNum: scope.row.billNum} }" class="card-active" v-else>{{ scope.row.billNum }} </router-link> 
      </el-table-column>
      <el-table-column
        prop="cardNum"
        label="卡号"
        width="200">
      </el-table-column>
      <el-table-column
        prop="lotName"
        label="停车场">
      </el-table-column>
      <el-table-column
        prop="billTime"
        label="时间">
      </el-table-column>
      <el-table-column
        prop="totalPay"
        label="总费用">
      </el-table-column>
      <el-table-column
        prop="state"
        label="状态">
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
				UserBillTable:[{
					billNum:`89757001001`,
					cardNum:`01011`,
					lotName:`A停车场`,
					billTime:`2018-3-31`,
					totalPay:`300`,
					state:`已缴费`
				},
				{
					billNum:`89757001001`,
					cardNum:`06011`,
					lotName:`B停车场`,
					billTime:`2017-12-31`,
					totalPay:`300`,
					state:`缴费`
				},
				{
					billNum:`89757001001`,
					cardNum:`03011`,
					lotName:`A停车场`,
					billTime:`2017-9-30`,
					totalPay:`300`,
					state:`未出账`
				}]
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
</style> -->