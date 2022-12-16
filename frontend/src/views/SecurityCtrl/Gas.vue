<template>
    <div class="gas-container">
        <!-- 面包屑 -->
        <Breadcrumb bread-route="燃气检测"/>
        <Chart 
        x-name="时间"
        :x-array="timeArray"
        :data-source="gasData"
        />
    </div>
</template>

<script setup lang="ts">
import Chart from '../../components/Chart.vue';
import {getCurrentGas} from '../../api/sensor/index';
import { ref,onMounted,nextTick } from 'vue';

/*
   当前燃气状态折线图
*/
// 横轴时间
const timeArray = ref<string[]>(['a','b'])
// 燃气数据
const gasData = ref<number[]>([])

setInterval(async()=>{
    const [data]:number[] = (await getCurrentGas()).data.list
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