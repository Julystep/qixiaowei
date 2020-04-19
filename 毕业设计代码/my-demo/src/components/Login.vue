<template>
  <div class="login_container">
        <!-- 登录表单区域 -->
        <el-form  ref="loginFormRef"  :model="loginForm" :rules="loginFormRules" label-width="0px" class="login_form">
            <el-form-item prop="loginName">
                <el-input v-model="loginForm.loginName" prefix-icon="el-icon-user-solid"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="loginForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
            </el-form-item >
            <el-form-item class="btns">
                <el-button type="primary" @click="login(loginForm)">登录</el-button>
                <el-button type="info" @click="resetLoginForm">重置</el-button>
            </el-form-item>
        </el-form>
  </div>
</template>

<script>
export default {
  data() {
      return {
          //登录表单的数据绑定对象
          loginForm: {
              loginName :'',
              password :''
          },
          loginFormRules: {
          loginName: [
              {required: true, message: "请输入登录名", trigger: "blur"},
              { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
          ],
          password: [
              {required: true, message: "请输入登录密码", trigger: "blur"},
              { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
          ]
          }
      } 
  },
  methods: {
      resetLoginForm(){
          //console.log(this);
          this.$refs.loginFormRef.resetFields();
      },
      login(loginForm) {
          console.log(this);
          this.postRequest('/login',{
              loginName: loginForm.loginName,
              password: loginForm.password
          }).then(resp => {
            if(resp.data === ""){
              this.$message({
                  type: "error",
                  message: "登录失败"
              })
              }
            if(resp.data !== ""){
            this.$message({
              type:"success",
              message:"登录成功"
            })
            this.$store.commit("login", resp.data);
            this.$router.replace({ path: '/index' });
          }
          })
      }
  }
}
</script>


<style scoped>
.login_container{
    background-color: #2b4b6b;
    height: 100%;
}

.login_form{
    position: absolute; 
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    bottom: 0;
    width: 50%;
    padding: 0 20px;
    size: border-box;
}

.btns{
    display: flex;
    justify-content: flex-end;
}
</style> 