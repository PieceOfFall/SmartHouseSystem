<template>
    <div>
        <!-- 数据显示 -->
        <el-table 
        stripe 
        :data="renderDataList"
        v-loading="loading">
            <el-table-column prop="startTime" :label="`异常开始时间`" />
            <el-table-column label="操作">
                <template #default="scope">
                  <el-button size="small" @click="JumpToCertain(scope.$index, scope.row)"
                    >详情</el-button
                  >
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script setup lang="ts">
import {getAbnormalByDifference} from '../../api/security/index';
import {sensorType} from '../../api/security/types';
import { onMounted,watch,ref } from 'vue';
import {Router, useRouter} from 'vue-router'
const router = useRouter()

/*
   查询并渲染异常开始时间表格
*/
interface RenderData {
    startTime:string,
    timeStamp:number
}
// 渲染数据
const renderDataList = ref<RenderData[]>([])
// 等待加载
const loading = ref(true)
// 页面初始化时从query中读取参数
onMounted(async ()=>{
    await getAndRenderAbnormals(router.currentRoute.value.query.sensorType as string)
})

// 页面query参数变动
const query = ref<Router>(router)
const unwatch = watch(query,async()=>{
    if(router.currentRoute.value.fullPath.match('query_abnormal_data')) {
        await getAndRenderAbnormals(router.currentRoute.value.query.sensorType as string)
        return
    }
    unwatch()
    return
},{
    deep:true
})

// 根据传感器类型查询并渲染异常开始时间表
async function getAndRenderAbnormals(sensorType:string) {
    loading.value = true
    renderDataList.value = await (await getAbnormalByDifference(sensorType as sensorType))
    .data.map((currentValue:number,index:number,array)=>{
        let date = new Date(currentValue*1000)
        return {
            startTime: `${date.getFullYear()}年${date.getMonth()+1}月${date.getDate()}日 
                ${date.getHours()}:
                ${date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes()}:
                ${date.getSeconds()<10?'0'+date.getSeconds():date.getSeconds()}`,
            timeStamp: currentValue
        }
    })
    loading.value = false
}

// 跳转到异常详情
async function JumpToCertain(index: number, row: RenderData) {
    router.push({
        path:'/warn_light/abnormal_certain_data',
        query: {
            startTime: row.timeStamp,
            sensorType: `${router.currentRoute.value.query?.sensorType}`,
            queryType: 'd'
        }
    })
}
    
</script>

<style lang="less" scoped>
.el-table{
    margin-top: 1rem;
    height: 70vh;
}
</style>