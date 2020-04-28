<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          v-model="info"
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
          <el-col :span="4" :offset="12"
            ><el-tag type="danger">{{ item.type }}</el-tag></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="24"
            ><div class="grid-content bg-purple">
              {{ item.content }}
            </div></el-col
          >
        </el-row>
        <el-row :gutter="450">
          <el-col :span="12"
            ><div class="grid-bottom bg-purple">
              {{ item.userId }}
            </div></el-col
          >
          <el-col :span="12"
            ><div class="grid-bottom bg-purple">
              {{ item.infotime }}
            </div></el-col
          >
    <div>
        <el-row style="margin-bottom: 10px">
            <el-col :span="6">
                <el-input
                placeholder="请输入内容"
                v-model="info"
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
      </el-card>
    </template>

<script>
export default {
    data() {
        return {
            information: [],
            info: "",
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
    min-height: 36px;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 80px;
    width: auto;
  }
  .grid-bottom {
    border-radius: 4px;
    min-height: 36px;
  }
</style>