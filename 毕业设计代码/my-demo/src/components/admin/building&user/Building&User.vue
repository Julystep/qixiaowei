<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          v-model="houseInfo"
          clearable
          @clear="getBuildingOfUser"
          class="input-with-select"
          size="mini"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getBuildingOfUser()"
          ></el-button>
        </el-input>
      </el-col>
      <el-col :span="2" :offset="16">
        <el-button
          type="primary"
          size="mini"
          @click="insertHouseVisible = true"
          style="width: 100%"
          >添加房间</el-button
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
      <el-table-column label="房间号" prop="hid" width="200"></el-table-column>
      <el-table-column label="房间名" prop="hname" width="200"></el-table-column>
      <el-table-column label="面积" prop="hArea" width="200"></el-table-column>
      <el-table-column label="业主" prop="userName" width="200"></el-table-column>
      <el-table-column label="邮箱" prop="email" width="230"> </el-table-column>
      <el-table-column label="电话" prop="telephone" width="230"></el-table-column>
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
              class="el-icon-edit"
            ></el-button>
          </el-tooltip>

          <el-tooltip
            class="item"
            effect="dark"
            content="解除与业主的关联"
            placement="top"
          >
            <el-button
              type="warning"
              size="small"
              circle
              @click="dismissWithUser(scope.row)"
              class="el-icon-circle-close"
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
        :total="count"
        :currentPage="currentPage"
        @current-change="handleCurrentChange"
      >
      </el-pagination>

            <el-dialog
              title="添加房间信息"
              :visible.sync="insertHouseVisible"
              width="50%"
            >
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
                <el-form-item label="楼号" prop="bid">
                  <el-input v-model="addhouse.bid"></el-input>
                </el-form-item>
                <el-form-item label="业主" prop="userName">
                  <el-select
                    v-model="addhouse.userName"
                    placeholder="请选择"
                    style="width: 100%"
                  >
                    <el-option
                      v-for="item in userNameOptions"
                      :key="item.userName"
                      :label="item.userName"
                      :value="item.userName"
                    >
                    </el-option>
                  </el-select>
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
            <el-dialog
              title="编辑房间信息"
              :visible.sync="updateHouseVisible"
              width="50%"
            >
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
                  <el-select
                    v-model="updatehouse.userName"
                    placeholder="请选择"
                    style="width: 100%"
                  >
                    <el-option
                      v-for="item in userNameOptions"
                      :key="item.userId"
                      :label="item.userName"
                      :value="item.userId"
                    >
                    </el-option>
                  </el-select>
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      houseInfo:"",
      building: {},
      houses: [],
      currentPage: 1,
      count: 0,
      currentPageHouses: 1,
      housesCount: 0,
      userNameOptions:[],
      insertHouseVisible: false,
      updateHouseVisible: false,
      addhouse: {
        hid: "",
        hname: "",
        hArea: "",
        bid:"",
        userName: "",
        email: "",
        telephone: ""
      },
      updatehouse: {
        id: "",
        hid: "",
        hname: "",
        hArea: "",
        userName: "",
        email: "",
        telephone: ""
      },
      formRules: {
        hid: [
          { required: true, message: "请输入房间号", trigger: "blur" },
          {
            pattern: /^([1-9]{1}|[1-9]{1}[0-9]{1})\-[1-6]{1}\-([1-6]{1}[0][1-2]{1})$/,
            message: "请输入正确的房间号"
          }
        ],
        hname: [
          { required: true, message: "请输入房间名", trigger: "blur" },
          { pattern: /^[\u0391-\uFFE5]{9,11}$/, message: "请输入正确的房间名" }
        ],
        hArea: [
          { required: true, message: "请输入房间面积", trigger: "blur" },
          { pattern: /^[1-9]{1}[0-9]{1,4}$/, message: "请输入合理的房间面积" }
        ],
        /*userName: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { pattern: /^[\u0391-\uFFE5]{2,4}$/, message: "请输入合理的用户名" }
        ],*/
        telephone: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          {
            pattern: /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/,
            message: "请输入正确的手机号"
          }
        ],
        email: [
          {
            pattern: /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,3})$/,
            message: "请输入正确的邮箱格式"
          }
        ]
      }
    };
  },
  mounted() {
    this.getBuildingOfUser();
    this.getUserNameOption();
  },
  methods: {
    getBuildingOfUser() {
      /**
      获取管理员管理的楼层和楼层中具体的房间数量
       */
      var _this = this;
      this.getRequest(
        "/admin/getBuildingAndHouses?userId=" +
          _this.$store.state.user.userId +
          "&page=" +
          _this.currentPage +
          "&size=" +
          10 +
          "&houseInfo="+
          _this.houseInfo
      ).then(resp => {
        console.log(resp.data);
        _this.building = resp.data.building;
        _this.houses = resp.data.houses;
        _this.count = resp.data.count;
      });
    },
    getUserNameOption(row){
      var _this = this;
      this.getRequest("/admin/getuserswithouthouses").then(resp => {
        _this.userNameOptions = resp.data;
        console.log(_this.userNameOptions);
      });
    },
    dismissWithUser(row) {
      var _this = this;
      this.$confirm("确定要解除关联信息吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          _this
            .postRequest("/admin/dismissWithUser", {
              id: row.id
            })
            .then(resp => {
              this.$message({
                type: "sucess",
                message: "解除关联成功"
              });
            });
        })
        .catch(() => {});
    },
    changeHouseInfo(house) {
      this.rowhouse = house;
      this.updateHouseVisible = true;
      this.updatehouse.hid = house.hid;
      this.updatehouse.hname = house.hname;
      this.updatehouse.hArea = house.hArea;
      this.updatehouse.userName = house.userName;
      this.updatehouse.email = house.email;
      this.updatehouse.telephone = house.telephone;
      return;
    },
    updateHouse(updatehouse) {
      var _this = this;
      updatehouse.id = this.rowhouse.id;
      updatehouse = JSON.stringify(updatehouse);
      this.$refs.updatehouseRef.validate(valid => {
        if (valid) {
          _this
            .postRequest("/admin/updateHouse", {
              house: updatehouse
            })
            .then(resp => {
              _this.$message({
                message: "修改成功",
                type: "success"
              });
              var data = resp.data;
              _this.getBuildingOfUser();
              if (data) {
                _this.updateHouseVisible = false;
                _this.getUserNameOption();
                
              }
            });
        } else {
          _this.$message({
            type: "error",
            message: "表单未按照规则填写"
          });
        }
      });
    },
    deleteHouseInfo(hid) {
      var _this = this;
      _this.hid = hid;
      this.$confirm("确定要删除该管理员信息吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.deleteRequest("/admin/deleteHouse?hid=" + _this.hid).then(() => {
          _this.getBuildingOfUser();
        });
      });
    },
    insertHouse(addhouse) {
      var _this = this;
      addhouse = JSON.stringify(addhouse);
      _this.$refs.addhouseRef.validate(valid => {
        if (valid) {
          _this
            .postRequest("/admin/addHouse", {
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
                _this.getBuildingOfUser();
                _this.getUserNameOption();
              }
            });
        } else {
          _this.$message({
            type: "error",
            message: "表单未按照规则填写"
          });
        }
      });
    },
    handleCurrentChange(newPage){
      this.currentPage=newPage;
      this.getBuildingOfUser();
    }
  }
};
</script>
