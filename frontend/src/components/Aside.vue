<template>

  <el-menu default-active="2" class="el-menu-vertical" background-color="#0e1117" :collapse="isCollapse"
    @open="handleOpen" @close="handleClose">
    <el-menu-item index="0" @click="changeCollapse">

      <el-icon>
        <Minus v-if="!isCollapse" />
        <MoreFilled v-else />
      </el-icon>
      <span>switch</span>

    </el-menu-item>
    <el-sub-menu index="1">
      <template #title>
        <el-icon>
          <location />
        </el-icon>
        <span>Navigator One</span>
      </template>
      <el-menu-item-group>
        <template #title><span>Group One</span></template>
        <el-menu-item index="1-1">item one</el-menu-item>
        <el-menu-item index="1-2">item two</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="Group Two">
        <el-menu-item index="1-3">item three</el-menu-item>
      </el-menu-item-group>
      <el-sub-menu index="1-4">
        <template #title><span>item four</span></template>
        <el-menu-item index="1-4-1">item one</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>
    <el-menu-item index="2">
      <el-icon>
        <icon-menu />
      </el-icon>
      <template #title>Navigator Two</template>
    </el-menu-item>
    <el-menu-item index="3" disabled>
      <el-icon>
        <document />
      </el-icon>
      <template #title>Navigator Three</template>
    </el-menu-item>
    <el-menu-item index="4">
      <el-icon>
        <setting />
      </el-icon>
      <template #title>Navigator Four</template>
    </el-menu-item>
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
    components:{
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


      return{
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