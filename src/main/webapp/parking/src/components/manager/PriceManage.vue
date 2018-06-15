<template>
	<div class="price-manage">
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
        <el-table-column label="车位总数" width="170">
          <template slot-scope="scope">
           	<span>{{scope.row.total}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
          	<el-button
          	size="mini"
          	@click="appearDialog(scope.row.parkingNum,scope.row.price)">修改价格</el-button>
          	<el-button
          	size="mini"
          	@click="checkReport(scope.row.parkingName,scope.row.id)">查看报表</el-button>
          </template>
        </el-table-column>
      </el-table>
            <!-- 修改价格弹窗 -->
      <el-dialog title="停车场价格" :visible.sync="priceFormVisible" :lock-scroll="false" >
      	<el-form :disabled="isDisabled">
      		<el-form-item label="价格" label-width="180" >
      			<div class="tip" v-show="isDisabled">今日为出账日，禁止修改价格</div>
      			<el-input v-model.number="modifyPrice"></el-input>
      		</el-form-item>
      	</el-form>
      	<div slot="footer" class="dialog-footer">
      		<el-button @click="priceFormVisible = false">取 消</el-button>
      		<el-button type="primary" @click="modifyLotPrice()" :disabled="isDisabled">提交修改</el-button>
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
        this.inputDisabled();
			})
		},
		data(){
			return{
				//搜索内容
				searchNumber: '',
				// 修改价格弹窗
				priceFormVisible: false,
				//输入框隐藏
				isDisabled:false,
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
			//出账日输入限制
			inputDisabled(){
	      var date=new Date();
	      var nowDate=date.getMonth()+1+'/'+date.getDate();
	      if(/*nowDate=='3/31'*/nowDate=='5/25'||nowDate=='6/30'||nowDate=='9/30'||nowDate=='12/31'){
	        this.isDisabled=true;
	      }
    	},
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
        this.inputDisabled();
        if(!this.isDisabled){
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
        }
        else{
        	this.$notify({
        		title: '提示',
        		message: '今日为出账日，禁止修改价格',
        		type: 'error'
        	});
        }
	  	},
    //查看报表
    checkReport(lotName,lotId){
    	this.$router.push({
    		name: `SellManage`,
    		params:{lotName:lotName,lotId:lotId}
    	});
    }
	}
}
</script>

<style scoped>
.tip{
  margin-bottom: 20px;
  color: #f00;
}
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