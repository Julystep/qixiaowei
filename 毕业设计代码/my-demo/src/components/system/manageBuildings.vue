<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          v-model="buildingInfo"
          class="input-with-select"
          size="mini"
        >
          <el-button slot="append" icon="el-icon-search" @click="getAllBuildings()"></el-button>
        </el-input>
      </el-col>
      <el-col :span="2" :offset="16">
        <el-button type="primary" size="mini" @click="insertBuildingVisible = true" style="width: 100%" >添加楼层</el-button
        >
      </el-col>
    </el-row>

    <el-table
      ref="table"
      :data="buildings"
      border
      size="mini"
      style="width: 100%"
      @row-click="toogleExpand"
      highlight-current-row
    >
      <el-table-column label="楼层 ID" prop="bid" width="530">
      </el-table-column>
      <el-table-column label="楼层名称" prop="bname" width="580">
      </el-table-column>
      <el-table-column type="expand" width="1" style="color: white">
        <template slot-scope="prop">
          <el-row style="margin-bottom: 10px">
            <el-col :span="6">
              <el-input
                placeholder="请输入内容"
                v-model="houseInfo"
                class="input-with-select"
                size="mini"
              >
                <el-button slot="append" icon="el-icon-search" @click="currentChangeHouses()"></el-button>
              </el-input>
            </el-col>
            <el-col :span="2" :offset="16">
              <el-button type="primary" size="mini" @click="insertHouseVisible = true" style="width: 100%">添加房间</el-button
              >
            </el-col>
          </el-row>

          <el-table
            :data="houses"
            border
            size="mini"
            row-key="hid"
            highlight-current-row
          >
            <el-table-column label="房间号" prop="hid" width="200">
            </el-table-column>
            <el-table-column label="房间名" prop="hname" width="200">
            </el-table-column>
            <el-table-column label="面积" prop="hArea" width="200">
            </el-table-column>
            <el-table-column label="业主" prop="userName" width="200">
            </el-table-column>
            <el-table-column label="邮箱" prop="email" width="230">
            </el-table-column>
            <el-table-column label="电话" prop="telephone" width="230">
            </el-table-column>
            <el-table-column label="操作" width="330">
              <template slot-scope="scope">
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="修改房间信息"
                  placement="top"
                >
                  <el-button
                    type="primary"
                    size="small"
                    circle
                    @click="changeHouseInfo(scope.row)"
                    class="el-icon-info"
                  ></el-button>
                </el-tooltip>

                <el-tooltip
                  class="item"
                  effect="dark"
                  content="删除房间信息"
                  placement="top"
                >
                  <el-button
                    type="danger"
                    size="small"
                    circle
                    @click="deleteHouseInfo(scope.row.hid)"
                    class="el-icon-delete"
                  ></el-button>
                </el-tooltip>
              </template>
            </el-table-column>
          </el-table>
          <div style="margin-top: 5px; margin-left:40%">
            <el-pagination
              background
              layout="prev, pager, next"
              :total="housesCount"
              :currentPage="currentPageHouses"
              @current-change="currentChangeHouses"
            >
            </el-pagination>

            <el-dialog title="添加房间信息" :visible.sync="insertHouseVisible" width="50%">
              <el-form
                :model="addhouse"
                :rules="formRules"
                ref="addhouseRef"
                label-width="100px"
                class="demo-ruleForm"
              >
                <el-form-item label="房间号" prop="hid">
                  <el-input v-model="addhouse.hid"></el-input>
                </el-form-item>
                <el-form-item label="房间名" prop="hname">
                  <el-input v-model="addhouse.hname"></el-input>
                </el-form-item>
                <el-form-item label="面积" prop="hArea">
                  <el-input v-model="addhouse.hArea"></el-input>
                </el-form-item>
                <el-form-item label="业主" prop="userName">
                  <el-input v-model="addhouse.userName"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                  <el-input v-model="addhouse.email"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="telephone">
                  <el-input v-model="addhouse.telephone"></el-input>
                </el-form-item>
              </el-form>
              <span slot="footer" class="dialog-footer">
                <el-button @click="insertHouseVisible = false">取 消</el-button>
                <el-button type="primary" @click="insertHouse(addhouse)"
                  >确 定</el-button
                >
              </span>
            </el-dialog>
            <el-dialog title="编辑房间信息" :visible.sync="updateHouseVisible" width="50%">
              <el-form
                :model="updatehouse"
                :rules="formRules"
                ref="updatehouseRef"
                label-width="100px"
                class="demo-ruleForm"
              >
                <el-form-item label="房间号" prop="hid">
                  <el-input v-model="updatehouse.hid"></el-input>
                </el-form-item>
                <el-form-item label="房间名" prop="hname">
                  <el-input v-model="updatehouse.hname"></el-input>
                </el-form-item>
                <el-form-item label="面积" prop="hArea">
                  <el-input v-model="updatehouse.hArea"></el-input>
                </el-form-item>
                <el-form-item label="业主" prop="userName">
                  <el-input v-model="updatehouse.userName"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                  <el-input v-model="updatehouse.email"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="telephone">
                  <el-input v-model="updatehouse.telephone"></el-input>
                </el-form-item>
              </el-form>
              <span slot="footer" class="dialog-footer">
                <el-button @click="updateHouseVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateHouse(updatehouse)"
                  >确 定</el-button
                >
              </span>
            </el-dialog>

          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="580">
        <template slot-scope="scope">
          <el-tooltip
            class="item"
            effect="dark"
            content="修改楼栋信息"
            placement="top"
          >
            <el-button
              type="primary"
              size="small"
              circle
              @click="changeBuildingInfo(scope.row)"
              class="el-icon-info"
            ></el-button>
          </el-tooltip>

          <el-tooltip
            class="item"
            effect="dark"
            content="删除楼栋信息"
            placement="top"
          >
            <el-button
              type="danger"
              size="small"
              circle
              @click="deleteBuildinginfo(scope.row.bid)"
              class="el-icon-delete"
            ></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 5px">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="buildingCounts"
        :currentPage="currentPageBuildings"
        @current-change="currentChangeBuildings"
      >
      </el-pagination>
    </div>
    <el-dialog title="添加楼栋信息" :visible.sync="insertBuildingVisible" width="50%">
      <el-form
        :model="addbuilding"
        :rules="formRules"
        ref="addbuildingRef"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="楼号" prop="bid">
          <el-input v-model="addbuilding.bid"></el-input>
        </el-form-item>
        <el-form-item label="楼名" prop="bname">
          <el-input v-model="addbuilding.bname"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="insertBuildingVisible = false">取 消</el-button>
        <el-button type="primary" @click="insertBuilding(addbuilding)"
          >确 定</el-button
        >
      </span>
    </el-dialog>
    <el-dialog title="编辑楼栋信息" :visible.sync="updateBuildingVisible" width="50%">
      <el-form
        :model="updatebuilding"
        :rules="formRules"
        ref="updatebuildingRef"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="楼号" prop="bid">
          <el-input v-model="updatebuilding.bid"></el-input>
        </el-form-item>
        <el-form-item label="楼名" prop="bname">
          <el-input v-model="updatebuilding.bname"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateBuildingVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateBuilding(updatebuilding)"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      buildings: [],
      buildingInfo:"",
      buildingCounts: 0,
      currentPageBuildings: 1,
      currentPageHouses: 1,
      housesCount: 0,
      houses: [],
      houseInfo:"",
      insertBuildingVisible: false,
      insertHouseVisible: false,
      updateBuildingVisible: false,
      updateHouseVisible:false,
      addbuilding:{
        bid:'',
        bname:''
      },
      updatebuilding:{
        bid:'',
        bname:''
      },
      addhouse:{
        hid:'',
        hname:'',
        hArea:'',
        userName:'',
        email:'',
        telephone:''
      },
      updatehouse:{
        hid:'',
        hname:'',
        hArea:'',
        userName:'',
        email:'',
        telephone:''
      },
      formRules: {
        bid: [
          { required: true, message: "请输入楼号", trigger: "blur" }
        ],
        bname: [{ required: true, message: "请输入楼名", trigger: "blur" }],
        hid: [{ required: true, message: "请输入房间号", trigger: "blur" }],
        hname: [{ required: true, message: "请输入房间名", trigger: "blur" }],
        hArea: [{ required: true, message: "请输入房间面积", trigger: "blur" }],
      }
    };
  },
  mounted() {
    this.getAllBuildings();
  },
  methods: {
    getAllBuildings() {
      var _this = this;
      this.postRequest("/getallbuildings", {
        houseId: -1,
        page: _this.currentPageBuildings,
        size: 10,
        buildingInfo: _this.buildingInfo
      }).then(resp => {
        _this.buildings = resp.data.buildings;
        _this.buildingCounts = resp.data.buildingCounts;
      });
    },
    changeBuildingInfo(building){
      this.updateBuildingVisible = true;
      this.updatebuilding.bid = building.bid;
      this.updatebuilding.bname = building.bname;
      return;
    },
    updateBuilding(updatebuilding){
      var _this = this;
      updatebuilding = JSON.stringify(updatebuilding);
      this.$refs.updatebuildingRef.validate(valid => {
        if (valid) {
          _this
            .postRequest("/root/updateBuilding", {
              buildings: updatebuilding
            })
            .then(resp => {
              _this.$message({
                message: "修改成功",
                type: "success"
              });
              var data = resp.data;
              if (data) {
                _this.updateBuildingVisible = false;
                _this.getAllBuildings();
              }
            });
        } else {
          _this.$message({
            type: "error",
            message: "表单未按照规则填写"
          })
        }
      });
    },
    deleteBuildinginfo(bid){
      var _this = this;
      _this.bid = bid;
      _this.$confirm("确定要删除该楼栋信息吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        _this.deleteRequest("/root/deleteBuilding?bid=" + _this.bid).then(
          () => {
            _this.getAllBuildings();
          }
        );
      });
    },
    insertBuilding(addbuilding){
      var _this = this;
      addbuilding = JSON.stringify(addbuilding);
        _this.$refs.addbuildingRef.validate(valid => {
        if (valid) {
           _this
        .postRequest("/root/addBuilding", {
          buildings: addbuilding
        })
        .then(resp => {
          this.$message({
            message: "添加成功",
            type: "success"
          });
          var data = resp.data;
          if (data) {
            this.insertBuildingVisible = false;
            this.getAllBuildings();
          }
        });
        }else {
          _this.$message({
            type: "error",
            message: "表单未按照规则填写"
          })
        }
        });
    },
    changeHouseInfo(house){
      this.updateHouseVisible = true;
      this.updatehouse.hid = house.hid;
      this.updatehouse.hname = house.hname;
      this.updatehouse.hArea = house.hArea;
      this.updatehouse.userName = house.userName;
      this.updatehouse.email = house.email;
      this.updatehouse.telephone = house.telephone;
      return;
    },
    updateHouse(updatehouse){
      var _this = this;
      updatehouse = JSON.stringify(updatehouse);
      this.$refs.updatehouseRef.validate(valid => {
        if (valid) {
          _this
            .postRequest("/root/updateHouse", {
              house: updatehouse
            })
            .then(resp => {
              _this.$message({
                message: "修改成功",
                type: "success"
              });
              var data = resp.data;
              if (data) {
                _this.updateHouseVisible = false;
              }
            });
        } else {
          _this.$message({
            type: "error",
            message: "表单未按照规则填写"
          })
        }
      });
    },
    deleteHouseInfo(hid){
      var _this = this;
      _this.hid = hid;
      this.$confirm("确定要删除该管理员信息吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.deleteRequest("/root/deleteHouse?hid=" + _this.hid).then(
          () => {
          }
        );
      });
    },
    insertHouse(addhouse){
      var _this = this;
      addhouse = JSON.stringify(addhouse);
        _this.$refs.addhouseRef.validate(valid => {
        if (valid) {
           _this
        .postRequest("/root/addHouse", {
          house: addhouse
        })
        .then(resp => {
          this.$message({
            message: "添加成功",
            type: "success"
          });
          var data = resp.data;
          if (data) {
            this.insertHouseVisible = false;
          }
        });
        }else {
          _this.$message({
            type: "error",
            message: "表单未按照规则填写"
          })
        }
        });
    },
    currentChangeBuildings(currentPageBuildings) {
      this.currentPageBuildings = currentPageBuildings;
      this.getAllBuildings();
    },
    currentChangeHouses(currentPageHouses) {
      this.currentPageHouses = currentPageHouses;
      this.postRequest("/gethousesbybuildingid", {
        bid: row.bid,
        page: _this.currentPageHouses,
        size: 10
      }).then(resp => {
        _this.houses = resp.data.houses;
        _this.housesCount = resp.data.housesCount;
      });
    },
    toogleExpand(row, column, event) {
      let $table = this.$refs.table;
      var _this = this;
      this.postRequest("/gethousesbybuildingid", {
        bid: row.bid,
        page: _this.currentPageHouses,
        size: 10
      }).then(resp => {
        console.log(resp);
        _this.houses = resp.data.houses;
        _this.housesCount = resp.data.housesCount;
        _this.buildings.map(item => {
          if (row.bid != item.bid) {
            $table.toggleRowExpansion(item, false);
          }
        });
        $table.toggleRowExpansion(row);
      });
    }
  }
};
</script>
