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
      <el-card :key="index" class="el-card">
        <el-row>
          <el-col :span="8"
            ><div>
              <el-tag
                @click="goDetail(item)"
                v-if="item.type === 1"
                type="danger"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
              <el-tag
                @click="goDetail(item)"
                v-if="item.type === 2"
                type="warning"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
              <el-tag
                @click="goDetail(item)"
                v-if="item.type === 3"
                type="primary"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
              <el-tag
                @click="goDetail(item)"
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
            ><div
              style="text-align: left; margin-left: 10px; margin-top: 20px; margin-bottom: 20px"
              class="grid-content bg-purple"
            >
              {{ item.content }}
            </div></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="24" style="text-align: left">
            <template v-if="item.picture != null">
              <template v-for="item1 in JSON.parse(item.picture.picture)">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="item1.imgUrl"
                  :preview-src-list="
                    handlePictureCardPreview(item.picture.picture)
                  "
                >
                </el-image>
              </template>
            </template>
          </el-col>
        </el-row>

        <el-row :gutter="200">
          <el-col :span="8"
            ><div class="grid-bottom bg-purple">
              <el-tag type="primary">
                {{ item.userId }}
              </el-tag>
            </div></el-col
          >
          <el-col :span="16"
            ><div class="grid-bottom bg-purple">
              <el-tag type="info">
                {{ formatDate(item.infotime) }}
              </el-tag>
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
    show(item) {
      console.log(item.picture.picture);
    },
    handlePictureCardPreview(picture) {
      this.urls = [];
      var x = JSON.parse(picture);
      for (var i = 0; i < x.length; i++) {
        this.urls.push(x[i].imgUrl);
      }
      return this.urls;
    },
    goDetail(item) {
      this.$router.push({
        path: "detailInfomation",
        query: { Info: JSON.stringify(item) }
      });
    },
    getAllInformations() {
      /* 获得所有的二级管理员 */
      var _this = this;
      this.getRequest(
        "/admin/getallinformations?info=" +
          _this.info +
          "&userid=" +
          _this.$store.state.user.userId
      ).then(resp => {
        _this.information = resp.data.information;
        console.log(_this.information);
      });
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
    },
    formatDate(value) {
      var date = new Date(value);
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var day = date.getDate();
      if (month < 10) {
        month = "0" + month;
      }
      if (day < 10) {
        day = "0" + day;
      }
      return year + "-" + month + "-" + day;
    }
  }
};
</script>

<style scoped>
.el-card {
  width: 75%;
}
.el-card:hover {
  cursor: pointer;
}
</style>
