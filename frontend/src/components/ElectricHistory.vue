<template>
    <div id="history-container">
        
        <el-timeline>
            <span>操作记录：</span>
            <el-timeline-item
            v-for="(activity, index) in operationHistoryRender"
            :key="index"
            :color="activity.color"
            :timestamp="activity.time"
            >
                <div v-if="activity.electricType === '灯光'">
                    {{ activity.electricId.substring(-1) }}灯光 设置 {{ activity.operationType }}
                </div>
                <div v-else>
                    {{ activity.electricId }}{{ activity.electricType }} 设置 {{ activity.operationType }}
                </div>
            </el-timeline-item>
        </el-timeline>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, computed } from 'vue';
import { getUserLatestHistory } from '../api/user/index';
import { OperationHistory, OperationHistoryRender } from '../api/user/types';

/*
   获取用户操作历史
*/
// 获取历史数据
const userOperationHistory = ref<OperationHistory[]>([])
onMounted(async()=>{
    userOperationHistory.value = await (await getUserLatestHistory()).data.list
    
})

// 历史数据渲染
const operationHistoryRender = computed(()=>{
    return userOperationHistory.value.map((currentValue:OperationHistory,index:number,array):OperationHistoryRender=>{
        const state = mapOperationState(currentValue)
        return {
            ...state
        }
    })
})

// 源数据映射为渲染数据
function mapOperationState(data:OperationHistory){
    const state:OperationHistoryRender = {
        time:'',
        electricType: '',
        operationType: '',
        electricId: '',
        color: ''
    }
    const date = new Date(data.time*1000)
    state.time = `${date.getFullYear()}年${date.getMonth()+1}月${date.getDate()}日  ${date.getHours()}:${date.getMinutes()<=9?'0'+date.getMinutes():date.getMinutes()}:${date.getSeconds()<=9?'0'+date.getSeconds():date.getSeconds()}`
    switch(data.electricType) {
        case 'light': {
            state.electricType = '灯光'
            state.electricId = data.electricId.split('light')[1]
            if (data.operationType === 'close') {
                state.operationType = '关闭'
            } else if(data.operationType === 'small'){
                state.operationType = '较小'
            } else if(data.operationType === 'big') {
                state.operationType = '正常'
            } else {
                state.operationType = '全开'
            }
            state.color = 'yellow'
            break;
        }
        default:{
            data.electricType === 'switch'?state.electricType = '开关':state.electricType = '窗帘'
            state.electricId = data.electricId.substr(-1)
            state.operationType = data.operationType=== 'on'?'开':'关'
            data.electricType === 'switch'?state.color = 'lightgreen':state.color = 'cyan'
        }
    }
    return state
}
</script>

<style lang="less" scoped>
#history-container {
    line-height: 1.3rem;
    margin-top: 1.5rem;
    .timeline {
        span{
            padding: 1rem;
        }
        
    }
    div {
            color: rgba(240, 248, 255, 0.697);
            font-family: 'SimHei';
    }
}

</style>