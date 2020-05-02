<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          v-model="info"
          clearable
          @clear = "getAllInformations"
          class="input-with-select"
          size="mini"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getAllInformations()"
          ></el-button>
        </el-input>
      </el-col>
    </el-row>

    
    <template v-for="(item, index) in information">
      <el-card>
        <el-row>
          <el-col :span="8"
            ><div class="grid-head bg-purple-dark">
              {{ item.head }}
            </div></el-col
          >
            <el-col :span="4" :offset="12" >
              <el-button
              type="primary"
              size="small"
              circle
              @click="changeInfo(index)"
              class="el-icon-edit"
              >
              </el-button>
              <el-button
              type="danger"
              size="small"
              circle
              @click="deleteInfo(index.id)"
              class="el-icon-delete"
              >
              </el-button>
            </el-col>
        </el-row>
        <el-row>
          <el-col :span="24"
            ><div class="grid-content bg-purple">
              {{ item.content }}
            </div></el-col
          >
        </el-row>
        <el-row :gutter="200">
          <el-col :span="8"
            ><div class="grid-bottom bg-purple">
              {{ item.userId }}
            </div></el-col
          >
          <el-col :span="16"
            ><div class="grid-bottom bg-purple">
              {{ item.infotime }}
            </div>
         </el-col>
        </el-row>
      </el-card>
    </template> 

    <el-dialog
        title="编辑房间信息"
        :visible.sync="updateInfoVisible"
        width="50%"
      >
        <el-form
          :model="updateinfo"
          :rules="formRules"
          ref="updateinfoRef"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="标题" prop="head">
            <el-input v-model="updateinfo.head"></el-input>
          </el-form-item>
          <el-form-item label="内容" prop="content">
            <el-input v-model="updateinfo.content"></el-input>
          </el-form-item>
          <el-form-item label="类型" prop="type">
            <el-select
              v-model="updateinfo.type"
              placeholder="请选择"
              style="width: 100%"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="updateInfoVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateInfo(updateinfo)"
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
            information: [],
            info: "",
            updateInfoVisible: false,
            updateinfo: {
                head: "",
                content: "",
                type: "",
            },
            options: [{
                value: '选项1',
                label: '失物招领'
                }, {
                value: '选项2',
                label: '业主通知'
                }, {
                value: '选项3',
                label: '新鲜事'
                }],
            value: '',
            formRules: {},
        }
    },
    mounted() {
        this.getAllInformations();
    },
    methods: {
        getAllInformations() {
            /* 获得所有的二级管理员 */
            var _this = this;
            this.getRequest(
                "/admin/getallinformations?info="
                + _this.info 
            ).then(resp => {
                _this.information = resp.data.information;
                console.log(_this.information);
            });
        },
        changeInfo(informations){
            this.updateInfoVisible = true;
            this.head = informations.head;
            this.content = informations.content;
            this.type = informations.type;
            return;
        },
        updateInfo(updateinfo) {
            var _this = this;
            updateinfo = JSON.stringify(updateinfo);
            this.$refs.updateinfoRef.validate(valid => {
                if (valid) {
                _this
                    .postRequest("/admin/updateInfo", {
                    imformation: updateinfo
                    })
                    .then(resp => {
                    _this.$message({
                        message: "修改成功",
                        type: "success"
                    });
                    var data = resp.data;
                    if (data) {
                        _this.updateInfoVisible = false;
                        _this.getAllInformations();
                        _this.$router.go(0);
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
        deleteInfo(id) {
            var _this = this;
            _this.id = index.id;
            this.$confirm("确定要删除该公告信息吗", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                this.deleteRequest("/admin/deleteInfo?id=" + _this.id).then(() => {
                _this.getAllInformations();
                });
            });
        },
    }
}
</script>

<style scoped>
.el-card {
    width: 75%;
}
 .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #dfe3e9;
  }
  .grid-head {
    border-radius: 4px;
    min-height: 30px;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 80px;
    width: auto;
  }
  .grid-bottom {
    border-radius: 4px;
    min-height: 30px;
  }
</style>