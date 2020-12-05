<template>
  <h1>{{this.person}}</h1>
</template>

<script>
export default {
  name: "ObjectPassByStore",
  data: function (){
    return {
      person: this.$store.person
    }
  }
}
</script>

<style scoped>

</style>
