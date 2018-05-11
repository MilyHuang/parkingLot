<template>
  <div class="admin-user">
    <el-dropdown>
      <el-button type="primary">
        {{userType}}<i class="el-icon-arrow-down el-icon--right"></i>
      </el-button>
      <el-dropdown-menu slot="dropdown">
        <router-link to="/AdminManger">
          <el-dropdown-item>运营商</el-dropdown-item>
        </router-link>
        <router-link to="/AdminOperator">
          <el-dropdown-item>操作员</el-dropdown-item>
        </router-link>
      </el-dropdown-menu>
    </el-dropdown>
    <div class="admin-user-search">
      <el-input v-model="input" placeholder="用户名"></el-input>
      <el-button type="primary">查找</el-button>
    </div>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tableData3: [],
      multipleSelection: [],
      userType: "用户角色"
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
a {
  text-decoration: none;
}

.admin-user {
  width: 980px;
  height: 90%;
}

.admin-user-search {
  float: right;
}

.admin-user-operate {
  margin-top: 30px;
  text-align: right;
}

.el-table {
  margin-top: 20px;
}

.el-input {
  display: inline-block;
  width: 200px;
}

.el-dropdown {
  vertical-align: top;
}

.el-dropdown+.el-dropdown {
  margin-left: 15px;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.el-pagination {
  width: auto;
  text-align: center;
  margin: 50px auto;
}

</style>
