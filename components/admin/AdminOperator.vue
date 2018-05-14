<template>
  <div>
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
      label="姓名"
      width="120">
      <template slot-scope="scope">{{ scope.row.date }}</template>
    </el-table-column>
    <el-table-column
      prop="name"
      label="日期"
      width="120">
    </el-table-column>
    <el-table-column
      prop="address"
      label="地址"
      show-overflow-tooltip>
    </el-table-column>
  </el-table>
    <div class="admin-user-operate">
      <el-button type="primary">增加</el-button>
      <el-button type="primary" @click="dialogVisible=true">删除</el-button>
    </div>
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
      <span>是否确定删除选择的用户？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
</el-dialog>
    <el-pagination background layout="prev, pager, next" :total="1">
    </el-pagination>
  </div>
    
</template>
<script>
export default {
  data() {
    return {
      dialogVisible:false,
      tableData3: [],
        multipleSelection: []
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
<style scoped>
.admin-user-operate {
  margin-top: 30px;
  text-align: right;
}

.el-table {
  margin-top: 20px;
}

.el-pagination {
  width: auto;
  text-align: center;
  margin: 50px auto;
}

</style>
