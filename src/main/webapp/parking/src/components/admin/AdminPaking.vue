<template>
  <div style="width: 980px;">
    <el-table
    :data="adminPackingLotTable"
    tooltip-effect="dark"
    style="width: 100%">
    <el-table-column
      label="停车场编号"
      width="100">
      <template slot-scope="scope">{{ scope.row.parkingNum }}</template>
    </el-table-column>
    <el-table-column
      prop="parkingName"
      label="停车场名"
      width="100">
    </el-table-column>
    <el-table-column
      prop="address"
      label="地址"
      width="300">
    </el-table-column>
    <el-table-column
      prop="total"
      label="容量"
      width="200">
    </el-table-column>
    <el-table-column
      prop="rent"
      label="租金(元/月)"
      show-overflow-tooltip>
    </el-table-column>
  </el-table>
    <div style="margin-top: 50px; text-align: right;">
      <el-button type="primary" @click="adddialogVisible=true">增加停车场</el-button>
      <el-button type="primary" @click="deldialogVisible=true">删除停车场</el-button>
    </div>
    <el-dialog title="提示" :visible.sync="deldialogVisible" width="30%">
      <span>是否确定删除选择的停车场？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deldialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="deldialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="添加停车场" :visible.sync="adddialogVisible" width="30%">
          停车场编号<el-input v-model="parkinglot.parkingNum" type="text"/>
          停车场名<el-input v-model="parkinglot.parkingName" type="text"/>
          地址<el-input v-model="parkinglot.address" type="text"/>
          容量<el-input v-model.trim.number="parkinglot.total" type="text"/>
          租金<el-input v-model.trim.number="parkinglot.rent" type="text"/>
      <span slot="footer" class="dialog-footer">
        <el-button @click="adddialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addPackingLot()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //管理员管理停车场字段
      parkinglot: {
          parkingNum: '',
          parkingName: '',
          address: '',
          total: '',
          rent:''
      },
      //停车场列表
      adminPackingLotTable: [],
      //删除停车场弹窗
      deldialogVisible: false,
      //增加停车场弹窗
      adddialogVisible: false
    }
  },
  mounted: function() {
    this.$nextTick(function() {
      //初始化停车场列表
      this.initPackingLot();
    })
  },
  methods: {
    initPackingLot(){
      this.axios.post(this.baseURI +'/parkinglot/selectParkinglot')
        .then(res => {
          this.adminPackingLotTable = [];
          var data = res.data.data;
          for (var i = 0; i < data.length; i++) {
            this.adminPackingLotTable.push(data[i]);
          }
          console.log(this.adminPackingLotTable);
        })
        .catch(err => {
          console.log(err);
        })
    },
    //增加停车场
    addPackingLot(){
      if (this.parkinglot.parkingNum && this.parkinglot.parkingName && this.parkinglot.address && this.parkinglot.total&& this.parkinglot.rent) {
        if(this.checkForm())
        this.axios.post(this.baseURI + '/parkinglot/insertParkinglot', { "parkingNum": this.parkinglot.parkingNum, "parkingName": this.parkinglot.parkingName, "address": this.parkinglot.address,"total": this.parkinglot.total,"rent": this.parkinglot.rent})
          .then(res => { 
            //插入成功执行
            console.log(res);
            if(res.data.message !== `OK`){
              this.$notify({ 
                title: '提示信息',
                message: res.data.message,
                type: 'error'
              });
            }
            this.adddialogVisible = false;
            this.initPackingLot();
          })
          .catch(err => {
            console.log(err)
          })
      }else{ //插入失败执行
          this.$notify({ 
          title: '提示信息',
          message: '请填写完整',
          type: 'error'
        });
        return false
      }
    },
    //表单检验
    checkForm(){
        //检验数字 
        if(typeof(this.parkinglot.total) == `number` && typeof(this.parkinglot.rent) == `number`){
          console.log(this.parkinglot.total + ` ` + typeof(this.parkinglot.rent));
           //检验负数
           if(this.parkinglot.total > 0 && this.parkinglot.rent > 0)return true;
        }
          this.$notify({ 
            title: '提示信息',
            message: '租金和容量只能为正数',
            type: 'error'
          });
          return false;
      }
  }
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.el-table {
  width: 100%;
  font-size: 14px;
}

</style>
