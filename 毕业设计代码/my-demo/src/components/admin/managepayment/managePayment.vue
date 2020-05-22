<template>
  <div>
    <el-row style="margin-bottom: 10px" :gutter="20">
      <el-col :span="12">
        <el-form :inline="true">
          <el-input
            placeholder="请输入用户名"
            v-model="userInfo"
            clearable
            style="width: 50%"
            @clear="getUsersByAdminId"
            class="input-with-select"
            size="mini"
          >
          </el-input>
          <el-date-picker
            size="mini"
            v-model="date"
            type="month"
            placeholder="选择月"
          >
          </el-date-picker>

          <el-button
            icon="el-icon-search"
            size="mini"
            @click="getUsersByAdminId"
          ></el-button
        ></el-form>
      </el-col>
      <el-col :span="6">
        <el-upload
          accept="application/vnd.ms-excel"
          action="/admin/importRecordings"
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          style="position: absolute; top: 0px;"
        >
          <el-button
            size="mini"
            type="success"
            icon="el-icon-sort-down"
            style="width: 100%"
          >
            导入月份账单
          </el-button>
        </el-upload>
      </el-col>
      <el-col :offset="1" :span="6">
        <el-button
          class="el-icon-sort-up"
          type="primary"
          size="mini"
          @click="exportInfo()"
          >导出用户缴费表</el-button
        >
      </el-col>
    </el-row>
    <el-table :data="users" border size="mini">
      <el-table-column prop="userId" label="用户编号" width="180">
      </el-table-column>
      <el-table-column prop="userName" label="姓名" width="180">
      </el-table-column>
      <el-table-column prop="hid" label="房间号" width="180"> </el-table-column>
      <el-table-column prop="telephone" label="联系方式" width="180">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="180"> </el-table-column>
      <el-table-column label="水费" width="180">
        <template slot-scope="scope">
          <template v-if="scope.row.charge.waterFee == null">
            暂无数据
          </template>
          <template v-if="scope.row.charge.waterFee != null">
            {{ scope.row.charge.waterFee }}
          </template>
        </template>
      </el-table-column>
      <el-table-column label="电费" width="180">
        <template slot-scope="scope">
          <template v-if="scope.row.charge.electricityFee == null">
            暂无数据
          </template>
          <template v-if="scope.row.charge.electricityFee != null">
            {{ scope.row.charge.electricityFee }}
          </template>
        </template>
      </el-table-column>
      <el-table-column label="物业费" width="180">
        <template slot-scope="scope">
          <template v-if="scope.row.charge.propertyCosts == null">
            暂无数据
          </template>
          <template v-if="scope.row.charge.propertyCosts != null">
            {{ scope.row.charge.propertyCosts }}
          </template>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="360">
        <template slot-scope="scope">
          <template v-if="scope.row.charge.id == 0">
            <el-button size="mini" @click="insertRecording(scope.row)"
              >添加缴费记录</el-button
            >
          </template>
          <template v-if="scope.row.charge.id != 0">
            <template v-if="scope.row.charge.state == 1">
              <el-button size="mini" type="info" disabled>已提交</el-button>
            </template>
            <template v-if="scope.row.charge.state == 0">
              <el-button
                size="mini"
                type="success"
                @click="sendMailRecording(scope.row)"
                >点击提醒缴费</el-button
              >
              <el-button
                size="mini"
                type="primary"
                @click="confirmRecording(scope.row)"
                >确认已经缴费</el-button
              >
            </template>
          </template>
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
    <el-dialog title="添加缴费记录" :visible.sync="insertDialog" width="50%">
      <el-form
        :model="addFormData"
        :rules="formRules"
        ref="addFormDataRef"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户编号" prop="userId">
          <el-input v-model="addFormData.userId" disabled></el-input>
        </el-form-item>
        <el-form-item label="水费" prop="waterFee">
          <el-input v-model="addFormData.waterFee"></el-input>
        </el-form-item>
        <el-form-item label="电费" prop="electricityFee">
          <el-input v-model="addFormData.electricityFee"></el-input>
        </el-form-item>
        <el-form-item label="物业费" prop="propertyCosts">
          <el-input v-model="addFormData.propertyCosts"></el-input>
        </el-form-item>
        <el-form-item label="选择月份" prop="date">
          <el-date-picker
            style="width: 100%"
            size="mini"
            v-model="addFormData.date"
            type="month"
            placeholder="选择月"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="insertDialog = false">取 消</el-button>
        <el-button type="primary" @click="insertRecordingDetail()"
          >确 定</el-button
        >
      </span>
    </el-dialog>
    <el-dialog :visible.sync="exportDialog" :close-on-click-modal="false">
      <el-date-picker
        size="mini"
        v-model="dateValue"
        type="month"
        placeholder="选择月"
      >
      </el-date-picker>
      <el-button
        size="mini"
        @click="exportExcel"
        type="success"
        style="margin-top: 10px"
        >导出信息</el-button
      >
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      buildings: {},
      users: [],
      count: 0,
      currentPage: 1,
      dateValue: new Date(),
      userInfo: "",
      date: new Date(),
      row: {},
      exportDialog: false,
      insertDialog: false,
      addFormData: {
        userId: "",
        waterFee: "",
        electricityFee: "",
        propertyCosts: "",
        date: ""
      },
      formRules: {
        waterFee: [
          { required: true, message: "请输入账号", trigger: "blur" },
          { pattern: /[1-9]{1}[0-9]/, message: "请输入数字", trigger: "blur" }
        ],
        electricityFee: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { pattern: /[1-9]{1}[0-9]/, message: "请输入数字", trigger: "blur" }
        ],
        propertyCosts: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { pattern: /[1-9]{1}[0-9]/, message: "请输入数字", trigger: "blur" }
        ],
        date: [
          {
            required: true,
            message: "请选择账单的日期",
            trigger: "blur"
          }
        ]
      }
    };
  },
  mounted() {
    this.getUsersByAdminId();
  },
  methods: {
    sendMailRecording(row) {
      console.log(row);
      var _this = this;
      this.postRequest("/mail/sendMail", {
        userId: row.userId,
        date: _this.date
      }).then(resp => {
        console.log(resp.data);
        if (resp.data == "true") {
          _this.$message({
            type: "success",
            message: "邮件发送成功"
          });
        } else {
          _this.$message({
            type: "error",
            message: "邮件发送失败，可能原因是用户没有有效的邮箱地址"
          });
        }
      });
    },
    confirmRecording(row) {
      var _this = this;
      _this.postRequest("/admin/changeState", {
        userId: row.userId,
        date: _this.date
      }).then(resp => {
        if (resp.data == true) {
          _this.$message({
            type: "success",
            message: "修改成功"
          });
          _this.$router.go(0);
        } else {
          _this.$message({
            type: "error",
            message: "修改失败"
          });
        }
      });
    },
    dateFormat(fmt, date) {
      let ret;
      const opt = {
        "Y+": date.getFullYear().toString(), // 年
        "m+": (date.getMonth() + 1).toString(), // 月
        "d+": date.getDate().toString(), // 日
        "H+": date.getHours().toString(), // 时
        "M+": date.getMinutes().toString(), // 分
        "S+": date.getSeconds().toString() // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
      };
      for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
          fmt = fmt.replace(
            ret[1],
            ret[1].length == 1 ? opt[k] : opt[k].padStart(ret[1].length, "0")
          );
        }
      }
      return fmt;
    },
    exportExcel() {
      var dateValue = this.dateFormat("YYYYmm", this.dateValue);
      console.log(dateValue);
      window.open(
        "/admin/exportRecordings?userid=" +
          this.$store.state.user.userId +
          "&date=" +
          dateValue
      );
      this.isloading = false;
      this.exportDialog = false;
    },
    exportInfo() {
      /* 显示导出学生信息的页面 */
      this.exportDialog = true;
    },
    fileUploadSuccess() {
      this.$message({
        type: "success",
        message: "导入文件成功"
      });
    },
    fileUploadError() {
      this.$message({
        type: "error",
        message: "导入文件失败"
      });
    },
    insertRecordingDetail() {
      var _this = this;
      _this.$refs.addFormDataRef.validate(valid => {
        if (valid) {
          var addFormData = JSON.stringify(_this.addFormData);
          _this
            .postRequest("/admin/insertRecording", {
              addFormData: addFormData
            })
            .then(resp => {
              this.$message({
                message: "发布成功",
                type: "success"
              });
              var data = resp.data;
              if (data) {
                this.insertVisible = false;
                this.getUsersByAdminId();
                this.$router.go(0);
              }
            });
        } else {
          _this.$message({
            type: "error",
            message: "表单未按照规则填写"
          });
        }
      });
    },
    handleCurrentChange(newPage) {
      this.userPage = newPage;
      this.getUsersByAdminId();
    },
    getUsersByAdminId() {
      var _this = this;
      _this
        .postRequest("/admin/getUserPageBytime", {
          userid: _this.$store.state.user.userId,
          page: _this.currentPage,
          size: 10,
          userInfo: _this.userInfo,
          date: _this.date
        })
        .then(resp => {
          var data = resp.data;
          console.log(data);
          _this.buildings = resp.data.buildings;
          _this.users = data.users;
          _this.count = data.userCount;
        });
    },
    insertRecording(row) {
      this.row = row;
      this.addFormData.userId = this.row.userId;
      this.insertDialog = true;
    }
  }
};
</script>
