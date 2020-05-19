<template>
  <div>
    <el-card>
      <el-row style="margin-bottom: 10px" :gutter="20">
        <el-col :span="8">
          <el-input
            placeholder="请输入用户名"
            v-model="userInfo"
            clearable
            @clear="getUserList"
            class="input-with-select"
            size="mini"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUserList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" size="mini" @click="insertVisible = true"
            >添加用户</el-button
          >
        </el-col>
      </el-row>
      <el-table :data="users" border size="mini">
        <el-table-column prop="userId" label="用户编号" width="180">
        </el-table-column>
        <el-table-column prop="userName" label="姓名" width="180">
        </el-table-column>
        <el-table-column prop="sex" label="性别" width="180"> </el-table-column>
        <el-table-column prop="gender" label="年龄" width="180">
        </el-table-column>
        <el-table-column prop="idCard" label="身份证号" width="180">
        </el-table-column>
        <el-table-column prop="homeplace" label="籍贯" width="180">
        </el-table-column>
        <el-table-column prop="workplace" label="工作地" width="180">
        </el-table-column>
        <el-table-column prop="houseid" label="房间号" width="180">
        </el-table-column>
        <el-table-column prop="telephone" label="联系方式" width="180">
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="180">
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="mini" @click="changeInfo(scope.row)"
              >编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="deleteUser(scope.row.userId)"
              >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        background
        layout="prev, pager, next"
        :total="count"
        :currentPage="currentPage"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </el-card>

    <!-- 添加用户对话框 -->
    <el-dialog title="添加用户" :visible.sync="insertVisible" width="50%">
      <el-form
        :model="addFormData"
        :rules="formRules"
        ref="addFormDataRef"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户编号" prop="userId">
          <el-input v-model="addFormData.userId"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="loginName">
          <el-input v-model="addFormData.loginName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addFormData.password"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="userName">
          <el-input v-model="addFormData.userName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="addFormData.sex"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="gender">
          <el-input v-model="addFormData.gender"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="addFormData.idCard"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="telephone">
          <el-input v-model="addFormData.telephone"></el-input>
        </el-form-item>
        <el-form-item label="籍贯" prop="homeplace">
          <el-input v-model="addFormData.homeplace"></el-input>
        </el-form-item>
        <el-form-item label="工作地" prop="workplace">
          <el-input v-model="addFormData.workplace"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addFormData.email"></el-input>
        </el-form-item>
        <el-form-item label="房间号" prop="houseid">
          <el-input v-model="addFormData.houseid"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="insertVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser(addFormData)"
          >确 定</el-button
        >
      </span>
    </el-dialog>
    <el-dialog title="编辑用户" :visible.sync="updateVisible" width="50%">
      <el-form
        :model="updateuser"
        :rules="formRules"
        ref="updateadmin"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户编号" prop="userId">
          <el-input v-model="updateuser.userId"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="loginName">
          <el-input v-model="updateuser.loginName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="updateuser.password"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="userName">
          <el-input v-model="updateuser.userName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="updateuser.sex"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="gender">
          <el-input v-model="updateuser.gender"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="updateuser.idCard"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="telephone">
          <el-input v-model="updateuser.telephone"></el-input>
        </el-form-item>
        <el-form-item label="籍贯" prop="homeplace">
          <el-input v-model="updateuser.homeplace"></el-input>
        </el-form-item>
        <el-form-item label="工作地" prop="workplace">
          <el-input v-model="updateuser.workplace"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="updateuser.email"></el-input>
        </el-form-item>
        <el-form-item label="房间号" prop="houseid">
          <el-input v-model="updateuser.houseid"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUser(updateuser)"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      row: "",
      users: [],
      buildings: {},
      userInfo: "",
      currentPage: 1,
      count: 0,
      iconFormVisible: false,
      rowIndex: null,
      insertVisible: false,
      updateVisible: false,
      addFormData: {
        userId: "",
        loginName: "",
        userName: "",
        password: "",
        telephone: "",
        sex: "",
        gender: "",
        idCard: "",
        homeplace: "",
        workplace: "",
        email: "",
        houseid: ""
      },
      updateuser: {
        userId: "",
        loginName: "",
        userName: "",
        password: "",
        telephone: "",
        sex: "",
        gender: "",
        idCard: "",
        homeplace: "",
        workplace: "",
        email: "",
        houseid: ""
      },
      formRules: {
        userId: [
          { required: true, message: "请输入用户编号", trigger: "blur" }
        ],
        loginName: [{ required: true, message: "请输入账号", trigger: "blur" }],
        userName: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        uassword: [{ required: true, message: "请输入密码", trigger: "blur" }],
        sex: [{ required: true, message: "请输入性别", trigger: "blur" }],
        gender: [{ required: true, message: "请输入年龄", trigger: "blur" }],
        idCard: [
          { required: true, message: "请输入身份证号", trigger: "blur" }
        ],
        telephone: [
          { required: true, message: "请输入手机号", trigger: "blur" }
        ],
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }]
      }
    };
  },
  methods: {
    //获取用户列表信息
    getUserList() {
      var _this = this;
      _this
        .postRequest("/admin/getUserPage", {
          userid: _this.$store.state.user.userId,
          page: _this.currentPage,
          size: 10,
          userInfo: _this.userInfo
        })
        .then(resp => {
          var data = resp.data;
          console.log(data);
          _this.buildings = resp.data.buildings;
          _this.users = data.users;
          _this.count = data.userCount;
        });
    },
    //处理分页
    handleCurrentChange(newPage) {
      this.userPage = newPage;
      this.getUserList();
    },
    addUser(addFormData) {
      var _this = this;
      addFormData = JSON.stringify(addFormData);
      _this
        .postRequest("/user/addUser", {
          user: addFormData
        })
        .then(resp => {
          this.$message({
            message: "添加成功",
            type: "success"
          });
          var data = resp.data;
          if (data) {
            this.insertVisible = false;
            this.getUserList();
          }
        });
    },
    changeInfo(user) {
      this.updateVisible = true;
      this.updateuser.userId = user.userId;
      this.updateuser.loginName = user.loginName;
      this.updateuser.password = user.upassword;
      this.updateuser.userName = user.userName;
      this.updateuser.sex = user.sex;
      this.updateuser.gender = user.gender;
      this.updateuser.telephone = user.telephone;
      this.updateuser.idCard = user.idCard;
      this.updateuser.email = user.email;
      this.updateuser.homeplace = user.homeplace;
      this.updateuser.workplace = user.workplace;
      this.updateuser.houseid = user.houseid;
      return;
    },

    updateUser(updateuser) {
      var _this = this;
      updateuser = JSON.stringify(updateuser);
      _this
        .postRequest("/user/updateUser", {
          user: updateuser
        })
        .then(resp => {
          _this.$message({
            message: "修改成功",
            type: "success"
          });
          var data = resp.data;
          if (data) {
            _this.updateVisible = false;
            _this.getUserList();
          }
        });
    },
    deleteUser(userId) {
      var _this = this;
      _this.userId = userId;
      _this
        .$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
        .then(() => {
          _this
            .deleteRequest("/user/deleteUser?userId=" + _this.userId)
            .then(resp => {
              _this.$message({
                message: "删除成功",
                type: "success"
              });
              var data = resp.data;
              if (data) {
                _this.getUserList();
              }
            });
        })
        .catch(() => {
          _this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    }
  },
  mounted() {
    this.getUserList();
  }
};
</script>
<style scoped></style>
