<template>
  <el-container class="index-conyainer">
    <el-header>
      <el-row style="width: 100%; text-align: left">
        <el-col :span="12">
          <p>qw小区物业管理系统</p>
        </el-col>
        <el-col :offset="10" :span="1">
          <el-dropdown @command="handleCommand"
            ><span
              ><el-avatar
                style="margin-top: 12px"
                size="large"
                :src="circleUrl"
              ></el-avatar> </span
            ><el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="changeUserface"
                >修改头像</el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
        <el-col :span="1">
          <el-button
            style="margin-top: 17px"
            type="info"
            size="small"
            @click="logout"
            >退出</el-button
          >
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          router
          default-active="2"
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          :unique-opened="true"
        >
          <template v-if="limitid === '1'">
            <el-submenu index="/system">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>系统管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/manageAdmins"
                  >二级管理员管理</el-menu-item
                >
                <el-menu-item index="/manageBuildings"
                  >楼栋信息管理</el-menu-item
                >
              </el-menu-item-group>
            </el-submenu>
          </template>
          <template v-if="limitid === '2'">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>用户管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/user">用户管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>楼层管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/buildinganduser">房间管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>公告管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/infomation">发布公告</el-menu-item>
                <el-menu-item index="/manageInfomation">公告管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>报修管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/managerepair">报修管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>缴费管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/managepayment">缴费管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </template>
          <template v-if="limitid === '3'">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>物业报修</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/repair">我要报修</el-menu-item>
                <el-menu-item index="/viewRepair">报修状态</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>公告信息</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/viewInfo">小区公告</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>缴费管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/payment">缴费</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </template>
        </el-menu>
      </el-aside>

      <el-main>
        <router-view />
      </el-main>
    </el-container>
    <el-dialog title="提示" :visible.sync="changeUserfaceVisiable" width="30%">
      <el-upload
        ref="upload"
        class="avatar-uploader"
        action="/userface/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :on-error="handleAvatarError"
        :before-upload="beforeAvatarUpload"
        :on-change="avatarChange"
        :auto-upload="false"
        :data="data"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
      <el-button
        style="margin-left: 10px; margin-top: 10px"
        size="small"
        type="success"
        @click="submitUpload"
        >修改头像</el-button
      >
    </el-dialog>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      host: this.$store.state.host,
      port: this.$store.state.port,
      changeUserfaceVisiable: false,
      limitid: this.$store.state.user.limitid,
      circleUrl: this.$store.state.user.avator,
      imageUrl: "",
      data: {
        userId: this.$store.state.user.userId
      }
    };
  },
  mounted() {},
  methods: {
    loadUserface() {
      var _this = this;
      this.getRequest(
        "/userface/get?userId=" + _this.$store.state.user.userId
      ).then(resp => {
        this.$store.state.user.avator = resp.data;
        this.circleUrl = this.$store.state.user.avator;
        this.calUserface();
      });
    },
    calUserface() {
      if (this.circleUrl.startsWith("/img")) {
        this.circleUrl = this.host + ":" + this.port + this.circleUrl;
      }
    },
    handleAvatarSuccess(res, file) {
      this.$message({
        showClose: true,
        message: "图片上传成功",
        type: "success"
      });
      this.loadUserface();
      this.changeUserfaceVisiable = false;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    submitUpload() {
      this.$refs.upload.submit();
    },
    avatarChange(file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    handleAvatarError(err) {
      console.log(err);
      this.$message({
        showClose: true,
        message: "图片上传失败",
        type: "error"
      });
    },
    handleCommand(cmd) {
      var _this = this;

      if (cmd == "changeUserface") {
        this.changeUserfaceVisiable = true;
        console.log(this.circleUrl);
      }
    },
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    }
  },
  name: "HelloWorld"
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.index-conyainer {
  height: 100%;
}
.el-header {
  background-color: #373d41;
  display: flex;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
}
.el-aside {
  background-color: #333744;
}
.el-menu {
  border-right: none;
}
.el-main {
  background-color: #eaedf1;
}
.el-menu-item-group {
  padding-left: 35px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
