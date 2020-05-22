<template>
  <el-form
    :model="repairForm"
    :rules="rules"
    ref="repairForm"
    label-width="100px"
    class="demo-ruleForm"
  >
    <el-form-item label="报修类型" prop="type">
      <el-select
        v-model="repairForm.type"
        placeholder="请选择报修类型"
        style="width: 100%"
      >
        <el-option label="水路问题" value="1"></el-option>
        <el-option label="电路问题" value="2"></el-option>
        <el-option label="天然气问题" value="3"></el-option>
        <el-option label="家具/家装" value="4"></el-option>
        <el-option label="疏通管道" value="5"></el-option>
        <el-option label="其他报修" value="6"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="报修详情" prop="detail">
      <el-input type="textarea" v-model="repairForm.detail"></el-input>
    </el-form-item>
    <el-form-item label="上传图片" style="text-align: left">
      <el-upload
        action="/user/submitRepair"
        list-type="picture-card"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
        :data="repairForm"
        :auto-upload="false"
        :http-request="uploadFile"
        :multiple="multiple"
        ref="upload"
      >
        <i class="el-icon-plus"></i>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="" />
      </el-dialog>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('repairForm')"
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
      multiple: true,
      repairForm: {
        type: "",
        detail: "",
        time: "",
        userid: this.$store.state.user.userId
      },
      dialogImageUrl: "",
      dialogVisible: false,
      disabled: false,
      rules: {},
      dialogImageUrl: "",
      dialogVisible: false,
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
          _this.fileData = new FormData();
          _this.$refs.upload.submit();
          _this.fileData.append("refForm", JSON.stringify(_this.repairForm));
          console.log(_this.fileData.get("refForm").userid);
          let config = { headers: { "Content-Type": "multipart/form-data" } };
          axios
            .post("/user/submitRepair", _this.fileData, config)
            .then(resp => {
              this.$message({
                type: "success",
                message: "发布成功"
              });
              this.$router.go(0);
            });
        } else {
          this.$message({
            type: "warning",
            message: "请按规则填写表单"
          });
        }
      });
    }
  }
};
</script>
<style scoped>
.demo-ruleForm {
  width: 60%;
}
</style>
