<template>
    <div>
        <!-- 数据显示 -->
        <div @click="queryOrBack" class="back"><el-button :icon="Search">上一步</el-button></div>
        <el-table 
        stripe 
        :data="renderDataList"
        v-loading="loading">
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
import { onMounted, ref,watch } from 'vue';
import {LocationQuery, useRouter} from 'vue-router';
import { SensorData,queryType,sensorType} from '../../api/sensor/types';
import {getCertainAbnormal} from '../../api/sensor/index';

// 渲染对象类型
interface RenderData {
    time:string
}
// 查询结果
const renderDataList = ref<RenderData[]>([])
// 当前数据查询间隔
const currentGap = ref<queryType>('d')
// 当前查询的传感器类型
let querySensorType: sensorType
// 路由变量
const router = useRouter()
// 等待加载
const loading = ref(true)

// 根据开始时间和时间间隔获取并渲染异常数据
async function getAndRenderByGapAndStartTime() {
    loading.value = true
    const query:LocationQuery = router.currentRoute.value.query
    renderDataList.value = await (await getCertainAbnormal(
        query.sensorType as sensorType,
        parseInt(query.startTime as string),
        60,
        1,
        currentGap.value
        )).data
        .list.map((currentValue:SensorData,index:number,array)=>{
            const date = new Date(currentValue.time)
            return {
                time: `${date.getFullYear()}年${date.getMonth()+1}月${date.getDate()}日  ${date.getHours()}:${date.getMinutes()<=9?'0'+date.getMinutes():date.getMinutes()}:${date.getSeconds()<=9?'0'+date.getSeconds():date.getSeconds()}`,
            }
        })
        loading.value = false
}

//获取到待查询的传感器类型和时间范围,执行查询
onMounted( async()=>{
    await getAndRenderByGapAndStartTime()
})

// 获取详情,并跳转
async function getInfo(index: number, row: RenderData){
    const query1 = router.currentRoute.value.query
    switch (currentGap.value) {
        case 'd':{
            currentGap.value = 'h'
            router.push({path:'/warn_light/abnormal_certain_data',
            query:{
                startTime:query1.startTime,
                sensorType:query1.sensorType,
                queryType:'h'
            }})
            break;
        }
        case 'h':{
            currentGap.value = 'm'
            router.push({path:'/warn_light/abnormal_certain_data',
            query:{
                startTime:query1.startTime,
                sensorType:query1.sensorType,
                queryType:'m'
            }})
            break;
        }
        case 'm':{
            currentGap.value = 's'
            router.push({path:'/warn_light/abnormal_certain_data',
            query:{
                startTime:query1.startTime,
                sensorType:query1.sensorType,
                queryType:'s'
            }})            
            break;
        }
    }

// 路由变量
const query = ref(router)
// 通过监测路由query变化,请求所需数据
const unwatch = watch(query.value,async()=>{
    if(router.currentRoute.value.fullPath.match('abnormal_certain_data')) {
        await getAndRenderByGapAndStartTime()      
        return
    }
    unwatch()
    return
  },{deep:true})
}

// 回退按钮
async function queryOrBack() {
    const query1 = router.currentRoute.value.query
    switch (currentGap.value) {
        case 's':
        {
        currentGap.value = 'm'
        router.push({path:'/warn_light/abnormal_certain_data',
        query:{
            startTime:query1.startTime,
            sensorType:query1.sensorType,
            queryType:'m'
        }})    
        break;
        }

        case 'm':
        {
        currentGap.value = 'h'
        router.push({path:'/warn_light/abnormal_certain_data',
        query:{
            startTime:query1.startTime,
            sensorType:query1.sensorType,
            queryType:'h'
        }})
        break;
        }


        case 'h':
        {
        currentGap.value = 'd'
        router.push({path:'/warn_light/abnormal_certain_data',
        query:{
            startTime:query1.startTime,
            sensorType:query1.sensorType,
            queryType:'d'
        }})
        break;
        }

        case 'd':
        router.push({
        path:'/warn_light/query_abnormal_data',
        query:{
            sensorType: router.currentRoute.value.query.sensorType
        }})    

    }
}

</script>

<style lang="less" scoped>

.el-table{
    margin-top: 1rem;
    height: 70vh;
}

</style>