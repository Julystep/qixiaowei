<template>
  <div>
  <el-row style="margin-bottom: 10px" :gutter="20">
      <el-col :span="8">
        <el-form :inline="true">
          </el-input>
          <el-date-picker
            size="mini"
            v-model="date"
            type="year"
            placeholder="选择年"
          >
          </el-date-picker>

          <el-button
            icon="el-icon-search"
            size="mini"
            @click="getUserPaymentByUserId"
          ></el-button
        ></el-form>
      </el-col>

    </el-row>
    <el-table :data="charge" border size="mini">
      <el-table-column prop="userId" label="用户编号" width="180">
      </el-table-column>
      <el-table-column prop="waterFee" label="水费" width="180">
      </el-table-column>
      <el-table-column prop="electricityFee" label="电费" width="180">
      </el-table-column>
      <el-table-column prop="propertyCosts" label="物业费" width="180">
      </el-table-column>
      <el-table-column label="时间" width="180">
        <template slot-scope="scope">
          {{ formatDate(scope.row.subtime) }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="360">
        <template slot-scope="scope">
          <template v-if="scope.row.state == 0">
            <el-button size="mini">点击提交</el-button>
          </template>
          <template v-if="scope.row.state == 1">
            <el-button size="mini" type="info" disabled>已提交</el-button>
          </template>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      date: new Date(),
      charge: []
    };
  },
  mounted() {
    this.getUserPaymentByUserId();
  },
  methods: {
    formatDate(value) {
      var date = new Date(value);
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var day = date.getDate();
      if (month < 10) {
        month = "0" + month;
      }
      if (day < 10) {
        day = "0" + day;
      }
      return year + "-" + month + "-" + day;
    },
    getUserPaymentByUserId() {
      var _this = this;
      this.postRequest("/user/getuserpaymentbyuserid", {
        userId: _this.$store.state.user.userId,
        date: _this.date
      }).then(resp => {
        _this.charge = resp.data;
        console.log(_this.charge);
      });
    }
  }
};
</script>
