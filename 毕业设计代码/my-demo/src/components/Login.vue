<template>
  <div class="note" :style ="note">
      <el-card>
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
      </el-card>
        
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
          },
          note: {
            backgroundImage: "url(" + require("D:/毕业设计代码/qixiaowei/毕业设计代码/my-demo/src/assets/img/登录界面.jpg") + ")",
            backgroundRepeat: "no-repeat",
            backgroundSize: "25px auto",
            marginTop: "5px",
          },
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
.el-card {
    background-color: steelblue;
    left: 500px;
    right: 500px;
    top: 150px;
    bottom: 150px;
    position: absolute; 
    transform: translate(70%,40%);
    width: 30%;
    height: 40%;
}

.login_form{
    position: absolute; 
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    bottom: 0;
    width: 70%;
    padding: 0 10px;
    size: border-box;
}

.btns{
    display: flex;
    justify-content: flex-end;
}
</style> 