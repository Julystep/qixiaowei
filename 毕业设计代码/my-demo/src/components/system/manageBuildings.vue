<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          class="input-with-select"
          size="mini"
        >
          <el-button slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </el-col>
      <el-col :span="2" :offset="16">
        <el-button type="primary" size="mini" style="width: 100%"
          >添加楼层</el-button
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
                class="input-with-select"
                size="mini"
              >
                <el-button slot="append" icon="el-icon-search"></el-button>
              </el-input>
            </el-col>
            <el-col :span="2" :offset="16">
              <el-button type="primary" size="mini" style="width: 100%"
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
                  content="修改管理员信息"
                  placement="top"
                >
                  <el-button
                    type="primary"
                    size="small"
                    circle
                    class="el-icon-info"
                  ></el-button>
                </el-tooltip>

                <el-tooltip
                  class="item"
                  effect="dark"
                  content="删除房间员信息"
                  placement="top"
                >
                  <el-button
                    type="danger"
                    size="small"
                    circle
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
        </template>
      </el-table-column>
      <el-table-column label="操作" width="580">
        <template slot-scope="scope">
          <el-tooltip
            class="item"
            effect="dark"
            content="修改管理员信息"
            placement="top"
          >
            <el-button
              type="primary"
              size="small"
              circle
              class="el-icon-info"
            ></el-button>
          </el-tooltip>

          <el-tooltip
            class="item"
            effect="dark"
            content="删除管理员信息"
            placement="top"
          >
            <el-button
              type="danger"
              size="small"
              circle
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      buildings: [],
      buildingCounts: 0,
      currentPageBuildings: 1,
      currentPageHouses: 1,
      housesCount: 0,
      houses: []
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
