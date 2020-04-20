<template>
  <div>
    <el-tabs  @tab-click="handleClick">
      <el-tab-pane label="用户报修管理" ><UserRepair ref="userRepair"></UserRepair></el-tab-pane>
      <el-tab-pane label="查看报修状态" ><RepairManage ref="repairManage"></RepairManage></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import UserRepair from './UserRepair'
import RepairManage from './RepairManage'
export default {
  components: {
    UserRepair,
    RepairManage,
  },
  data () {
    return {
      limitid:this.$store.state.user.limitid,
      repair:{
        username:'',
        telephone:'',
        hname:'',
        repairType:'',
        repairInfo:'',
      }
    }
  },
  methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-card{
  width: 50%;
}
</style>