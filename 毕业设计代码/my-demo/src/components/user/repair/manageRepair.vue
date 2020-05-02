<template>
  <div>
    <el-card>
    <el-row :gutter="20">
        <el-col :span=8>
          <el-input placeholder="请输入报修编号" v-model="repairInfo"  clearable @clear="getRepairList">
          <el-button slot="append" icon="el-icon-search" @click="getRepairList" ></el-button>
          </el-input>
        </el-col>
      </el-row>
     <el-table
    :data="repairlist">
    <el-table-column
      label="报修编号"
      prop="repairId"
      width="180">
    </el-table-column>
    <el-table-column
      label="用户名"
      prop="username"
      width="180">
    </el-table-column>
    <el-table-column
      label="房间号"
      prop="houseid"
      width="180">
    </el-table-column>
    <el-table-column
      label="设备名称"
      prop="devicename"
      width="180">
    </el-table-column>
    <el-table-column
      label="报修信息"
      prop="repairInfo"
      width="180">
    </el-table-column>
    <el-table-column
      label="联系方式"
      prop="phone"
      width="180">
    </el-table-column>
    <el-table-column
      label="状态"
      prop="status"
      width="180">
      <template slot-scope="scope">
        <el-switch
          v-model="scope.row.status">
        </el-switch>
      </template>
    </el-table-column>
  </el-table>

  <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="pagesize"
      layout="total, prev, pager, next, jumper"
      :total="total">
  </el-pagination> 

  </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      repairlist:[],
      repairInfo:'',
      currentPage: 1,
      pagesize: 3,
      count:0,
      total:12,
    }
  },
  methods: {
    getRepairList(){
      var _this = this;
      this.getRequest("/getRepairPage?page=" + this.currentPage+ "&size=5" + "&repairInfo=" + this.repairInfo).then(resp =>{
        var data = resp.data;  
        _this.repairlist = data.repair;
        _this.count = data.count;      
      })
    },
    handleSizeChange(newSize){
      this.size=newSize;
      this.getRepairList()
    },
    handleCurrentChange(newPage){
      this.currentPage=newPage;
      this.getRepairList()
    }
  },
   
   mounted() {
     this.getRepairList();
   }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>