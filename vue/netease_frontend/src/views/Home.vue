<template>
  <div class="home">
    <el-table
        :data="songs"
        stripe
        style="width: 100%">
      <el-table-column 
          hidden="hidden"
          prop="id"
          label="ID"
          :width="screen_width*0.1">
      </el-table-column>
      <el-table-column
          prop="singer"
          label="歌手"
          :width="screen_width/4">
      </el-table-column>
      <el-table-column
          prop="name"
          label="歌名"
          :width="screen_width/4">
      </el-table-column>
      <el-table-column
          label="Operations"
          width="120">
        <template slot-scope="scope">
          <el-button @click="playSong(scope.row.id)" type="text" size="small">play</el-button>
          <el-button type="text" size="small">Edit</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <audio 
      ref="audio"
      hidden="hidden"
      src="" 
      loop = "true"
      preload="true"
      controls="controls">
      Your browser does not support the audio element.
      </audio>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data: function (){
    return {
      songs: '',
      song: {
        id: 10,
        name: '修改后的名字',
        singer: 'song_singer'
      }
    }
  },
  created() {
    axios.get("/api/songs").then(response => {
      this.songs=response.data.data.songs;
    });
    this.screen_width=document.body.scrollWidth;
  },
  methods: {
    setAudioSrc:  async function(id){
        await axios.get("/api/song/"+id).then(response => {
          this.song = response.data.data.song;
        });
        var songSinger = this.song.singer;
        var songName = this.song.name;
        this.$refs.audio.src = "http://47.108.63.126:8001/song/download?singer="+songSinger+"&songname="+songName;
    },
    audioPlay: function(){
      this.$refs.audio.play();
    },
    playSong: async function(id){
        await this.setAudioSrc(id);
        await this.audioPlay();
    }
  }
}
</script>
