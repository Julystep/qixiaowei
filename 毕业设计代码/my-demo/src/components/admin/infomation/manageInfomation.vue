<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="8">
        <el-input
          placeholder="请输入内容"
          v-model="info"
          clearable
          @clear="getAllInformations"
          class="input-with-select"
          size="mini"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getAllInformations()"
          ></el-button>
        </el-input>
      </el-col>
    </el-row>

    <template v-for="(item, index) in information">
      <el-card :key="index">
        <el-row>
          <el-col :span="8"
            ><div>
              <el-tag v-if="item.type === 1" type="danger" style="width:100%">{{
                item.head
              }}</el-tag>
              <el-tag
                v-if="item.type === 2"
                type="warning"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
              <el-tag
                v-if="item.type === 3"
                type="primary"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
              <el-tag
                v-if="item.type === 4"
                type="success"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
            </div></el-col
          >
          <el-col :span="4" :offset="12">
            <el-button
              type="primary"
              size="small"
              circle
              @click="changeInfo(item)"
              class="el-icon-edit"
            >
            </el-button>
            <el-button
              type="danger"
              size="small"
              circle
              @click="deleteInfo(item.id)"
              class="el-icon-delete"
            >
            </el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24"
            ><div class="grid-content bg-purple">
              {{ item.content }}
            </div></el-col
          >
        </el-row>
        <el-row :gutter="200">
          <el-col :span="8"
            ><div class="grid-bottom bg-purple">
              {{ item.userId }}
            </div></el-col
          >
          <el-col :span="16"
            ><div class="grid-bottom bg-purple">
              {{ item.infotime }}
            </div>
          </el-col>
        </el-row>
      </el-card>
    </template>

    <el-dialog
      title="编辑房间信息"
      :visible.sync="updateInfoVisible"
      width="50%"
    >
      <el-form
        :model="updateinfo"
        :rules="formRules"
        ref="updateinfoRef"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="标题" prop="head">
          <el-input v-model="updateinfo.head"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input v-model="updateinfo.content"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select
            v-model="updateinfo.value"
            placeholder="请选择消息类型"
            style="width: 100%"
          >
            <el-option label="失物招领" value="1"></el-option>
            <el-option label="寻物启事" value="2"></el-option>
            <el-option label="业主通知" value="3"></el-option>
            <el-option label="新鲜事" value="4"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateInfoVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateInfo(updateinfo)"
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
      information: [],
      info: "",
      updateInfoVisible: false,
      updateinfo: {
        id: "",
        head: "",
        content: "",
        infotime: "",
        userId: "",
        value: ""
      },
      formRules: {}
    };
  },
  mounted() {
    this.getAllInformations();
  },
  methods: {
    getAllInformations() {
      /* 获得所有的二级管理员 */
      var _this = this;
      this.getRequest("/admin/getallinformations?info=" + _this.info).then(
        resp => {
          _this.information = resp.data.information;
          console.log(_this.information);
        }
      );
    },
    changeInfo(informations) {
      console.log(informations);
      this.updateInfoVisible = true;
      this.updateinfo.head = informations.head;
      this.updateinfo.content = informations.content;
      this.updateinfo.infotime = informations.infotime;
      this.updateinfo.userId = informations.userId;
      this.updateinfo.value = informations.type + "";
      this.updateinfo.id = informations.id;
      return;
    },
    updateInfo(updateinfo) {
      var _this = this;
      _this.$refs.updateinfoRef.validate(valid => {
        if (valid) {
          _this.updateinfo.userId = _this.$store.state.user.userId;
          _this.updateinfo.infotime = new Date();
          updateinfo = JSON.stringify(_this.updateinfo);
          _this
            .postRequest("/admin/updateInfo", {
              item: updateinfo
            })
            .then(resp => {
              _this.$message({
                message: "修改成功",
                type: "success"
              });
              var data = resp.data;
              if (data) {
                _this.updateInfoVisible = false;
                _this.getAllInformations();
                _this.$router.go(0);
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
      this.$confirm("确定要删除该公告信息吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.deleteRequest("/admin/deleteInfo?id=" + _this.id).then(() => {
          _this.getAllInformations();
        });
      });
    }
  }
};
</script>

<style scoped>
.el-card {
  width: 75%;
}
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #dfe3e9;
}
.grid-head {
  border-radius: 4px;
  min-height: 30px;
}
.grid-content {
  border-radius: 4px;
  min-height: 80px;
  width: auto;
}
.grid-bottom {
  border-radius: 4px;
  min-height: 30px;
}
</style>
