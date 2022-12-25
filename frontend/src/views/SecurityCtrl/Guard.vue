<template>
    <div class="guard-container">
        <!-- 面包屑 -->
        <Breadcrumb bread-route="警鸣灯光"/>
        <!-- 标签页 -->
        <el-tabs
        v-model="activeName"
        type="card"
        >
            <!-- 标签 -->
            <el-tab-pane label="家 庭 守 卫" name="first">
                家庭守卫
            </el-tab-pane>

            <el-tab-pane label="异 常 查 询" name="second">  

                <el-row justify="center">
                    <el-col :span="2">
                        <!-- 日期选择器 -->
                        <!-- <DatePicker
                        @change = "confirmDate"
                        /> -->
                    </el-col>
                    <el-col :span="7">
                        <!-- 传感器选择器 -->
                        <Selector
                        @change="getQuerySensor"
                        />
                    </el-col>
                </el-row>
                <!-- 表格 -->
                <router-view/>

            </el-tab-pane>

        </el-tabs>
    </div>
</template>

<script setup lang="ts">
import { ref,watch } from 'vue'
import Selector from '../../components/Selector.vue';
import {useRouter} from 'vue-router'

/*
   标签页
*/
const activeName = ref('first') // 默认激活

/*
   历史异常查询
*/
// 查询参数
const submitSensorType = ref<'gas'|'smog'|'shake'|''>('')

// 传感器种类参数获取
async function getQuerySensor(sensorType:'gas'|'smog'|'shake'|'') {
    submitSensorType.value = sensorType
} 

const router = useRouter()
// 提交查询
watch(submitSensorType,async()=>{
    if(submitSensorType.value === '') {
        return
    }
    router.push({
        path:'/warn_light/query_abnormal_data',
        query:{
            sensorType: submitSensorType.value
        }
    })
})
</script>

<style lang="less" scoped>
    .guard-container {
        text-align: center;
        padding: 2.5rem;
        height: 100%;
        background-color: #010409;
        font-weight: 300;
        .el-tabs{
            .el-row{
                margin-bottom: 1rem;
            }
        }
    }
</style>