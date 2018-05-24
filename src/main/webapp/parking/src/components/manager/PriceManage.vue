<template>
	<div class="price-manage">
		<!-- 搜索停车场 -->
		<!-- <div class="price-search">
			<el-input v-model="searchNumber" placeholder="请输入停车场编号"></el-input>
			<el-button type="primary">查找</el-button>
		</div> -->
		<!-- 停车场详细信息 -->
		<div class="sell-detail">
			<el-table
			:data="sellData"
			style="width: 100%">
		    <el-table-column label="停车场编号" width="140">
          <template slot-scope="scope">
         		<span>{{scope.row.parkingNum}}</span>
          </template> 
        </el-table-column>
        <el-table-column label="停车场名字" width="160">
          <template slot-scope="scope">
           	<span>{{scope.row.parkingName}}</span>
          </template>
        </el-table-column>
        <el-table-column label="价格(元/月)" width="120">
          <template slot-scope="scope">
           	<span>{{scope.row.price}} </span>
          </template>
        </el-table-column>
        <el-table-column label="租金(元/月)" width="120">
          <template slot-scope="scope">
          	<span>{{scope.row.rent}}</span>
          </template>
        </el-table-column>
          <!-- <el-table-column
        prop="benefit"
        label="销售额"
        width="110">
          </el-table-column> -->
         <!--  <el-table-column
        prop="parkingCount"
        label="剩余车位"
        width="110">
          </el-table-column> -->
        <el-table-column label="车位总数" width="170">
          <template slot-scope="scope">
           	<span>{{scope.row.total}}</span>
          </template>
        </el-table-column>
          <!-- <el-table-column
        prop="usePercent"
        label="使用率"
        width="130">
          </el-table-column> -->
        <el-table-column label="操作">
          <template slot-scope="scope">
          	<el-button
          	size="mini"
          	@click="appearDialog(scope.row.parkingNum,scope.row.price)">修改价格</el-button>
          	<el-button
          	size="mini"
          	@click="checkReport(scope.row.parkingName)">查看报表</el-button><!-- 
          	<router-link to="/ManagerIndex/SellManage/123">jump</router-link> -->
          </template>
        </el-table-column>
      </el-table>
            <!-- 修改价格弹窗 -->
      <el-dialog title="停车场价格" :visible.sync="priceFormVisible" :lock-scroll="false" >
      	<el-form>
      		<!-- <el-form-item label="月份" label-width="150">
      			<el-date-picker
      			v-model="priceform.month"
      			type="month"
      			placeholder="选择月份"
      			@change="output()">
      		    </el-date-picker>
      		</el-form-item> -->
      		<el-form-item label="价格" label-width="180" >
      			<el-input v-model.number="modifyPrice"></el-input>
      		</el-form-item>
      	</el-form>
      	<div slot="footer" class="dialog-footer">
      		<el-button @click="priceFormVisible = false">取 消</el-button>
      		<el-button type="primary" @click="modifyLotPrice()">提交修改</el-button>
      	</div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
	export default{
		name: `PriceManage`,
		mounted(){
			this.$nextTick(() => {
                this.loadParkingLot();
			})
		},
		data(){
			return{
				//搜索内容
				searchNumber: '',
				// 修改价格弹窗
				priceFormVisible: false,
				//修改停车场Id
				currentLotId: '1',
				//修改的价格
				modifyPrice: '1000',
				//停车场数据
				sellData: [/*{
					lotId: '01',
					lotName: '天河停车场',
					price: '999￥',
					totalCount: 100
				}, {
					lotId: '02',
					lotName: '白云停车场',
					price: '999￥',
					totalCount: 100
				}, {
					lotId: '03',
					lotName: '越秀停车场',
					price: '999￥',
					totalCount: 100
				}, {
					lotId: '04',
					lotName: '荔湾停车场',
					price: '999￥',
					totalCount: 100
				}*/],
				
				
			}
		},
		methods:{
			// 加载停车场
	    loadParkingLot(){
	    	this.axios.get(this.baseURI + `/parkinglot/selectParkinglot`)
	    	.then(res => {
	    		this.sellData = res.data.data;
	    	})
	    },
	    // 显示修改框
	    appearDialog(lotId,price){
        this.priceFormVisible = true;
        this.currentLotId = lotId;
        this.modifyPrice = price;
	    },
	    // 修改当前停车场价格
	    modifyLotPrice(){
        this.priceFormVisible = false;
        if(typeof(this.modifyPrice) == `number` 
           && this.modifyPrice > 0){
        	this.axios.post(this.baseURI + `/parkinglot/updateParkingLotPrice`,{
        		parkingNum: this.currentLotId,
        		price: this.modifyPrice
        	}).then( res => {
        		console.log(res);
        		if(res.data.message == "OK"){
        			this.$notify({
        				title: '提示',
        				message: '价格修改成功',
        				type: 'success'
        			});
        		}
        		else{
        			this.$notify({
        				title: '提示',
        				message: '价格修改失败',
        				type: 'error'
        			});
        		}
        		this.loadParkingLot();
        	})
        }
        else{
        	this.$notify({
        		title: '提示',
        		message: '价格只能为正数',
        		type: 'error'
        	});
        }
	  	},
    //查看报表
    checkReport(lotName){
    	this.$router.push({
    		name: `SellManage`,
    		params:{lotName:lotName}
    	});
    }
	}
}
</script>

<style scoped>
.price-manage {
   width: 90%;
   min-width: 950px;
}
.price-search {

}
.sell-detail {

}
.el-input {
	display: inline-block;
	width: 200px;
	margin-bottom: 30px;
}
.el-dialog {
}
.el-form{
	margin-left: 60px;
	margin-bottom: -40px;
}

</style>