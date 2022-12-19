<template>
            <div class="header-container" @click="backHome">
                <!-- 标题 -->
                <span>539 <b>智</b> 能家居</span>
            </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import useAsideStore from '../store';
import {
  storeToRefs
} from 'pinia';

// pinia
const store = useAsideStore().aside
let {
    selectItem
} = storeToRefs(store)

// 点击标题回到主页
const router = useRouter()
async function backHome() {
    router.push('/homepage')
    selectItem.value = '/homepage'
    window.sessionStorage.setItem('selectItem','/homepage')
}

</script>

<style lang="less" scoped>
    .header-container {
        box-sizing: border-box;
        letter-spacing: 0.2rem;
        height: 100%;
        padding: 1.5rem 1rem;
        background-color: #171b22;
        
        span {
            cursor: pointer;
            font-family: 'STHeiti';
            margin-left: 1rem;
            color: gainsboro;

            b {
                font-size: large;
                font-family: 'STXingkai';
                box-shadow: 0 0 1.1rem white;
                padding: 5px;
                border-radius: 50%;
            }

        }

    }
</style>