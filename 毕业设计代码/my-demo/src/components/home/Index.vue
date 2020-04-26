<template>
  <el-container class="index-conyainer">
    <el-header>
      <div>
        <img src="" alt="" />
        <span>qw小区物业管理系统</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          router
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
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
            <el-submenu index="/system">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>楼层管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/manageAdmins">楼层管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </template>
        </el-menu>
      </el-aside>

      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  created() {},
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    }
  },
  name: "HelloWorld",
  data() {
    return {
      limitid: this.$store.state.user.limitid
    };
  }
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
  justify-content: space-between;
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
</style>
