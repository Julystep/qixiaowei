<template>
  <div>
    <el-table
      :data="houses"
      border
      size="mini"
      row-key="hid"
      highlight-current-row
    >
      <el-table-column label="房间号" prop="hid" width="200"> </el-table-column>
      <el-table-column label="房间名" prop="hname" width="200">
      </el-table-column>
      <el-table-column label="面积" prop="hArea" width="200"> </el-table-column>
      <el-table-column label="业主" prop="userName" width="200">
      </el-table-column>
      <el-table-column label="邮箱" prop="email" width="230"> </el-table-column>
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
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      building: {},
      houses: [],
      currentPage: 1,
      count: 0
    };
  },
  mounted() {
    this.getBuildingOfUser();
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
          10
      ).then(resp => {
        console.log(resp.data);
        _this.building = resp.data.building;
        _this.houses = resp.data.houses;
        _this.count = resp.data.count;
      });
    }
  }
};
</script>
