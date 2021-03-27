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
          :auto-upload="true"
          >
            <el-button :span="3" size="small" type="info" class="el-icon-plus bg" round>上传音乐</el-button>  
          </el-upload>
        </div>
      </div>
      <el-main class="main bg">
        <el-table
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
          :cell-mouse-enter="cellMouseEnter(row)"
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
          <el-table-column label="播放" :width="screen_width * 0.18">
            <template slot-scope="scope">
              <el-button
                class="el-icon-service"
                @click="playSong(scope.$index)"
                type="text"
                size="small"
              ></el-button
              >
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
          <el-progress :percentage="30" status="exception"></el-progress>
        </el-row>
      </el-footer>
    </el-container>
    <div>
      <audio
        ref="audio"
        hidden="hidden"
        src="http://47.108.63.126:8001/song/download?singer=BEYOND&songname=海阔天空"
        preload="true"
        controls="controls"
        @ended="nextPlay"
      >
        Your browser does not support the audio element.
      </audio>
    </div>
  </div>
</template>
<style scoped>
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
.el-icon-service{
  color: red;
}
.twobutton {
  float: left;
  padding-left: 40px;
  padding-bottom: 100px;
}
.el-icon-caret-right{
  float: left;
}

.upload-demo{
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
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
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
      };
      this.$refs.audio.src = this.srcs[0];
      this.index = songs.length - 1;
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
    cellMouseEnter: function(row){
      console.log("enter")
      console.log(row);
    },
    playAll: function(){
      this.playSong(0);
    }
  },
};
</script>
