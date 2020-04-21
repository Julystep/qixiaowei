<template>
<div>
  <el-card>
    <el-row :gutter="20">
        <el-col :span=8>
          <el-input placeholder="请输入房间名" v-model="houseInfo"  clearable @clear="getHouseList">
          <el-button slot="append" icon="el-icon-search" @click="getHouseList" ></el-button>
          </el-input>
        </el-col>
        <el-col :span=4>
          <el-button type="primary" @click="insertVisible = true">添加房间</el-button>
        </el-col>
      </el-row>
     <el-table
    :data="houselist">
    <el-table-column
      label="房间号"
      prop="hid"
      width="180">
    </el-table-column>
    <el-table-column
      label="房间名"
      prop="hname"
      width="180">
    </el-table-column>
    <el-table-column
      label="面积"
      prop="hArea"
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
    <el-table-column
      label="楼号"
      prop="bid"
      width="180">
    </el-table-column>
    <el-table-column label="操作" width="180">
            <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="changeInfo(scope.row)">编辑
                </el-button>
                <el-button
                  size="mini"
                  type="danger"
                  @click="deleteHouse(scope.row.hid)">删除
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
        <el-form-item label="房间号" prop="hid">
          <el-input v-model="addFormData.hid"></el-input>
        </el-form-item>
        <el-form-item label="房间名" prop="hname">
          <el-input v-model="addFormData.hname"></el-input>
        </el-form-item>
        <el-form-item label="面积" prop="hArea">
          <el-input v-model="addFormData.hArea"></el-input>
        </el-form-item>
         <el-form-item label="状态" prop="status">
          <el-input v-model="addFormData.status"></el-input>
        </el-form-item>
        <el-form-item label="楼号" prop="bid">
          <el-input v-model="addFormData.bid"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="insertVisible = false">取 消</el-button>
        <el-button type="primary" @click="addHouse(addFormData)">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="编辑"
      :visible.sync="updateVisible"
      width="50%">
      <el-form :model="updatehouse" :rules="formRules" ref="updatehouse" label-width="100px" class="demo-ruleForm">
        <el-form-item label="房间号" prop="hid">
          <el-input v-model="updatehouse.hid"></el-input>
        </el-form-item>
        <el-form-item label="房间名" prop="hname">
          <el-input v-model="updatehouse.hname"></el-input>
        </el-form-item>
        <el-form-item label="面积" prop="hArea">
          <el-input v-model="updatehouse.hArea"></el-input>
        </el-form-item>
         <el-form-item label="状态" prop="status">
          <el-input v-model="updatehouse.status"></el-input>
        </el-form-item>
        <el-form-item label="楼号" prop="bid">
          <el-input v-model="updatehouse.bid"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateHouse(updatehouse)">确 定</el-button>
      </span>
    </el-dialog>
</div>  
</template>

<script>
export default {
  data () {
    return {
      houselist: [],
      houseInfo: "",
      currentPage: 1,
      pagesize: 3,
      count:0,
      total:12,
      insertVisible: false,
      updateVisible: false,
      addFormData: {
        hid: '',
        hname: '',
        hArea: '',
        status: '',
        bid:'',
      },
      updatehouse: {
        hid: '',
        hname: '',
        hArea: '',
        status: '',
        bid:'',
      },
      formRules:{
        hid: [
        { required: true, message: '请输入房间号', trigger: 'blur' },
        ],
        hname: [
        { required: true, message: '请输入房间名', trigger: 'blur' },
        ],
        hArea: [
        { required: true, message: '请输入面积', trigger: 'blur' },
        ],
        bid: [
        { required: true, message: '请输入楼号', trigger: 'blur' },
        ],
      },
    }
  },
  methods: {
    getHouseList(){
        var _this = this;
        this.getRequest("/getHousePage?page=" + this.currentPage+ "&size=5" + "&houseInfo=" + this.houseInfo).then(resp =>{
          var data = resp.data;  
          _this.houselist = data.houses;
          _this.count = data.count;      
         /* 
         _this.users = data.userlist;
         console.log(resp.data);
        _this.count = data.count; 
         
        */
      })
    },
    addHouse(addFormData){
        var _this = this;
        addFormData = JSON.stringify(addFormData);
        _this.postRequest("/addHouse", {
            house: addFormData
        }).then(resp => {
          this.$message({
          message: "添加成功",
          type: "success"
        });
          var data = resp.data;
            if (data) {
          this.insertVisible = false;
          this.getHouseList();
        }
        }).catch(() => {
                    _this.$message({
                        type: 'info',
                        message: '取消添加'
                    });
                  });
      },
      changeInfo(house) {
        this.updateVisible = true;
        this.updatehouse.hid = house.hid;
        this.updatehouse.hname = house.hname;
        this.updatehouse.hArea = house.hArea;
        this.updatehouse.status = house.status;
        this.updatehouse.bid = house.bid;
        return;
      },

      updateHouse(updatehouse) {
        var _this = this;
        updatehouse = JSON.stringify(updatehouse);
        _this.postRequest("/updateHouse", {
          house: updatehouse
        }).then(resp => {
           _this.$message({
            message: "修改成功",
            type: "success"
          });
          var data = resp.data;
          if (data) {
            _this.updateVisible = false;
            if (data) {
               _this.getHouseList();
              }
          }
        }).catch(() => {
                    _this.$message({
                        type: 'info',
                        message: '取消修改'
                    });
                  });
      },
      deleteHouse(hid) {
        var _this = this;
        _this.hid = hid;
        _this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    _this.deleteRequest("/deleteHouse?hid=" + _this.hid).then(resp => {
                       _this.$message({
                          message: "删除成功",
                          type: "success"
                        });
                        var data = resp.data;
                        if (data) {
                          _this.getHouseList();
                        }
                    })
                }).catch(() => {
                    _this.$message({
                        type: 'info',
                        message: '取消删除'
                    });
                });
      },
    handleSizeChange(newSize){
          this.size=newSize;
          this.getHouseList()
        },
        handleCurrentChange(newPage){
          this.currentPage=newPage;
          this.getHouseList()
        }
   },
   
   mounted() {
     this.getHouseList();
   }
    
 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>