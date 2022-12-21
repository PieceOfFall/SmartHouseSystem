<template>
    <div class="smog-container">
        <!-- 面包屑 -->
        <Breadcrumb bread-route="烟雾检测"/>
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
                :data-source="smogData"
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
import { ref,onMounted,nextTick,onBeforeUnmount } from 'vue'
import DatePicker from '../../components/DatePicker.vue';
import { getCurrentData } from '../../api/sensor'
import { SmogData } from '../../api/sensor/types';
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
const smogData = ref<number[]>([])

// 初始化状态数据
onMounted(async()=>{
    setTimeout(()=>{
        window.scrollTo({
        top:1000,
        behavior: 'smooth'
    })
    },200)
    const data:SmogData[] = (await getCurrentData('smog')).data.list
    let nowSecond:number = new Date().getSeconds()-5
    
    data.forEach(e=>{
        timeArray.value.push(nowSecond++)
        smogData.value.push(e.smog)
    })
})

// 每秒更新数据
const updateInterval = setInterval(async()=>{
    const [data]:SmogData[] = (await getCurrentData('smog')).data.list
    timeArray.value.shift()
    timeArray.value.push(new Date().getSeconds())
    smogData.value.shift()
    smogData.value.push(data['smog'])
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
            path: '/smog_detect/query_certain_smog_data', 
            query: { startTime: date[0],endTime: date[1] } 
        })
    })
}

</script>

<style lang="less" scoped>
.smog-container {
        text-align: center;
        padding: 2.5rem;
        height: 100%;
        background-color: #010409;
        font-weight: 300;
    }
</style>