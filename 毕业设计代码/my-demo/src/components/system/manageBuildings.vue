<template>
  <div>
    <el-table :data="buildings" style="width: 100%">
      <el-table-column label="商品 ID" prop="bid"> </el-table-column>
      <el-table-column label="商品名称" prop="bname"> </el-table-column>
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      buildings: [],
      buildingCounts: 0,
      currentPageBuildings: 1
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
        size: 10
      }).then(resp => {
        _this.buildings = resp.data.buildings;
        _this.buildingCounts = resp.data.buildingCounts;
      });
    },
    currentChangeBuildings(currentPageBuildings) {
      this.currentPageBuildings = currentPageBuildings;
      this.getAllBuildings();
    }
  }
};
</script>
