<template>
	<div class="parking-manage">
		<h3>停车场停车服务</h3>
		<el-form ref="parkingform" :model="parkingform" >
			<el-form-item >
				<label>停车场编号</label>
				<el-input v-model="parkingform.lotId" placeholder="请输入停车场编号" ></el-input>
			</el-form-item>
			<el-form-item>
				<label>卡号</label>
				<el-input v-model="parkingform.cardId" placeholder="请输入卡号"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button class="parking-button" type="primary" @click="parkingCar()">停车</el-button>
				<el-button class="parking-button" type="primary" @click="withdrawCar()">取车</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default{
		name: 'ParkingManage',
		mounted(){
			this.$nextTick(function () {
				this.spaceLimit();
			})
		},
		data(){
			return{
				//停车信息
				parkingform: {
					lotId: ``,
                    cardId: ``
				},
			}
		},
		methods:{
			spaceLimit(){
				var input=document.getElementsByTagName("input");
				for(var i=0;i<input.length;i++){
					input[i].onkeyup=function(){
						this.value=this.value.replace(/^ +| +$/g,'');
					}
				}
			},
			//停车
			parkingCar(){
				if(this.parkingform.lotId && this.parkingform.cardId){
					this.axios.post(this.baseURI + "/parkingRecord/insertParkingRecord",{
						parkingNum: this.parkingform.lotId,
						cardNum: this.parkingform.cardId
					}).then(res => {
						console.log(res);
						if(res.data.message == "OK"){
							this.$notify({
							title: '提示信息',
							message: '停车成功',
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
				}
				else{
					this.$notify({
						title: '提示信息',
						message: '请填写完整停车信息',
						type: 'error'
					});
				}
				
			},
			//取车
			withdrawCar(){
				if(this.parkingform.lotId && this.parkingform.cardId){
					this.axios.post(this.baseURI + "/parkingRecord/updateParkingRecord",{
						parkingNum: this.parkingform.lotId,
						cardNum: this.parkingform.cardId
					}).then(res => {
						console.log(res);
                        if(res.data.message == "OK"){
                        	this.$notify({
                        		title: '提示信息',
                        		message: '取车成功',
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
				}
				else{
					this.$notify({
						title: '提示信息',
						message: '请填写完整取车信息',
						type: 'error'
					});
				}
			}
	    }
    }
</script>

<style scoped>
	.parking-manage{
        overflow: hidden;
	}
	.el-button{
		margin-left: 100px;
		width: 100px;
	}
	.el-input{
		margin-left: 100px;
		width: 300px;
	}
	.el-form-item{
		min-width: 600px;
	}
	label{
		width: 100px;
		position: absolute;
	}
	h3{
		margin-top: 10px;
		margin-bottom: 35px;
	}
</style>