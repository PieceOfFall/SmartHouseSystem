<template>
    <div class="gas-container">
        <!-- 面包屑 -->
        <Breadcrumb bread-route="燃气检测"/>
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
                :data-source="gasData"
                />
            </el-tab-pane>
            <!-- 历史查询 -->
            <el-tab-pane label="历史状态查询" name="second">

            </el-tab-pane>
        </el-tabs>

    </div>
</template>

<script setup lang="ts">
import Chart from '../../components/Chart.vue';
import {getCurrentData} from '../../api/sensor/index';
import { ref,onMounted,getCurrentInstance,ComponentInternalInstance } from 'vue';

/*
   默认选中监测卡片
*/
const activeName = ref('currentDetect')

/*
   当前燃气状态折线图
*/
// 横轴时间
const timeArray = ref<number[]>([])
// 燃气数据
const gasData = ref<number[]>([])
    
// 初始化状态数据
onMounted(async()=>{
    setTimeout(()=>{
        window.scrollTo({
        top:1000,
        behavior: 'smooth'
    })
    },200)
    const data:number[] = (await getCurrentData('gas')).data.list
    let nowSecond:number = new Date().getSeconds()-5
    data.forEach(e=>{
        timeArray.value.push(nowSecond++)
        gasData.value.push(e)
    })
})

// 每秒更新数据
setInterval(async()=>{
    const [data]:number[] = (await getCurrentData('gas')).data.list
    timeArray.value.shift()
    timeArray.value.push(new Date().getSeconds())
    gasData.value.shift()
    gasData.value.push(data)
},1000)


</script>

<style lang="less" scoped>
    .gas-container {
        text-align: center;
        padding: 2.5rem;
        height: 100%;
        background-color: #010409;
        font-weight: 300;
    }


</style>