<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          v-model="repairInfo"
          class="input-with-select"
          size="mini"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getRepair()"
          ></el-button>
        </el-input>
      </el-col>
    </el-row>
    <el-table :data="repair" border style="width: 70%" size="mini">
      <el-table-column prop="type" label="报修类型" width="300"> </el-table-column>
      <el-table-column prop="detail" label="报修详情" width="300">
      </el-table-column>
      <el-table-column prop="status" label="状态" width="300">
        <template slot-scope="scope">
        <el-switch
          v-model="scope.row.status" :disabled="true">
        </el-switch>
      </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 5px">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="count"
        :currentPage="currentPage"
        @current-change="currentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentPage: 1,
      repair: [],
      count: 0,
      repairInfo: "",
    };
  },
  mounted() {
    this.getRepair();
  },
  methods: {
    getRepair() {
      var _this = this;
      this.getRequest(
        "/user/getrepair?userid=" +
          _this.$store.state.user.userId +
          "&page=" +
          _this.currentPage +
          "&size=" +
          10 +
          "&repairInfo=" +
          _this.repairInfo
      ).then(resp => {
        console.log(resp.data);
        _this.repair = resp.data.repair;
        _this.count = resp.data.count;
      });
    },
    currentChange(currentPage) {
      this.currnetPage = currentPage;
      this.getRepair();
    },
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
