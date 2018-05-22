<template>
	<div class="create-card">
		<div>
			<el-input v-model="searchNumber" placeholder="请输入手机号"></el-input>
			<el-button type="primary">查找</el-button>
			<el-form ref="cardInfo" :model="cardInfo" label-width="50px">
				<el-form-item>
					<span>用户名</span>
					<el-input v-model="cardInfo.name"></el-input>
				</el-form-item>
				<el-form-item>
					<span>电话</span>
					<el-input v-model="cardInfo.phoneNumber"></el-input>
				</el-form-item>
				<el-form-item>
					<span>卡号</span>
					<el-input v-model="cardInfo.cardAccount"></el-input>
				</el-form-item>
				<el-form-item>
					<span>电话</span>
					<el-input v-model="cardInfo.phoneNumber"></el-input>
				</el-form-item>
				<el-form-item>
					<span>密码</span>
					<el-input type="password" v-model="cardInfo.password"></el-input>
				</el-form-item>
				<el-form-item >
					<el-button class="deal-button" type="primary">办理</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	export default{
		name: `CreateCard`,
		data(){
			return{
				searchNumber: ``,
				cardInfo:{
                   name: `chris`,
                   phoneNumber: `123456`,
                   cardAccount: `85280110`,
                   password: `12345678`
				},
				formLabelAlign: {
					name: '123',
					region: '456',
					type: '789'
				}
			}
		}
	}
</script>

<style scoped>
	.create-card{
		overflow: hidden;
		text-align: center;
	}
	.el-input {
		display: inline-block;
		width: 270px;
		margin-bottom: 30px;
		margin-left: 40px;
		padding: 0;
	}
	.deal-button{
		margin-left: 20%;
        width: 140px;
	}
</style>