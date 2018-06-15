<template>
	<div class="admin">
		    <el-menu
		    class="el-menu-vertical-demo menu"
		    background-color="#409EFF"
		    text-color="black"
		    active-text-color="#ffd04b">
		        <el-menu-item index="1">
		    	    <i class="el-icon-menu"></i>
		    	    <span slot="title">导航一</span>
		        </el-menu-item>
		        <el-menu-item index="2">
		    	    <i class="el-icon-menu"></i>
		    	    <span slot="title">导航二</span>
		        </el-menu-item>
		        <el-menu-item index="3">
		    	    <i class="el-icon-document"></i>
		    	    <span slot="title">导航三</span>
		        </el-menu-item>
		        <el-menu-item index="4">
		    	    <i class="el-icon-setting"></i>
		    	    <span slot="title">导航四</span>
		        </el-menu-item>
		    </el-menu>
	    <div class="parking-list">
	    	<div class="parking-detail"></div>
	    	<div class="parking-button">
	    	    <el-button type="primary" round>添加停车场</el-button>
	    	    <el-button type="primary" round>删除停车场</el-button>
	        </div>
	    </div>
</div>
</template>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>

	export default{
		// el: `admin`,
		name: `admin`,
		data () {
			return{
            
			}
		},
	}
</script>

<style >
    .admin{
        width: 100%;
        height: 100%;
        overflow: hidden;
    }
    .menu{
    	overflow: hidden;
    	margin-bottom: -2500px;
    	padding-bottom: 2500px;
    	width: 200px;
    	float: left;
    	background-color: #545c64;
    }
    .parking-list{
    	width: calc(100% - 205px);
    	height: 700px;
    	border: 2px solid black;
    	float: right;
    }
    .parking-detail{
    	margin: 50px auto;
    	width: 700px;
    	height: 400px;
    	border: 2px solid black;
    }
    .parking-button{
        margin-left: calc(100% - 620px);
    }
</style>