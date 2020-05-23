<template>
  <div>
    <el-page-header @back="goBack" content="详情页面"> </el-page-header>
    <el-card style=" margin-top: 10px">
      <el-row>
        <el-col :span="3">
          <el-image
            style="width: 100px; height: 100px; height: 100%"
            :src="info.avator"
            fit="fill"
          ></el-image>
          <el-link
            style="width: 100%; text-align:center"
            :underline="false"
            type="primary"
            >{{ info.userName }}</el-link
          >
        </el-col>
        <el-col :offset="2" :span="16">
          <div
            style="width: 100%; margin-top: 10px; font-size:20px; font-weight:bold"
          >
            {{ info.head }}
          </div>
          <el-main style="text-align: left">
            {{ info.content }}
          </el-main>
        </el-col>
      </el-row>
      <el-row>
        <el-col :offset="5" :span="19" style="text-align: left">
          <template v-if="info.picture != null"
            ><template v-for="item2 in JSON.parse(info.picture.picture)">
              <el-image
                style="width: 100px; height: 100px"
                :src="item2.imgUrl"
                :preview-src-list="
                  handlePictureCardPreview(info.picture.picture)
                "
              >
              </el-image>
            </template>
          </template>
        </el-col>
      </el-row>

      <el-divider></el-divider>
      <template v-for="(item, index) in chat">
        <el-row>
          <el-col :span="3">
            <el-image
              style="width: 100px; height: 100px; height: 100%"
              :src="item.avator"
              fit="fill"
            ></el-image>
            <el-link
              style="width: 100%; text-align:center"
              :underline="false"
              type="primary"
              >{{ item.userName }}</el-link
            >
          </el-col>
          <el-col :offset="2" :span="16">
            <el-main style="text-align: left">
              {{ item.text }}
            </el-main>
          </el-col>
        </el-row>
        <template v-if="flag == false">
          <el-link type="primary" @click="showMessage(index)" :underline="false"
            >点击显示{{ item.children.length }}条评论<i
              class="el-icon-arrow-down"
            ></i
          ></el-link>
        </template>
        <template v-if="flag == true && index == indexStore">
          <el-row>
            <el-col :offset="4" :span="20" style="background-color: #eaedf1">
              <el-row
                v-for="(item1, index) in item.children"
                :key="index"
                style="margin-top:10px"
              >
                <el-col :span="3">
                  <el-image
                    style="width: 50px; height: 50px; height: 100%"
                    :src="item1.avator"
                    fit="fill"
                  ></el-image>
                  <el-link
                    style="width: 100%; text-align:center"
                    :underline="false"
                    type="primary"
                    >{{ item1.userName }}</el-link
                  >
                </el-col>
                <el-col :offset="2" :span="16">
                  <el-main style="text-align: left">
                    {{ item1.text }}
                  </el-main>
                </el-col>
              </el-row>
              <template>
                <el-link
                  type="primary"
                  @click="DisplayMessage()"
                  :underline="false"
                  >点击关闭<i class="el-icon-arrow-up"></i
                ></el-link>
              </template>
              <el-row>
                <el-col :offset="10">
                  <el-button size="mini" @click="showDialog()"
                    >我说一句</el-button
                  >
                </el-col>
              </el-row>
              <template v-if="dialogFlag == true">
                <el-row>
                  <el-col :offset="4" :span="20">
                    <el-input
                      style="margin-top:10px"
                      v-model="text1"
                      placeholder="说点什么吧"
                      type="textarea"
                    ></el-input>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :offset="11" style="margin-top: 5px">
                    <el-button
                      type="primary"
                      size="mini"
                      @click="publish1(item, index)"
                      >点击发表</el-button
                    >
                  </el-col>
                </el-row>
              </template>
            </el-col>
          </el-row>
        </template>
        <el-divider></el-divider>
      </template>
    </el-card>
    <el-input
      style="margin-top:10px"
      v-model="text"
      placeholder="说点什么吧"
      type="textarea"
    ></el-input>
    <el-row>
      <el-col :offset="11" style="margin-top: 5px">
        <el-button type="primary" @click="publish()">点击发表</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  inject: ["reload"],
  data() {
    return {
      info: JSON.parse(this.$route.query.Info),
      chat: [],
      text1: "",
      text: "",
      flag: false,
      dialogFlag: false,
      user: {
        userId: this.$store.state.user.userId,
        userName: this.$store.state.user.userName,
        avator: this.$store.state.user.avator,
        text: "",
        children: []
      }
    };
  },
  mounted() {
    var _this = this;
    this.getRequest("/getInfomation?id=" + this.info.id).then(resp => {
      this.info = resp.data;
      this.info.chat = JSON.parse(this.info.chat);
      if (this.info.chat != null) {
        this.chat = this.info.chat;
        console.log(this.info);
      }
    });
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
    goBack() {
      this.$router.push("manageInfomation");
    },
    publish1(item, index) {
      var user = JSON.parse(JSON.stringify(this.user));
      user.text = JSON.parse(JSON.stringify(this.text1));
      this.info.chat[index].children.push(user);
      this.text1 = "";
      this.dialogFlag = false;
      this.postRequest("/publicChat", {
        chat: JSON.stringify(this.chat),
        id: this.info.id
      }).then(resp => {
        if (resp.data == true) {
          this.$message.success("发表成功");
        }
      });
    },
    publish() {
      var user = JSON.parse(JSON.stringify(this.user));
      user.text = JSON.parse(JSON.stringify(this.text));
      this.text = "";
      this.chat.push(user);
      this.info.chat = this.chat;
      var _this = this;
      this.postRequest("/publicChat", {
        chat: JSON.stringify(this.chat),
        id: this.info.id
      }).then(resp => {
        if (resp.data == true) {
          this.$message.success("发表成功");
        }
      });
    },
    showMessage(index) {
      this.indexStore = index;
      this.flag = true;
    },
    DisplayMessage() {
      this.indexStore = -1;
      this.flag = false;
    },
    showDialog() {
      this.dialogFlag = true;
    }
  }
};
</script>
