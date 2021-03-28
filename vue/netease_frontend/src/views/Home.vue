<template>
  <div class="home">
    <el-container class="container">
      <div class="header bg">
        <el-col :span="24"
          ><div class="bg font"><b>我的音乐云盘</b></div></el-col
        >
        <div :span="24" class="bg twobutton">
          <el-button
            :span="3"
            size="small"
            type="danger"
            class="el-icon-caret-right"
            :show-file-list="false"
            @click="playAll()"
            :style="{ background: '#EC4141', paddingLeft: '15px' }"
            round
          >
            播放全部</el-button
          >
          <el-upload
            class="upload-demo"
            action="/api/song/upload"
            accept="*/*"
            ref="upload"
          >
            <el-button
              :span="3"
              size="small"
              type="info"
              class="el-icon-plus bg"
              round
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              >上传音乐</el-button
            >
          </el-upload>
        </div>
      </div>
      <el-main class="main bg">
        <el-table
          class="mytable"
          :cell-style="{ borderColor: '#2e2e2e' }"
          :header-cell-style="{
            background: '#2b2b2b',
            color: '#888888',
            borderColor: '#2e2e2e',
          }"
          :header-row-style="{
            background: '#2b2b2b',
            color: '#888888',
            borderColor: '#2e2e2e',
          }"
          :row-style="{ background: '#2b2b2b', color: '#D3D3D3' }"
          :data="songs"
          style="width: 110%"
        >
          <el-table-column
            label="序号"
            type="index"
            :width="screen_width * 0.2"
          >
          </el-table-column>
          <el-table-column
            prop="singer"
            label="歌手"
            :width="screen_width * 0.3"
          >
          </el-table-column>
          <el-table-column prop="name" label="歌名" :width="screen_width * 0.3">
          </el-table-column>
          <el-table-column
            label="播放"
            :width="screen_width * 0.18"
            align="center"
          >
            <template slot-scope="scope">
              <el-button
                class="el-icon-service"
                @click="playSong(scope.$index)"
                type="text"
                size="small"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <el-footer class="footer">
        <el-row>
          <el-button
            class="bg"
            size="mini"
            type="info"
            icon="el-icon-caret-left"
            @click="previosPlay()"
          ></el-button>
          <el-button
            class="bg"
            size="mini"
            type="info"
            icon="el-icon-video-pause"
            @click="playAndPause()"
          ></el-button>
          <el-button
            class="bg"
            size="mini"
            type="info"
            icon="el-icon-caret-right"
            @click="nextPlay()"
          ></el-button>
          <el-progress
            :percentage="this.percent"
            :stroke-width="2"
            status="exception"
          ></el-progress>
        </el-row>
      </el-footer>
    </el-container>
    <div>
      <audio
        ref="audio"
        hidden="hidden"
        src="http://47.108.63.126:8001/song/download?singer=BEYOND&songname=海阔天空"
        controls="controls"
        preload="false"
        @ended="nextPlay"
      >
        Your browser does not support the audio element.
      </audio>
    </div>
  </div>
</template>
<style scoped>
::v-deep ::-webkit-scrollbar {
  /*width: 0;宽度为0隐藏*/
  width: 0px;
}

::v-deep ::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  border-radius: 2px;
  background: #2b2b2b;
}
.header {
  position: absolute;
  left: 5%;
  right: 5%;
  top: 0%;
  bottom: 88%;
}
.main {
  position: absolute;
  left: 5%;
  right: 5%;
  top: 12%;
  bottom: 10%;
  overflow-y: scroll;
}
.footer {
  position: absolute;
  top: 90%;
  bottom: 0%;
  left: 5%;
  right: 5%;
  padding-top: 10px;
  background: #212124;
}
::v-deep .el-table tbody tr:hover > td {
  background-color: #373737;
}
.container {
  left: 5%;
  right: 5%;
  top: 0%;
  bottom: 0%;
}
.el-icon-d-arrow-left {
  color: #d2d2d2;
}
.el-icon-service {
  color: red;
}
.twobutton {
  float: left;
  padding-left: 40px;
  padding-bottom: 100px;
}
.el-icon-caret-right {
  float: left;
}

.upload-demo {
  float: right;
  padding-left: 20px;
}
.bg {
  background: #2b2b2b;
}
.table {
  background: #f9fafc;
}
.font {
  color: #d0d0d0;
  font-family: "黑体";
  font-size: 25px;
  margin-bottom: 7px;
}
.uploadbutton {
  background: red;
}
::v-deep .stroke-width {
  line-height: 1.5;
}
::v-deep .el-progress-bar {
  width: 95%;
}
::v-deep .el-progress-bar__outer {
  background-color: #464646;
}
::v-deep .el-upload-list__item-name {
  display: none !important;
}
::v-deep .el-icon-upload-success {
  display: none !important;
}
::v-deep .el-icon-circle-close {
  display: none !important;
}
::v-deep .el-icon-circle-check {
  display: none !important;
}
::v-deep .el-upload .el-upload--text {
  display: none !important;
}
</style>

<script>
export default {
  name: "Home",
  data: function () {
    return {
      songs: [],
      srcs: [],
      index: 0,
      fileList: null,
      timer: "",
      percent: 0,
    };
  },
  created() {
    axios.get("/api/songs").then((response) => {
      var songs = response.data.data.songs;
      this.songs = songs;
      for (var i = 0; i < songs.length; i++) {
        this.srcs.push(
          "http://47.108.63.126:8001/song/download?singer=" +
            songs[i].singer +
            "&songname=" +
            songs[i].name
        );
      }
      this.$refs.audio.src = this.srcs[0];
      this.index = songs.length - 1;
      window.setInterval(() => {
        setTimeout(this.getNewMessage(), 0);
      }, 1000);
    });
    this.screen_width = document.body.scrollWidth * 0.9;
  },
  methods: {
    playSong: async function (index) {
      this.index = index;
      this.$refs.audio.src = this.srcs[index];
      await this.$refs.audio.play();
    },
    playAndPause: function () {
      if (this.$refs.audio.paused) {
        this.$refs.audio.play();
      } else {
        this.$refs.audio.pause();
      }
    },
    previosPlay: function () {
      if (this.index == 0) {
        this.index = this.srcs.length - 1;
      } else {
        this.index = this.index - 1;
      }
      this.playSong(this.index);
    },
    nextPlay: function () {
      this.index = (this.index + 1) % this.srcs.length;
      this.playSong(this.index);
    },
    playAll: function () {
      this.playSong(0);
    },
    getNewMessage: function () {
      this.percent =
        (this.$refs.audio.currentTime * 100) / this.$refs.audio.duration;
    },
  },
  mounted() {
    var that = this;
    document.onkeydown = function (e) {
      if (window.event.keyCode == 32) {
        that.playAndPause();
      }
    };
  },
};
</script>
