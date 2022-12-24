<template>
    <div>
        <!-- 数据显示 -->
        <div @click="queryOrBack" class="back"><el-button :icon="Search">上一步</el-button></div>
        <el-table 
        stripe 
        :data="renderDataList">
            <el-table-column prop="time" :label="`记录时间 ${currentGap.toLocaleUpperCase()}`" />
            <el-table-column prop="sensor" label="记录值" />
            <el-table-column label="操作">
                <template #default="scope" v-if="currentGap!=='s'">
                  <el-button size="small" @click="getInfo(scope.$index, scope.row)"
                    >详情</el-button
                  >
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script setup lang="ts">
import { Search } from '@element-plus/icons-vue'
import { ref,onMounted,watch } from 'vue';
import {useRouter} from 'vue-router'
import {getDataByDifference,getGapByDifference} from '../../api/sensor/index';
import { SensorData,queryType,sensorType} from '../../api/sensor/types';

const router = useRouter()

/*
   查询query参数范围数据，并渲染
*/
// 渲染对象类型
interface RenderData {
    time:string
    timestamp:number // 仅存储,不显示
    sensor:string
}
// 查询结果
const renderDataList = ref<RenderData[]>([])
// 当前数据查询间隔
const currentGap = ref<queryType>('s')
// 当前查询的传感器类型
let querySensorType: sensorType

//获取到待查询的传感器类型和时间范围,执行查询
onMounted( async()=>{  
    const startTime:number = parseInt(router.currentRoute.value.query.startTime as string)
    const endTime:number = parseInt(router.currentRoute.value.query.endTime as string)
    querySensorType = router.currentRoute.value.path.split('/')[1].split('_')[0] as sensorType
    await getAndRenderByDifference(startTime,endTime)
})

// 获取数据并渲染
async function getAndRenderByDifference(startTime: number, endTime: number) {  
    if(!startTime){
        return  
    }    
    renderDataList.value = await (await getDataByDifference(startTime,endTime,`${querySensorType}`))
        .data.list?.map((currentValue:SensorData,index:number,array)=>{
            // 利用数组map方法将原始结果数组映射为渲染数组
            let date:Date = new Date(currentValue.time)
            return {
                    time: `${date.getFullYear()}年${date.getMonth()+1}月${date.getDate()}日  ${date.getHours()}:${date.getMinutes()<=9?'0'+date.getMinutes():date.getMinutes()}:${date.getSeconds()<=9?'0'+date.getSeconds():date.getSeconds()}`,
                    timestamp: currentValue.time,
                    sensor:`${querySensorType==='gas'?currentValue[`${querySensorType}`]*100:currentValue[`${querySensorType}`]}%`
                }
        })
        
        currentGap.value = getGapByDifference(endTime-startTime) as queryType
        
}

// 获取详情,并跳转
async function getInfo(index: number, row: RenderData){
    switch (currentGap.value) {
        case 'd':{
            router.push({path: `/${querySensorType}_detect/query_certain_${querySensorType}_data`, query: { startTime: row.timestamp,endTime: row.timestamp+1000*60*60*24-1000 }})
            break;
        }
        case 'h':{
            router.push({path: `/${querySensorType}_detect/query_certain_${querySensorType}_data`, query: { startTime: row.timestamp,endTime: row.timestamp+1000*60*60-1000 }})
            break;
        }
        case 'm':{
            router.push({path: `/${querySensorType}_detect/query_certain_${querySensorType}_data`, query: { startTime: row.timestamp,endTime: row.timestamp+1000*60-1000 }})
            break;
        }

    }
    
}

// 回退按钮
async function queryOrBack() {
    router.back()
}

// 路由变量
const query = ref(router)
// 通过监测路由query变化,请求所需数据
watch(query.value,async()=>{
    const startTime:number = parseInt(router.currentRoute.value.query.startTime as string)
    const endTime:number = parseInt(router.currentRoute.value.query.endTime as string)
    await getAndRenderByDifference(startTime,endTime)   
},{deep:true})


</script>

<style lang="less" scoped>
.back{
    margin-top: 1rem;
    .el-button {
        letter-spacing: 0.5rem;
    }
}
.el-table{
    margin-top: 1rem;
    height: 70vh;
}
</style>