<template>
  <div>
    <h1>
      {{this.song}}
    </h1>
    <el-button type="success" plain @click="insert">新增</el-button>
    <el-button type="info" plain @click="put">修改</el-button>
  </div>
</template>

<script>
export default {
  name: "ObjectPassByRouter",
  data: function (){
    return {
      song: JSON.parse(this.$route.query.song)
    }
  },
  methods: {
    insert: function (person){
      axios.post('/song/songs',this.song).then(response => {
        console.log(response)
      })
    },
    put: function (){
      axios.put('/song/songs',this.song).then(response => {
        console.log(response)
      })
    }
  }
}
</script>

<style scoped>

</style>
