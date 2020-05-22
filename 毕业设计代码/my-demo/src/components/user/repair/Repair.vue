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
    <el-form-item>
      <el-button type="primary" @click="submitForm('repairForm')"
        >立即创建</el-button
      >
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      repairForm: {
        type: "",
        detail: "",
        time: "",
        userid: "",
        type: ""
      },
      rules: {
        
      }
    };
  },
  methods: {
    submitForm(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          _this.repairForm.userid = _this.$store.state.user.userId;
          _this.repairForm.time = new Date();
          var repairForm = JSON.stringify(_this.repairForm);
          _this
            .postRequest("/user/submitRepair", { repairForm: repairForm })
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
  }
};
</script>
<style scoped>
.demo-ruleForm{
  width: 60%;
}
</style>
