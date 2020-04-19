<template>
  <div>
    <el-card>
      <el-form ref="form" :model="repair" label-width="80px">
        <el-form-item label="用户姓名" >
          <el-input v-model="repair.username"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="repair.telephone"></el-input>
        </el-form-item>
        <el-form-item label="联系地址">
          <el-input v-model="repair.hname"></el-input>
        </el-form-item>
        <el-form-item label="报修类型">
          <el-select v-model="repair.repairType" placeholder="请选择报修类型">
            <el-option label="设备报修" value="repairDevice"></el-option>
            <el-option label="网络报修" value="repairNetwork"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="报修内容">
          <el-input type="textarea" v-model="repair.repairInfo"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交报修单</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
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
      onSubmit() {
        console.log('submit!');
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