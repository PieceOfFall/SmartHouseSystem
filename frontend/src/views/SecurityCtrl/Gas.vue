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
                stripe 
                :data="renderDataList">
                    <el-table-column prop="time" :label="`记录时间 ${currentGap.toLocaleUpperCase()}`" />
                    <el-table-column prop="gas" label="记录值" />
                    <el-table-column label="操作">
                        <template #default="scope" v-if="currentGap!=='s'">
                          <el-button size="small" @click="getInfo(scope.$index, scope.row)"
                            >详情</el-button
                          >
                        </template>
                    </el-table-column>
                </el-table>
                
            </el-tab-pane>
        </el-tabs>

    </div>
</template>

<script setup lang="ts">
import Chart from '../../components/Chart.vue';
import DatePicker from '../../components/DatePicker.vue';
import {getCurrentData,getDataByDifference,getGapByDifference} from '../../api/sensor/index';
import {GasData, SensorData,queryType,sensorType} from '../../api/sensor/types';
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
onMounted( async()=>{
    setTimeout(()=>{
        window.scrollTo({
        top:1000,
        behavior: 'smooth'
    })
    },200)
    const data:GasData[] = (await getCurrentData('gas')).data.list
    let nowSecond:number = new Date().getSeconds()-5
    data.forEach(e=>{
        timeArray.value.push(nowSecond++)
        gasData.value.push(e.gas)
    })
})

// 每秒更新数据
setInterval(async()=>{
    const [data]:GasData[] = (await getCurrentData('gas')).data.list
    timeArray.value.shift()
    timeArray.value.push(new Date().getSeconds())
    gasData.value.shift()    
    gasData.value.push(data['gas'])
},1000)

/*
   查询历史数据
*/
// 渲染对象类型
interface RenderData {
    time:string
    timestamp:number // 仅存储,不显示
    gas:string
}
// 查询结果
const renderDataList = ref<RenderData[]>([])
// 当前数据查询间隔
const currentGap = ref<queryType>('s')

//获取查询范围并请求数据
async function confirmDate(date:[number,number]) {    
    if(!date){
        return
    } else {
        await getAndRenderByDifference(date[0],date[1])
    }
}

// 获取详情,并调用渲染
async function getInfo(index: number, row: RenderData){
    switch (currentGap.value) {
        case 'd':{
            await getAndRenderByDifference(row.timestamp,row.timestamp+1000*60*60*24-1000)
            break;
        }
        case 'h':{
            await getAndRenderByDifference(row.timestamp,row.timestamp+1000*60*60-1000)
            break;
        }
        case 'm':{
            await getAndRenderByDifference(row.timestamp,row.timestamp+1000*60-1000)
            break;
        }
    }
}

// 获取数据并渲染
async function getAndRenderByDifference(startTime: number, endTime: number) {    
    renderDataList.value = await (await getDataByDifference(startTime,endTime,'gas'))
        .data.list?.map((currentValue:GasData,index:number,array)=>{
            // 利用数组map方法将原始结果数组映射为渲染数组
            let date:Date = new Date(currentValue.time)
            return {
                    time: `${date.getFullYear()}年${date.getMonth()+1}月${date.getDate()}日  ${date.getHours()}:${date.getMinutes()<=9?'0'+date.getMinutes():date.getMinutes()}:${date.getSeconds()<=9?'0'+date.getSeconds():date.getSeconds()}`,
                    timestamp: currentValue.time,
                    gas:`${currentValue.gas*100}%`
                }
        })
        currentGap.value = getGapByDifference(endTime-startTime) as queryType
}


</script>

<style lang="less" scoped>
    .gas-container {
        text-align: center;
        padding: 2.5rem;
        height: 100%;
        background-color: #010409;
        font-weight: 300;
        .el-table{
            margin-top: 1rem;
            height: 70vh;
        }
    }


</style>