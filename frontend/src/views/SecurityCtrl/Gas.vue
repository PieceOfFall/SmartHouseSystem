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
                <!-- 时间选择 -->
                <DatePicker
                @change = "confirmDate"
                />
                <!-- 数据显示 -->
                <el-table 
                :data="[{},{},3]">
                    <el-table-column />
                </el-table>
            </el-tab-pane>
        </el-tabs>

    </div>
</template>

<script setup lang="ts">
import Chart from '../../components/Chart.vue';
import DatePicker from '../../components/DatePicker.vue';
import {getCurrentData} from '../../api/sensor/index';
import {GasData} from '../../api/sensor/types';
import { ref,onMounted } from 'vue';

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
    const data:Array<GasData> = (await getCurrentData('gas')).data.list
    let nowSecond:number = new Date().getSeconds()-5
    data.forEach(e=>{
        timeArray.value.push(nowSecond++)
        gasData.value.push(e.gas)
    })
})

// 每秒更新数据
setInterval(async()=>{
    // let a = (await getCurrentData('gas')).data.list
    // let [data] = a
    // console.log(a);
    
    // console.log(data);
    
    // timeArray.value.shift()
    // timeArray.value.push(new Date().getSeconds())
    // gasData.value.shift()    
    // gasData.value.push(data['gas'])
},1000)

/*
   查询历史数据
*/
//获取查询范围并请求数据
async function confirmDate(date:[]) {
    console.log(date);
}   


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