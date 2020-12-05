<template>
  <div class="home">
    <el-input placeholder="Please input path_para_pass" v-model="id"></el-input>
    <el-button type="primary" plain @click="path_para_pass">路径参数传递</el-button>
    <el-button type="info" plain @click="object_pass_by_store">store传递person对象</el-button>
    <el-button type="info" plain @click="object_pass_by_router">router传递person对象</el-button>
    <el-button type="warn" plain @click="delete_by_id(7)">删除ID6</el-button>
    <el-table
        :data="songs"
        stripe
        style="width: 100%">
      <el-table-column
          prop="id"
          label="ID"
          :width="screen_width*0.1">
      </el-table-column>
      <el-table-column
          prop="name"
          label="歌名"
          :width="screen_width/4">
      </el-table-column>
      <el-table-column
          prop="singer"
          label="歌手"
          :width="screen_width/4">
      </el-table-column>
      <el-table-column
          prop="feeling"
          label="感受"
          :width="screen_width/4">
      </el-table-column>
      <el-table-column
          label="Operations"
          width="120">
        <template slot-scope="scope">
          <el-button @click="handleClick" type="text" size="small">Detail</el-button>
          <el-button type="text" size="small">Edit</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data: function (){
    return {
      songs: '',
      screen_width: 0,
      id: '',
      person: {
        name: 'IzumiSakai',
        age: 40,
        favorite: 'sing songs'
      },
      song: {
        id: 10,
        name: '修改后的名字',
        singer: 'song_singer',
        feeling: 'song_feeling'
      }
    }
  },
  created() {
    axios.get("/song/songs").then(response => {
      this.songs=response.data.data.songs;
    });
    this.screen_width=document.body.scrollWidth;
  },
  methods: {
    path_para_pass: function (){
      this.$router.push({
        path:'/pathparapass',
        query: {id: this.id}
      })
    },
    object_pass_by_store: function (){
      this.$store.person=this.person
      this.$router.push({path:'/objectpassbystore'})
    },
    object_pass_by_router: function (){
      this.$router.push({
        path: '/objectpassbyrouter',
        query: {
          song: JSON.stringify(this.song)
        }
      })
    },
    delete_by_id: function (id){
      axios.delete('/song/songs/'+id);
    }
  }
}
</script>
