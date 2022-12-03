<template>

  <el-menu default-active="2" class="el-menu-vertical" background-color="#0e1117" :collapse="isCollapse"
    @open="handleOpen" @close="handleClose" unique-opened>

    <!-- 收起侧边栏 -->
    <el-menu-item index="0" @click="changeCollapse">
      <el-icon>
        <Minus v-if="!isCollapse" />
        <MoreFilled v-else />
      </el-icon>
      <span>收起</span>
    </el-menu-item>

    <!-- 电控系统 -->

  </el-menu>
</template>

<script lang="ts">
  import {
    defineComponent,
    onMounted,
    ref
  } from 'vue'
  import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
    Minus,
    MoreFilled
  } from '@element-plus/icons-vue'
  import useAsideStore from '../store';
  import {
    storeToRefs
  } from 'pinia';

  export default defineComponent({
    components: {
      Document,
      IconMenu,
      Location,
      Setting,
      Minus,
      MoreFilled
    },
    setup() {
      // pinia
      const store = useAsideStore().aside
      let {
        isStoreCollapse
      } = storeToRefs(store)

      // 侧边栏开关
      const isCollapse = ref(true)
      onMounted(() => {
        let storageCollapse = window.sessionStorage.getItem('isCollapse')
        if (storageCollapse === 'true') {
          isCollapse.value = true
        } else {
          isCollapse.value = false;
        }
        isStoreCollapse.value = isCollapse.value

      })

      async function changeCollapse() {
        isCollapse.value = !isCollapse.value;
        isStoreCollapse.value = isCollapse.value
        window.sessionStorage.setItem('isCollapse', `${isCollapse.value}`)
      }
      const handleOpen = (key: string, keyPath: string[]) => {
        console.log(key, keyPath)
      }
      const handleClose = (key: string, keyPath: string[]) => {
        console.log(key, keyPath)
      }


      return {
        isCollapse,
        handleOpen,
        handleClose,
        changeCollapse
      }

    }
  })
</script>



<style>
  .el-menu-vertical:not(.el-menu--collapse) {
    width: 200px;
    height: 100%;
  }

  .el-menu--collapse {
    border: 0;
    height: 100%;
  }
</style>