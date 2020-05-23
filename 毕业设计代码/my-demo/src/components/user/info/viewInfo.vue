<template>
  <div>
    <el-row style="margin-bottom: 10px">
      <el-col :span="6">
        <el-input
          placeholder="请输入内容"
          v-model="info"
          clearable
          @clear="getInfos"
          class="input-with-select"
          size="mini"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getInfos()"
          ></el-button>
        </el-input>
      </el-col>
    </el-row>

    <template v-for="(item, index) in information">
      <el-card :key="index" class="el-card">
        <el-row>
          <el-col :span="8"
            ><div>
              <el-tag
                @click="goDetail(item)"
                v-if="item.type === 1"
                type="danger"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
              <el-tag
                @click="goDetail(item)"
                v-if="item.type === 2"
                type="warning"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
              <el-tag
                @click="goDetail(item)"
                v-if="item.type === 3"
                type="primary"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
              <el-tag
                @click="goDetail(item)"
                v-if="item.type === 4"
                type="success"
                style="width:100%"
                >{{ item.head }}</el-tag
              >
            </div></el-col
          >
          <el-col :span="4" :offset="12">
            <el-button
              type="primary"
              size="small"
              circle
              @click="changeInfo(item)"
              class="el-icon-edit"
            >
            </el-button>
            <el-button
              type="danger"
              size="small"
              circle
              @click="deleteInfo(item.id)"
              class="el-icon-delete"
            >
            </el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24"
            ><div
              style="text-align: left; margin-left: 10px; margin-top: 20px; margin-bottom: 20px"
              class="grid-content bg-purple"
            >
              {{ item.content }}
            </div></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="24" style="text-align: left">
            <template v-if="item.picture != null">
              <template v-for="item1 in JSON.parse(item.picture.picture)">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="item1.imgUrl"
                  :preview-src-list="
                    handlePictureCardPreview(item.picture.picture)
                  "
                >
                </el-image>
              </template>
            </template>
          </el-col>
        </el-row>

        <el-row :gutter="200">
          <el-col :span="8"
            ><div class="grid-bottom bg-purple">
              <el-tag type="primary">
                {{ item.userId }}
              </el-tag>
            </div></el-col
          >
          <el-col :span="16"
            ><div class="grid-bottom bg-purple">
              <el-tag type="info">
                {{ formatDate(item.infotime) }}
              </el-tag>
            </div>
          </el-col>
        </el-row>
      </el-card>
    </template>
  </div>
</template>

<script>
export default {
  data() {
    return {
      information: [],
      info: ""
    };
  },
  mounted() {
    this.getInfos();
  },
  methods: {
    getInfos() {
      /* 获得所有的二级管理员 */
      var _this = this;
      this.getRequest("/user/getinfos?info=" + _this.info).then(resp => {
        _this.information = resp.data.information;
        console.log(_this.information);
      });
    },
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
    show(item) {
      console.log(item.picture.picture);
    },
    handlePictureCardPreview(picture) {
      this.urls = [];
      var x = JSON.parse(picture);
      for (var i = 0; i < x.length; i++) {
        this.urls.push(x[i].imgUrl);
      }
      return this.urls;
    },
    goDetail(item) {
      this.$router.push({
        path: "detailInfomation",
        query: { Info: JSON.stringify(item) }
      });
    }
  }
};
</script>

<style scoped>
.el-card {
  width: 75%;
}
.el-card:hover {
  cursor: pointer;
}
</style>
