<template>
    <el-menu 
    class="el-menu-vertical"
    :default-active="selectItem"
    background-color="#0e1117"
    :collapse="isCollapse"
    router
    unique-opened>

      <!-- 收起侧边栏 -->
      <div class="switch" @click="changeCollapse">
        <span v-if="!isCollapse">| | |</span>
        <el-icon>
          <MoreFilled v-if="isCollapse" />
        </el-icon>
      </div>
      <!-- 功能模块 -->
      <el-sub-menu v-for="(item,index) in menuList" :index="item.path">

        <template #title>
          <el-icon>
            <MagicStick v-if="index===0"/>
            <Umbrella v-else-if="index===1"/>
            <HomeFilled v-else/>
          </el-icon>
          <span>{{item.authName}}</span>
        </template>
        <el-menu-item-group>
          <!-- 具体功能 -->
          <el-menu-item v-for="(innerItem) in item.children" :index="innerItem.path">
            {{innerItem.authName}}
          </el-menu-item>

        </el-menu-item-group>

      </el-sub-menu>
    </el-menu>
</template>

<script setup lang="ts">
import {
  onMounted,
  ref,
  watch,
} from 'vue'
import {
  Menu as IconMenu,
  MoreFilled,
  MagicStick,
  Umbrella,
  HomeFilled
} from '@element-plus/icons-vue'
import useStore from '../store';
import { storeToRefs } from 'pinia';
import { useRouter } from "vue-router";
import { getAsideList } from '../api/aside/index';
import { MenuItem } from '../api/aside/types';

/*
   侧边栏开关
*/
// Pinia
const asideStore = useStore().aside
let {
  isStoreCollapse,
  selectItem
} = storeToRefs(asideStore)

// 获取侧边栏开关状态
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

// 改变侧边栏开关状态
async function changeCollapse() {
  isCollapse.value = !isCollapse.value
  isStoreCollapse.value = isCollapse.value
  window.sessionStorage.setItem('isCollapse', `${isCollapse.value}`)
}

/*
   获取侧边栏信息
*/
let menuList = ref < MenuItem[] > ()
onMounted(async () => {
  menuList.value = await (await getAsideList()).data
})

// 选中菜单选项
const router = ref(useRouter().currentRoute);
watch(router,
async ()=>{   
  let savePath:string
  if(router.value.path.match('/query_certain')) {
    savePath = `/${router.value.path.split('/')[1]}`
  } else if(router.value.path.match('/warn_light')){
    savePath = `/${router.value.path.split('/')[1]}`
  }
  else {
    savePath = router.value.path
  } 
  selectItem.value = savePath
  window.sessionStorage.setItem('selectItem', savePath)
})

// 页面刷新时从本地缓存读取并激活上一次路径
onMounted(() => {
  if (window.sessionStorage.getItem('selectItem')) {
    selectItem.value = window.sessionStorage.getItem('selectItem') as string
  } else {
    selectItem.value = '/homepage'
  }
})

</script>



<style lang="less">
  .switch {
    text-align: center;
    cursor: pointer;
    color: rgba(211, 211, 211, 0.603);

    .el-icon {
      padding-top: 10px;
    }
  }

  .el-menu-item {
    color: grey;
  }

  .el-sub-menu__title {
    color: lightgray;
  }

  .el-menu-vertical:not(.el-menu--collapse) {
    width: 200px;
    height: 100%;
  }

  .el-menu--collapse {
    border: 0;
    height: 100%;
  }
</style>