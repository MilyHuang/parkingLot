<template>
	<div class="search-card">
		<el-input v-model="searchNumber" placeholder="请输入手机号查询账单"></el-input>
		<el-button type="primary" @click="SearchUserBill()">查找</el-button>
		<el-table
          :data="UserBillData"
          style="width: 95%">
          <el-table-column
            label="账单编号"
            width="120"
            class="card-active">
            <template slot-scope="scope">
              <!-- 未出帐禁用点击 -->
              <!-- <span v-if="scope.row.flag == 2">{{ scope.row.billNum }}</span> -->
              <!-- 查看帐单 -->
              <router-link :to="{ name:'SearchBillDetail', params: {billNum: scope.row.billNum}}" class="card-active">{{ scope.row.billNum }} </router-link> 
            </template>
          </el-table-column>
          <el-table-column
            prop="cardNum"
            label="卡号"
            width="125">
          </el-table-column>
          <el-table-column
            prop="price"
            label="单价（元/月）"
            width="120">
          </el-table-column>
          <el-table-column
            prop="account"
            label="总费用（元）"
            width="110">
          </el-table-column>
          <el-table-column
            prop="parkingName"
            label="停车场"
            width="115">
          </el-table-column>
          <el-table-column
            label="出账日期"
            width="115">
             <template slot-scope="scope">
              <span>{{secondToDate(scope.row.statementDate)}}</span>
             </template>
          </el-table-column>
          <el-table-column
            label="状态">
            <template slot-scope="scope">
              <!-- 显示未缴费状态 -->
              <span v-if="scope.row.flag == 0 || scope.row.flag == 3" :class="{unpaid:scope.row.flag == 0 || scope.row.flag == 3}">{{ payArr[0] }}</span>
              <!-- 显示未出账和已交费 -->
              <span v-else>{{ payArr[scope.row.flag] }}</span>
              <!-- 显示提示信息 -->
              <span v-if="scope.row.flag == 0 || scope.row.flag == 3"  class="tip">{{ '（' + scope.row.tis + '）'}}</span>
            </template>
          </el-table-column>
           <!-- <el-table-column>
            <template slot-scope="scope">
              <span class="tip">{{ scope.row.tip }}</span>
            </template>
          </el-table-column> -->
        </el-table>
	</div>
</template>

<script>
	export default{
		name: `SearchCard`,
		mounted(){
            this.$nextTick( () =>{
            	if(this.$route.params.phone){
                    this.SearchUserBill();
            	}
            })
		},
		data(){
			return{
				searchNumber: ``,
				//保存下来的电话
				prePhone: ``,
				UserBillData:[],
				//缴费样式数组
        		payArr:[`未缴费`,`已缴费`,`未出账`,`欠费`],
			}
		},
		methods:{
			//查询账单
			SearchUserBill(){
				//接收搜索的信息
				if(this.$route.params.phone == 0)
					this.prePhone = this.searchNumber;
                else
                	this.prePhone = this.$route.params.phone;
				this.axios.post(this.baseURI + '/parkingBill/selectAllParkingBillEntity', { "phone": this.prePhone})
                .then(res => {
                  // sessionStorage.getItem("phone")
                  console.log(res);
                  this.UserBillData = res.data.data;
                })
                .catch(err => {
                  console.log(err)
                })
			},
      		//时间转换函数
      		secondToDate(date){
      		    let res = new Date(date).toLocaleString();
      		    return res.slice(0,res.indexOf(' '));
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
</style>