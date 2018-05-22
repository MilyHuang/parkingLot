<template>
  <div style="width: 980px;">
    <el-table
    ref="multipleTable"
    :data="tableData3"
    tooltip-effect="dark"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      label="停车场编号"
      width="100">
      <template slot-scope="scope">{{ scope.row.id }}</template>
    </el-table-column>
    <el-table-column
      prop="name"
      label="停车场名"
      width="100">
    </el-table-column>
    <el-table-column
      prop="address"
      label="地址"
      width="300">
    </el-table-column>
    <el-table-column
      prop="number"
      label="容量"
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
      <form method="post">
          停车场编号<el-input v-model="parkinglot.id" type="text"/>
          停车场名<el-input v-model="parkinglot.name" type="text"/>
          地址<el-input v-model="parkinglot.address" type="text"/>
          容量<el-input v-model="parkinglot.number" type="text"/>
        </form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="adddialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="adddialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      parkinglot: {
          id: '',
          name: '',
          address: '',
          number: ''
      },
      tableData3: [{
          id: '1',
          name: 'pl1',
          address: '上海市普陀区金沙江路 1518 弄',
          number: 55
        }, {
         id: '2',
          name: 'pl2',
          address: '上海市普陀区金沙江路 1518 弄',
          number: 70
        }],
      multipleSelection: [],
      deldialogVisible: false,
      adddialogVisible: false
    }
  },

  methods: {
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
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

thead th{
  text-align: center;
}

</style>
