<template>
    <div class="home-container">
        <!-- 欢迎 -->
        <div id="welcome">
            <span>{{ userAccount }}</span> , 欢迎回家 !
        </div>
        <!-- 使用天数 -->
        <div id="days">
            539智能家居 已为您保驾护航 <span>{{ useDays }}</span> 天
        </div>

        <div id="layout-container">
            <el-row :gutter="20">
                <!-- 用户操作历史 -->
                <el-col :span="6">
                    <ElectricHistory/>
                </el-col>
                <!-- TODO: 实时温湿度 -->
                <el-col :span="18">
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script setup lang="ts">
import { storeToRefs } from 'pinia';
import useStore from '../store';
import {getUserCreateTimestamp} from '../api/user';
import { ref,onMounted } from 'vue';
import ElectricHistory from '../components/ElectricHistory.vue';

/*
   欢迎使用
*/
// Pinia
const userStore = useStore().user
const { userAccount } = storeToRefs(userStore)

/*
   获取使用天数
*/
const useDays = ref<number>(0)
onMounted(async()=>{
    const createdTimestamp = await (await getUserCreateTimestamp()).data
    useDays.value = Math.floor((new Date().getTime() - createdTimestamp)/(1000*60*60*24))
})

</script>

<style lang="less" scoped>
    .home-container{
        padding: 2.5rem;
        font-size: large;
        height: 100%;
        background-color: #010409;
        color: #f1f6fc;
        font-weight: 300;
        #welcome {
            font-style: italic;
            user-select: none;
            span {
                font-weight: bold;
                letter-spacing: 0.1rem;
            }
        }
        #days {
            user-select: none;
            color: darkgray;
            font-style: italic;
            font-size: 14px;
            margin-top: 0.5rem;
            padding-left: 1.2rem;
            letter-spacing: 0.05rem;
            font-weight: 300;
            span{
                color: lightblue;
                font-size: 18px;
            }
        }
        #layout-container {
            margin-top: 1rem;
            height: 70vh;
        }
    }
</style>