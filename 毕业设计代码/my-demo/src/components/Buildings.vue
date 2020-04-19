<template>
<div>
  <el-card>
    <el-row :gutter="20">
        <el-col :span=8>
          <el-input placeholder="请输入楼名" v-model="buildingInfo"  clearable @clear="getBuildList">
          <el-button slot="append" icon="el-icon-search" @click="getBuildList" ></el-button>
          </el-input>
        </el-col>
        <el-col :span=4>
          <el-button type="primary" @click="insertVisible = true">添加楼栋信息</el-button>
        </el-col>
      </el-row>
     <el-table
    :data="buildingList"
    style="width: 100%">
    <el-table-column
      label="楼号"
      prop="bid"
      width="120">
    </el-table-column>
    <el-table-column
      label="楼名"
      prop="bname"
      width="120">
    </el-table-column>
    <el-table-column
      label="创建时间"
      prop="createTime"
      width="120">
    </el-table-column>
    <el-table-column
      label="竣工时间"
      prop="endTime"
      width="120">
    </el-table-column>
    <el-table-column
      label="房间总数"
      prop="bHouseSum"
      width="120">
    </el-table-column>
    <el-table-column
      label="已住房间数"
      prop="bPersonHSum"
      width="120">
    </el-table-column>
    <el-table-column
      label="漏洞信息"
      prop="bug"
      width="120">
    </el-table-column>
    <el-table-column label="操作" width="120">
            <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="changeInfo(scope.row)">编辑
                </el-button>
                <el-button
                  size="mini"
                  type="danger"
                  @click="deleteBuildings(scope.row.bid)">删除
                </el-button>
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
      <el-form :model="addFormData" :rules="formRules" ref="addFormDataRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="楼号" prop="bid">
          <el-input v-model="addFormData.bid"></el-input>
        </el-form-item>
        <el-form-item label="楼名" prop="bname">
          <el-input v-model="addFormData.bname"></el-input>
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="addFormData.createTime"></el-input>
        </el-form-item>
         <el-form-item label="竣工时间" prop="endTime">
          <el-input v-model="addFormData.endTime"></el-input>
        </el-form-item>
        <el-form-item label="房间总数" prop="bHouseSum">
          <el-input v-model="addFormData.bHouseSum"></el-input>
        </el-form-item>
        <el-form-item label="已住房间数" prop="bPersonHSum">
          <el-input v-model="addFormData.bPersonHSum"></el-input>
        </el-form-item>
        <el-form-item label="漏洞信息" prop="bug">
          <el-input v-model="addFormData.bug"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="insertVisible = false">取 消</el-button>
        <el-button type="primary" @click="addBuildings(addFormData)">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="编辑"
      :visible.sync="updateVisible"
      width="50%">
      <el-form :model="updatebuild" :rules="formRules" ref="updatebuild" label-width="100px" class="demo-ruleForm">
        <el-form-item label="楼号" prop="bid">
          <el-input v-model="updatebuild.bid"></el-input>
        </el-form-item>
        <el-form-item label="楼名" prop="bname">
          <el-input v-model="updatebuild.bname"></el-input>
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="updatebuild.createTime"></el-input>
        </el-form-item>
         <el-form-item label="竣工时间" prop="endTime">
          <el-input v-model="updatebuild.endTime"></el-input>
        </el-form-item>
        <el-form-item label="房间总数" prop="bHouseSum">
          <el-input v-model="updatebuild.bHouseSum"></el-input>
        </el-form-item>
        <el-form-item label="已住房间数" prop="bPsersonHSum">
          <el-input v-model="updatebuild.bPersonHSum"></el-input>
        </el-form-item>
        <el-form-item label="漏洞信息" prop="bug">
          <el-input v-model="updatebuild.bug"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateBuildings(updatebuild)">确 定</el-button>
      </span>
    </el-dialog>
</div>  
</template>

<script>
export default {
  data () {
    return {
      buildings: [],
      buildingInfo: "",
      currentPage: 1,
      pagesize: 3,
      count:0,
      total:12,
      buildingList: [],
      insertVisible: false,
      updateVisible: false,
      addFormData: {
        bid: '',
        bname: '',
        createTime: '',
        endTime: '',
        bHouseSum:'',
        bPersonHSum:'',
        bug:'',
      },
      updatebuild: {
        bid: '',
        bname: '',
        createTime: '',
        endTime: '',
        bHouseSum:'',
        bPersonHSum:'',
        bug:'',
      },
      formRules:{
        bid: [
        { required: true, message: '请输入楼号', trigger: 'blur' },
        ],
        bname: [
        { required: true, message: '请输入楼名', trigger: 'blur' },
        ],
        createTime: [
        { required: true, message: '请输入创建时间', trigger: 'blur' },
        ],
        endTime: [
        { required: true, message: '请输入竣工时间', trigger: 'blur' },
        ],
        bHouseSum: [
        { required: true, message: '请输入房间总数', trigger: 'blur' },
        ],
        bPsersonHSum: [
        { required: true, message: '请输入已住房间数', trigger: 'blur' },
        ],
      },
    }
  },
  methods: {
    getBuildList(){
        var _this = this;
        this.getRequest("/getBuildingsPage?page=" + this.currentPage+ "&size=5" + "&buildingInfo=" + this.buildingInfo).then(resp =>{
          var data = resp.data;  
          _this.buildingList = data.build;
          _this.count = data.count;      
         /* 
         _this.users = data.userlist;
         console.log(resp.data);
        _this.count = data.count; 
        
         
        */
      })
    },
    addBuildings(addFormData){
        var _this = this;
        addFormData = JSON.stringify(addFormData);
        _this.postRequest("/addBuildings", {
            buildings: addFormData
        }).then(resp => {
          this.$message({
          message: "添加成功",
          type: "success"
        });
          var data = resp.data;
            if (data) {
          this.insertVisible = false;
          this.getBuildList();
        }
        })
      },
      changeInfo(building) {
        this.updateVisible = true;
        this.updatebuild.bid = building.bid;
        this.updatebuild.bname = building.bname;
        this.updatebuild.createTime = building.createTime;
        this.updatebuild.endTime = building.endTime;
        this.updatebuild.bHouseSum = building.bHouseSum;
        this.updatebuild.bPersonHSum = building.bPersonHSum;
        this.updatebuild.bug = building.bug;
        return;
      },

      updateBuildings(updatebuild) {
        var _this = this;
        updatebuild = JSON.stringify(updatebuild);
        _this.postRequest("/updateBuildings", {
          buildings: updatebuild
        }).then(resp => {
           _this.$message({
            message: "修改成功",
            type: "success"
          });
          var data = resp.data;
          if (data) {
            _this.updateVisible = false;
            _this.getBuildList();
          }
        })
      },
      deleteBuildings(bid) {
        var _this = this;
        _this.bid = bid;
        _this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    _this.deleteRequest("/deleteBuildings?bid=" + _this.bid).then(resp => {
                       _this.$message({
                          message: "删除成功",
                          type: "success"
                        });
                        var data = resp.data;
                        if (data) {
                         
                          _this.getBuildList();
                        }
                    })
                }).catch(() => {
                    _this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
      },
    handleSizeChange(newSize){
          this.size=newSize;
          this.getBuildList()
        },
        handleCurrentChange(newPage){
          this.currentPage=newPage;
          this.getBuildList()
        }
   },
   
   mounted() {
     this.getBuildList();
   }
    
 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>