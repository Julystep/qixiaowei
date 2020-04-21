<template>
  <div>
    <h1>欢迎{{ loginName }}使用小区物业管理系统</h1>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      loginName: this.$store.state.user.loginName,
      
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>