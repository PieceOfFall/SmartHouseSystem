

<template>

  <router-view />

</template>

<script setup lang="ts">

</script>

<style scoped lang="less">
  @import "./assets/style/global.css";

  * {
    margin: 0;
    padding: 0;
  }

  :deep(.el-card__body) {
    background-color:#0e1117 !important;
  }


</style>