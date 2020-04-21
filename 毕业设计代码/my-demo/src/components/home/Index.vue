<template>
    <el-container class="index-conyainer">
    <el-header>
      <div>
        <img src="" alt="">
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
        :unique-opened="true">
        <template v-if="limitid === '2'">
          <el-submenu index="">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>用户管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/user">
                <template slot="title">
                  <i class="el-icon-user"></i>
                  <span>住户信息</span>
                </template>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </template>
        <template v-if="limitid === '1'">
          <el-submenu index="/">
            <template slot="title">
              <i class="el-icon-s-home"></i>
              <span>房间管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/buildings">
                  <i class="el-icon-office-building"></i>
                  <span>楼栋信息</span>
              </el-menu-item>
              <el-menu-item index="/house">
                  <i class="el-icon-s-home"></i>
                  <span>房间信息</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </template>
        <el-submenu index="/service">
          <template slot="title">
            <i class="el-icon-platform-eleme"></i>
            <span>生活服务</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/pay">
              <i class="el-icon-tickets"></i>
              <span>缴费管理</span>
            </el-menu-item>
            <el-menu-item index="/repair">
              <i class="el-icon-setting"></i>
              <span>报修管理</span>
            </el-menu-item> 
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="/announcement">
          <template slot="title">
            <i class="el-icon-message"></i>
            <span>消息管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/lostandfound"><i class="el-icon-chat-dot-round"></i>失物招领</el-menu-item>
            <el-menu-item index="/novelty"><i class="el-icon-chat-dot-square"></i>新鲜事</el-menu-item>
            <el-menu-item index="/announcement"><i class="el-icon-chat-line-square"></i>其他公告</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <template v-if="limitid === '1'">
        <el-submenu index="/system">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>系统管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/permission">用户权限管理</el-menu-item>
            <el-menu-item index="/device">房间设备管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        </template>
      </el-menu>
    </el-aside>

      <el-main>
          <router-view/>
      </el-main>
    </el-container>
</el-container>
</template>

<script>
export default {
  created() {
    
  },
  methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      logout() {
        window.sessionStorage.clear()
        this.$router.push('/login')
      }
    },
  name: 'HelloWorld',
  data () {
    return {
      limitid:this.$store.state.user.limitid,
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.index-conyainer{
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
.el-menu{
    border-right: none;
  }
.el-main {
  background-color:#EAEDF1;
}
.el-menu-item-group{
  padding-left: 35px;
}
</style>
