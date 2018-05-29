<template>
	<div class="parking-manage">
		<h3>停车场停车服务</h3>
		<el-form ref="parkingform" :model="parkingform" >
			<el-form-item >
				<label>停车场编号</label>
				<el-input v-model="parkingform.lotId" placeholder="请输入停车场编号"></el-input>
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
		<!-- <h3>停车场取车</h3>
		<el-form ref="parkingform" :model="parkingform" >
            <el-form-item>
				<label>卡号</label>
				<el-input v-model="withdrawform.cardId" placeholder="请输入卡号"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button class="parking-button" type="primary" @click="parkingCar()">取车</el-button>
			</el-form-item>
		</el-form> -->
	</div>
</template>

<script>
	export default{
		name: 'ParkingManage',
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
			//停车
			parkingCar(){
				// this.axios.post("",{

				// }).then(res => {

				// })
			},
			//取车
			withdrawCar(){
    //             this.axios.post("",{

				// }).then(res => {
					
				// })
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