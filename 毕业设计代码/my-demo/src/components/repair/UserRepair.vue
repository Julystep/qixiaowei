<template>
  <div>
    <el-card>
      <el-button type="primary" icon="el-icon-plus" @click="insertVisible = true"></el-button>
      <el-button type="primary" icon="el-icon-edit" @click="changeInfo(updateData)"></el-button>
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
    <el-dialog
      title="添加"
      :visible.sync="insertVisible"
      width="50%">
      <el-form :model="repairData" :rules="formRules" ref="repairDataRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="repairData.username"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model="repairData.phone"></el-input>
        </el-form-item>
        <el-form-item label="联系地址" prop="houseid">
          <el-input v-model="repairData.houseid"></el-input>
        </el-form-item>
         <el-form-item label="报修设备" prop="devicename">
          <el-input v-model="repairData.devicename"></el-input>
        </el-form-item>
        <el-form-item label="报修信息" prop="repairInfo">
          <el-input v-model="repairData.repairInfo"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="insertVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRepair(repairData)">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="编辑"
      :visible.sync="updateVisible"
      width="50%">
      <el-form :model="updateData" :rules="formRules" ref="updatehouse" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="updateData.username"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model="updateData.phone"></el-input>
        </el-form-item>
        <el-form-item label="联系地址" prop="houseid">
          <el-input v-model="updateData.houseid"></el-input>
        </el-form-item>
         <el-form-item label="报修设备" prop="devicename">
          <el-input v-model="updateData.devicename"></el-input>
        </el-form-item>
        <el-form-item label="报修信息" prop="repairInfo">
          <el-input v-model="updateData.repairInfo"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateRepair(updateData)">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data () {
    return {
      limitid:this.$store.state.user.limitid,
      insertVisible: false,
      updateVisible: false,
      repairlist:[],
      repairInfo:'',
      currentPage: 1,
      pagesize: 5,
      count:0,
      total:12,
      repairData:{
        username:'',
        phone:'',
        houseid:'',
        devicename:'',
        repairInfo:'',
      },
      updateData:{
        username:'',
        phone:'',
        houseid:'',
        devicename:'',
        repairInfo:'',
      },
      formRules:{
        username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        devicename: [
        { required: true, message: '请输入设备名', trigger: 'blur' },
        ],
        phone: [
        { required: true, message: '请输入联系方式', trigger: 'blur' },
        ],
        houseid: [
        { required: true, message: '请输入房间号', trigger: 'blur' },
        ],
        repairInfo: [
        { required: true, message: '请输入报修信息', trigger: 'blur' },
        ],
      },
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
    },
    addRepair(repairData){
        var _this = this;
        repairData = JSON.stringify(repairData);
        _this.postRequest("/addRepair", {
            repair: repairData
        }).then(resp => {
          this.$message({
          message: "添加成功",
          type: "success"
        });
          var data = resp.data;
            if (data) {
          this.insertVisible = false;
        }
        }).catch(() => {
                    _this.$message({
                        type: 'info',
                        message: '取消添加'
                    });
                  });
      },
      changeInfo(repair) {
        this.updateVisible = true;
        this.updateData.username = repair.username;
        this.updateData.phone = repair.phone;
        this.updateData.houseid = repair.houseid;
        this.updateData.devicename = repair.devicename;
        this.updateData.repairInfo = repair.repairInfo;
        return;
      },

      updateRepair(updateData) {
        var _this = this;
        updateData = JSON.stringify(updateData);
        _this.postRequest("/updateRepair", {
          repair: updateData
        }).then(resp => {
           _this.$message({
            message: "修改成功",
            type: "success"
          });
          var data = resp.data;
          if (data) {
            _this.updateVisible = false;
            
          }
        }).catch(() => {
                    _this.$message({
                        type: 'info',
                        message: '取消修改'
                    });
                  });
      },
    },
    mounted() {
     this.getRepairList();
   }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>