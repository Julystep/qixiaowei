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
export default {
  data() {
    return {
      ruleForm: {
        head: "",
        content: "",
        infotime: "",
        userId: "",
        type: ""
      },
      rules: {
        head: [
          { required: true, message: "请输入标题", trigger: "blur" },
          { pattern: /^([1-9])|[\u0391-\uFFE5]|([\u4E00-\u9FA5]+[0-9])|([0-9]+[\u4E00-\u9FA5]){1,20}$/, 
            message: "请输入合理的标题" }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          _this.ruleForm.userId = _this.$store.state.user.userId;
          _this.ruleForm.infotime = new Date();
          var ruleForm = JSON.stringify(_this.ruleForm);
          _this
            .postRequest("/admin/submitInfomation", { ruleForm: ruleForm })
            .then(resp => {
              _this.$message({ message: "发布成功", type: "success" });
              _this.$router.go(0);
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
.demo-ruleForm{
  width: 60%;
}
</style>
