<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          v-model="repairInfo"
          class="input-with-select"
          size="mini"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getAllRepairs()"
          ></el-button>
        </el-input>
      </el-col>
    </el-row>
    <el-table :data="repairs" border style="width: 100%" size="mini">
      <el-table-column prop="userName" label="业主姓名" width="150">
      </el-table-column>
      <el-table-column prop="hname" label="房间地址" width="300">
      </el-table-column>
      <el-table-column prop="telephone" label="联系方式" width="300">
      </el-table-column>
      <el-table-column prop="type" label="报修类型" width="300"> </el-table-column>
      <el-table-column prop="detail" label="报修详情" width="300">
      </el-table-column>
      <el-table-column prop="status" label="状态" width="300">
        <template slot-scope="scope">
        <el-switch
          v-model="scope.row.status" :disabled="true">
        </el-switch>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-tooltip
            class="item"
            effect="dark"
            content="修改信息"
            placement="top"
          >
            <el-button
              type="primary"
              size="small"
              circle
              @click="changeInfo(scope.row)"
              class="el-icon-info"
            ></el-button>
          </el-tooltip>

          <el-tooltip
            class="item"
            effect="dark"
            content="删除信息"
            placement="top"
          >
            <el-button
              type="danger"
              size="small"
              circle
              @click="deleteInfo(scope.row.id)"
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
        :total="repairCount"
        :currentPage="currentPage"
        @current-change="currentChange"
      >
      </el-pagination>
    </div>

    <el-dialog title="编辑" :visible.sync="updateVisible" width="50%">
      <el-form
        :model="updaterepairs"
        :rules="formRules"
        ref="updaterepairsRef"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="status" prop="status">
          <el-input v-model="updaterepairs.status"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateRepair(updaterepairs)">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentPage: 1,
      repairs: [],
      repairCount: 0,
      repairInfo: "",
      updateVisible: false,
      updaterepairs: {
        id: "",
        time: "",
        status: "",
      },
      formRules: {
        status: [
          { required: true, message: "请输入修理状态", trigger: "blur" },
        ],
      }
    };
  },
  mounted() {
    this.getAllRepairs();
  },
  methods: {
    getAllRepairs() {
      var _this = this;
      this.getRequest(
        "/admin/getallrepairs?userId=" +
          _this.$store.state.user.userId +
          "&page=" +
          _this.currentPage +
          "&size=" +
          10 +
          "&repairInfo=" +
          _this.repairInfo
      ).then(resp => {
        console.log(resp.data);
        _this.repairs = resp.data.repairs;
        _this.building = resp.data.building;
        _this.repairCount = resp.data.repairCount;
      });
    },
    currentChange(currentPage) {
      this.currnetPage = currentPage;
      this.getAllRepairs();
    },
    changeInfo(repair) {
      this.updateVisible = true;
      this.updaterepairs.id = repair.id;
      this.updaterepairs.userName = repair.userName;
      this.updaterepairs.telephone = repair.telephone;
      this.updaterepairs.hname = repair.hname;
      this.updaterepairs.type = repair.type;
      this.updaterepairs.detail = repair.detail;
      this.updaterepairs.status = repair.status;
      return;
    },
    updateRepair(updaterepairs) {
      var _this = this;
      updaterepairs = JSON.stringify(updaterepairs);
      this.$refs.updaterepairsRef.validate(valid => {
        if (valid) {
          _this.updaterepairs.time = new Date();
          _this
            .postRequest("/admin/updateRepair", {
              repair: updaterepairs
            })
            .then(resp => {
              _this.$message({
                message: "修改成功",
                type: "success"
              });
              var data = resp.data;
              if (data) {
                _this.updateVisible = false;
                _this.getAllRepairs();
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
    deleteInfo(id) {
      var _this = this;
      _this.id = id;
      _this
        .$confirm("确定要删除该管理员信息吗", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
        .then(() => {
          this.deleteRequest("/admin/deleteRepair?id=" + _this.id).then(
            () => {
              _this.getAllRepairs();
            }
          );
        });
    },
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
