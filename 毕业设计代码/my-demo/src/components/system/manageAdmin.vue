<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          v-model="userInfo"
          class="input-with-select"
          size="mini"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getAllAdmins()"
          ></el-button>
        </el-input>
      </el-col>
      <el-col :span="4" :offset="14">
        <el-button
          type="primary"
          size="mini"
          @click="insertAdmins()"
          style="width: 100%"
          >添加管理员</el-button
        >
      </el-col>
    </el-row>
    <el-table :data="admins" border style="width: 100%" size="mini">
      <el-table-column fixed prop="userId" label="用户账户" width="200">
      </el-table-column>
      <el-table-column prop="userName" label="姓名" width="150">
      </el-table-column>
      <el-table-column prop="loginName" label="登录名" width="150">
      </el-table-column>
      <el-table-column prop="homeplace" label="住址" width="300">
      </el-table-column>
      <el-table-column prop="telephone" label="电话" width="300">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="300"> </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-tooltip
            class="item"
            effect="dark"
            content="修改管理员信息"
            placement="top"
          >
            <el-button
              type="primary"
              size="small"
              circle
              @click="changeAdminInfo(scope.row)"
              class="el-icon-info"
            ></el-button>
          </el-tooltip>

          <el-tooltip
            class="item"
            effect="dark"
            content="删除管理员信息"
            placement="top"
          >
            <el-button
              type="danger"
              size="small"
              circle
              @click="deleteAdminInfo(scope.row)"
              class="el-icon-delete"
            ></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 5px">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="count"
        :currentPage="currentPage"
        @current-change="currentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      currentPage: 1,
      admins: [],
      count: '',
      userInfo: ''
    }
  },
  mounted () {
    this.getAllAdmins()
  },
  methods: {
    getAllAdmins () {
      /* 获得所有的二级管理员 */
      var _this = this
      this.getRequest(
        '/root/getalladmins?page=' +
          _this.currentPage +
          '&size=' +
          10 +
          '&userInfo=' +
          _this.userInfo
      ).then(resp => {
        _this.admins = resp.data.admins
        _this.count = resp.data.count
      })
    },
    currentChange (currentPage) {
      this.currnetPage = currentPage
      this.getAllAdmins()
    },
    changeAdminInfo (row) {},
    deleteAdminInfo (row) {},
    insertAdmins (row) {}
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
