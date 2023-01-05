<template>
    <div class="shake-container">
            <!-- 面包屑 -->
            <Breadcrumb bread-route="震动检测"/>
            <!-- 标签页 -->
            <el-tabs
            v-model="activeName"
            type="border-card"
            >
            <!-- 当前监测 -->
            <el-tab-pane label="当前状态监测" name="currentDetect">
                <Chart 
                x-name="时间"
                :x-array="timeArray"
                :data-source="shakeData"
                />
            </el-tab-pane>
            <!-- 历史查询 -->
            <el-tab-pane label="历史状态查询" name="second">
                <!-- 时间选择 -->
                <DatePicker
                @change = "confirmDate"
                />
                <!-- 查询页占位符 -->
                <router-view/>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script setup lang="ts">
import Chart from '../../components/LineChart.vue';
import { ref,onMounted,nextTick,onBeforeUnmount } from 'vue';
import DatePicker from '../../components/DoubleDatePicker.vue';
import { getCurrentData } from '../../api/sensor';
import { ShakeData } from '../../api/sensor/types';
import {useRouter} from 'vue-router'

/*
   默认选中监测卡片
*/
const activeName = ref('currentDetect')

/*
   当前震动状态折线图
*/
// 横轴时间
const timeArray = ref<number[]>([])
// 震动数据
const shakeData = ref<number[]>([])

// 初始化状态数据
onMounted(async()=>{
    setTimeout(()=>{
        window.scrollTo({
        top:1000,
        behavior: 'smooth'
    })
    },200)
    const data:ShakeData[] = (await getCurrentData('shake')).data.list
    let nowSecond:number = new Date().getSeconds()-5
    
    data.forEach(e=>{
        timeArray.value.push(nowSecond++)
        shakeData.value.push(e.shake)
    })
})

// 每秒更新数据
const updateInterval = setInterval(async()=>{
    const [data]:ShakeData[] = (await getCurrentData('shake')).data.list
    timeArray.value.shift()
    timeArray.value.push(new Date().getSeconds())
    shakeData.value.shift()
    shakeData.value.push(data['shake'])
},1000)

// 页面卸载时停止更新
onBeforeUnmount(()=>{
    clearInterval(updateInterval)
})

/*
   获取查询范围,跳转查询页进行查询
*/
const router =useRouter()
async function confirmDate(date:[number,number]){
    nextTick(async()=>{
        router.push({
             path: '/shake_detect/query_certain_shake_data', 
             query: { startTime: date[0],endTime: date[1] } 
            })
    })
}


</script>

<style lang="less" scoped>
    .shake-container {
        text-align: center;
        padding: 2.5rem;
        height: 100%;
        background-color: #010409;
        font-weight: 300;
    }
</style>