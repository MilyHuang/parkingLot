<template>
  <div style="width: 980px;">
    <el-table ref="multipleTable" :data="tableData3" tooltip-effect="dark" @selection-change="handleSelectionChange" size="small">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column label="停车场" width="150">
        <template slot-scope="scope">{{ scope.row.date }}</template>
      </el-table-column>
      <el-table-column label="容量" width="150">
        <template slot-scope="scope">{{ scope.row.name }}</template>
      </el-table-column>
      <el-table-column prop="address" label="地址" show-overflow-tooltip>
      </el-table-column>
    </el-table>
    <div style="margin-top: 50px; text-align: right;">
      <el-button type="primary">增加停车场</el-button>
      <el-button type="primary" @click="dialogVisible=true">删除停车场</el-button>
    </div>
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
      <span>是否确定删除选择的停车场？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tableData3: [{
        date: 'parking lot 1',
        name: '50',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: 'parking lot 2',
        name: '60',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: 'parking lot 3',
        name: '40',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: 'parking lot 4',
        name: '80',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: 'parking lot 5',
        name: '40',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: 'parking lot 6',
        name: '60',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
      multipleSelection: [],
      dialogVisible: false

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
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    }
  }
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.el-table {
  /* margin-left: 50px;
  margin-top: 50px; */
  width: 100%;
  font-size: 14px;
}


/* .el-table__body{
  width: 90%;
}
.el-table tr,.el-table th{
  width: 900px;
} */

</style>
