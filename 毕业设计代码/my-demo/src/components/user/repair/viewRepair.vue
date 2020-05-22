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
    <el-table :data="repair" border size="mini">
      <el-table-column prop="type" label="报修类型" width="400">
      </el-table-column>
      <el-table-column prop="detail" label="报修详情" width="400">
      </el-table-column>
      <el-table-column prop="status" label="状态" width="400">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status" :disabled="true"> </el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="查看图片" width="479">
        <template slot-scope="scope"
          ><template v-for="item in JSON.parse(scope.row.picture)">
            <el-image
              style="width: 40px; height: 40px"
              :src="item.imgUrl"
              :preview-src-list="handlePictureCardPreview(scope.row.picture)"
            >
            </el-image>
          </template>
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
      urls: [],
      dialogVisible: false
    };
  },
  mounted() {
    this.getRepair();
  },
  methods: {
    handlePictureCardPreview(picture) {
      this.urls = [];
      var x = JSON.parse(picture);
      for (var i = 0; i < x.length; i++) {
        this.urls.push(x[i].imgUrl);
      }
      return this.urls;
    },
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
        _this.repair = resp.data.repair;
        /* for (var i = 0; i < _this.repair.length; i++) {
          _this.repair[i].picture = JSON.parse(_this.repair[i].picture);
          console.log(JSON.parse(_this.repair[i].picture));
        }
        console.log(_this.repair); */
        _this.count = resp.data.count;
      });
    },
    currentChange(currentPage) {
      this.currnetPage = currentPage;
      this.getRepair();
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
