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
      <el-col :span="2" :offset="16">
        <el-button
          type="primary"
          size="mini"
          @click="insertVisible = true"
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
              @click="deleteAdminInfo(scope.row.userId)"
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

    <el-dialog
      title="添加管理员"
      :visible.sync="insertVisible"
      width="50%">
      <el-form :model="addadmins" :rules="formRules" ref="addadminsRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户编号" prop="userId">
          <el-input v-model="addadmins.userId"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="loginName">
          <el-input v-model="addadmins.loginName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addadmins.password"></el-input>
        </el-form-item>
         <el-form-item label="姓名" prop="userName">
          <el-input v-model="addadmins.userName"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="telephone">
          <el-input v-model="addadmins.telephone"></el-input>
        </el-form-item>
        <el-form-item label="籍贯" prop="homeplace">
          <el-input v-model="addadmins.homeplace"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addadmins.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="insertVisible = false">取 消</el-button>
        <el-button type="primary" @click="insertAdmins (addadmins)">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="编辑用户"
      :visible.sync="updateVisible"
      width="50%">
      <el-form :model="updateadmins" :rules="formRules" ref="updateadminsRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户编号" prop="userId">
          <el-input v-model="updateadmins.userId"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="loginName">
          <el-input v-model="updateadmins.loginName"></el-input>
        </el-form-item>
         <el-form-item label="姓名" prop="userName">
          <el-input v-model="updateadmins.userName"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="telephone">
          <el-input v-model="updateadmins.telephone"></el-input>
        </el-form-item>
        <el-form-item label="籍贯" prop="homeplace">
          <el-input v-model="updateadmins.homeplace"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="updateadmins.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateAdmin(updateadmins)">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      currentPage: 1,
      admins: [],
      count: '',
      userInfo: '',
      insertVisible: false,
      updateVisible: false,
      addadmins: {
        userId: '',
        loginName: '',
        password:'',
        userName: '',
        telephone:'',
        homeplace:'',
        email: '',
      },
      updateadmins:{
        userId: '',
        loginName: '',
        userName: '',
        telephone:'',
        homeplace:'',
        email: '',
      },
      formRules:{
            userId: [
            { required: true, message: '请输入用户编号', trigger: 'blur' },
            ],
            loginName: [
            { required: true, message: '请输入账号', trigger: 'blur' },
            ],
            userName: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            ],
            password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            ],
            telephone: [
            { required: true, message: '请输入手机号', trigger: 'blur' },
            ],
            email: [
            { required: true, message: '请输入邮箱', trigger: 'blur' },
            ],
          },
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
    changeAdminInfo (admin) {
      this.updateVisible = true;
        this.updateadmins.userId = admin.userId;
        this.updateadmins.loginName = admin.loginName;
        this.updateadmins.userName = admin.userName;
        this.updateadmins.telephone = admin.telephone;
        this.updateadmins.email = admin.email;
        this.updateadmins.homeplace = admin.homeplace;
        return;
    },
    updateAdmin(updateadmins){
      var _this = this;
        updateadmins = JSON.stringify(updateadmins);
        _this.postRequest("/root/updateAdmin", {
          admin: updateadmins
        }).then(resp => {
           _this.$message({
            message: "修改成功",
            type: "success"
          });
          var data = resp.data;
          if (data) {
            _this.updateVisible = false;
            _this.getAllAdmins();
          }
        })
    },
    deleteAdminInfo (userId) {
      var _this = this;
      _this.userId = userId;
      this.$confirm("确定要删除该管理员信息吗","提示",{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/root/deleteAdmin?userId=" + _this.userId).then(() => {
          this.getAllAdmins();
        });
      });
    },
    insertAdmins (addadmins) {
      var _this = this;
        addadmins = JSON.stringify(addadmins);
        _this.postRequest("/root/addAdmin", {
            admin: addadmins
        }).then(resp => {
          this.$message({
          message: "添加成功",
          type: "success"
        });
          var data = resp.data;
            if (data) {
          this.insertVisible = false;
          this.getAllAdmins();
        }
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
