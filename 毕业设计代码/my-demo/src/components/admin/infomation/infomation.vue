<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="100px"
    class="demo-ruleForm"
  >
    <el-form-item label="标题" prop="head">
      <el-input v-model="ruleForm.head"></el-input>
    </el-form-item>
    <el-form-item label="内容" prop="content">
      <el-input type="textarea" v-model="ruleForm.content"></el-input>
    </el-form-item>
    <el-form-item label="上传图片" style="text-align: left">
      <el-upload
        action="/user/submitRepair"
        list-type="picture-card"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
        :data="ruleForm"
        :auto-upload="false"
        :http-request="uploadFile"
        multiple
        ref="upload"
      >
        <i class="el-icon-plus"></i>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="" />
      </el-dialog>
    </el-form-item>

    <el-form-item label="消息类型" prop="content">
      <el-select
        v-model="ruleForm.type"
        placeholder="请选择消息类型"
        style="width: 100%"
      >
        <el-option label="失物招领" value="1"></el-option>
        <el-option label="寻物启事" value="2"></el-option>
        <el-option label="业主通知" value="3"></el-option>
        <el-option label="新鲜事" value="4"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')"
        >立即创建</el-button
      >
    </el-form-item>
  </el-form>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      dialogImageUrl: "",
      dialogVisible: false,
      ruleForm: {
        head: "",
        content: "",
        infotime: "",
        userId: "",
        type: "",
        userName: "",
        avator: ""
      },
      rules: {
        head: [
          { required: true, message: "请输入标题", trigger: "blur" },
          {
            pattern: /^([1-9])|[\u0391-\uFFE5]|([\u4E00-\u9FA5]+[0-9])|([0-9]+[\u4E00-\u9FA5]){1,20}$/,
            message: "请输入合理的标题"
          }
        ]
      },
      fileData: ""
    };
  },
  methods: {
    uploadFile(content) {
      this.fileData.append("files", content.file);
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    submitForm(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          _this.ruleForm.userId = _this.$store.state.user.userId;
          _this.ruleForm.userName = _this.$store.state.user.userName;
          _this.ruleForm.avator = _this.$store.state.user.avator;
          _this.ruleForm.infotime = new Date();
          _this.fileData = new FormData();
          _this.$refs.upload.submit();
          _this.fileData.append("ruleForm", JSON.stringify(_this.ruleForm));
          let config = { headers: { "Content-Type": "multipart/form-data" } };
          axios
            .post("/admin/submitInfomation", _this.fileData, config)
            .then(resp => {
              this.$message({
                type: "success",
                message: "发布成功"
              });
              /* this.$router.go(0); */
            });
        } else {
          this.$message({
            type: "warning",
            message: "请按规则填写表单"
          });
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style scoped>
.demo-ruleForm {
  width: 60%;
}
</style>
