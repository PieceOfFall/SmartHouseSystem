<script setup lang="ts">
</script>

<template>

<router-view/>

</template>

<style scoped lang="less">
* {
  margin: 0;
  padding: 0;
}

/*定义滚动条轨道 内阴影+圆角*/
::-webkit-scrollbar-track {
  border-radius: 3px;
}

/* 修改的滚动条样式 */
::-webkit-scrollbar {
  width: 6px;
  height: 6px;
  background-color: #fff;
}

/*定义滚动条滑块 内阴影+圆角*/
::-webkit-scrollbar-thumb {
  border-radius: 3px;
  -webkit-box-shadow: inset 0 0 6px #cccccc;
  background-color: #cccccc;
  &:hover {
    background-color: #777777;
  }
}

/*定义滚动条宽度*/
::-webkit-scrollbar {
  width: 4px;
}

</style>
