<template>
  <div>
    <el-card>
      <el-table
        :data="limitList"
        stripe
        style="width: 100%">
        <el-table-column
          prop="userId"
          label="用户账号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="userName"
          label="用户名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="limitid"
          label="权限">
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="changeInfo(scope.row)">编辑
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pagesize"
        layout="total, prev, pager, next, jumper"
        :total="total">
      </el-pagination>

    </el-card>

  </div>
</template>

<script>
export default {
  data () {
    return {
      limitList:[],
      limitInfo:'',
      currentPage: 1,
      pagesize: 3,
      count:0,
      total:12,
    }
  },
  methods: {
    handleSizeChange(newSize){
          this.size=newSize;
          this.getHouseList()
        },
        handleCurrentChange(newPage){
          this.currentPage=newPage;
          this.getHouseList()
        }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>