<template>
    <div id="home-container">
        <!-- 欢迎 -->
        <div id="welcome">
            <span>{{ userAccount }}</span> , 欢迎回家 !
        </div>
        <!-- 使用天数 -->
        <div id="days">
            539智能家居 已为您保驾护航 <span>{{ tweened.number.toFixed(0) }}</span> 天
        </div>

        <div id="layout-container">
            <el-row :gutter="20">

                <!-- 用户操作历史 -->
                <el-col :span="6">
                    <ElectricHistory/>
                </el-col>
                
                <!-- 实时温湿度 -->
                <el-col :span="18">
                    <BarAndLineChart 
                    bar-name="湿度" 
                    line-name="温度"
                    :y-axis-bar="{
                        min:0,
                        max:1,
                        interval:0.1
                    }"
                    :y-axis-line="{
                        min:0,
                        max:40,
                        interval:4
                    }"
                    :x-axis="timeArray"
                    :data-source="chartData"
                    />

                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script setup lang="ts">
import gsap from 'gsap'
import { storeToRefs } from 'pinia';
import useStore from '../store';
import {getUserCreateTimestamp} from '../api/user';
import {reactive,
        ref,
        onMounted,
        onBeforeUnmount,
        watch } from 'vue';
import {HumidityAndTemperature,
        HumidityAndTemperaturePromise,
        TemperatureData,
        HumidityData} from '../api/sensor/types';
import { getCurrentData } from '../api/sensor/index';
import ElectricHistory from '../components/ElectricHistory.vue';
import BarAndLineChart from '../components/BarAndLineChart.vue';

/*
   欢迎使用
*/
// Pinia
const userStore = useStore().user
const { userAccount } = storeToRefs(userStore)

/*
   获取使用天数
*/
const tweened = reactive({
  number: 0
})
const useDays = ref<number>(0)
onMounted(async()=>{
    const createdTimestamp = await (await getUserCreateTimestamp()).data
    useDays.value = Math.floor((new Date().getTime() - createdTimestamp)/(1000*60*60*24))
})
// 数值渐变
watch(useDays, (n) => {
  gsap.to(tweened, { duration: 1, number: Number(n) || 0 })
})

/*
   温湿度柱面折线图
*/
// 横轴时间
const timeArray = ref<number[]>([])
// 图表数据
const chartData = ref<HumidityAndTemperature>({bar:[],line:[]})

// 初始化状态数据
onMounted(async()=>{
    const humidityAndTemperaturePromiseData:HumidityAndTemperaturePromise = await Promise
    .all([
    (await getCurrentData('temperature')).data.list,
    (await getCurrentData('humidity')).data.list
    ])

    let nowSecond:number = new Date().getSeconds()-5
    const temperatureArray = humidityAndTemperaturePromiseData[0]
    const humidityArray = humidityAndTemperaturePromiseData[1]
    for(let i = 0;i<humidityAndTemperaturePromiseData[0].length;i++) {
        timeArray.value.push(nowSecond++)
        chartData.value.bar.push(humidityArray[i].humidity)
        chartData.value.line.push(temperatureArray[i].temperature)
    }

})

// 每秒更新数据
const updateInterval = setInterval(async()=>{
    timeArray.value.shift()
    timeArray.value.push(new Date().getSeconds())

    const [humidityData]:HumidityData[] = (await getCurrentData('humidity')).data.list
    chartData.value.bar.shift()
    chartData.value.bar.push(humidityData.humidity)

    const [temperatureData]:TemperatureData[] = (await getCurrentData('temperature')).data.list
    chartData.value.line.shift()
    chartData.value.line.push(temperatureData.temperature)
},1000)

// 页面卸载时停止更新
onBeforeUnmount(()=>{
    clearInterval(updateInterval)
})

</script>

<style lang="less" scoped>
    #home-container{
        padding: 1.5rem;
        font-size: large;
        height: 100%;
        background-color: #010409;
        color: #f1f6fcae;
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