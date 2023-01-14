<template>
    <div id="guard-container">
        <!-- 面包屑 -->
        <Breadcrumb bread-route="警鸣灯光"/>
        <!-- 标签页 -->
        <el-tabs
        v-model="activeName"
        type="card"
        >
            <!-- 标签 -->
            <el-tab-pane label="家 庭 守 卫" name="first">
                <span>
                    <div id="desc-box">
                        安防 就像是雨伞，平时没有什么用，但下雨的时候就变得很重要了
                        <br/>
                        <b>现在，我就是你的雨伞</b>
                    </div>
                </span>
                <!-- 家庭守卫 -->
                <FamilyManager/>
            </el-tab-pane>

            <el-tab-pane label="异 常 查 询" name="second">  

                <el-row justify="center">
                        <!-- 传感器选择器 -->
                        <Selector
                        @change="getQuerySensor"
                        />
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
import FamilyManager from '../../components/security-control/FamilyManager.vue';

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
    #guard-container {
        text-align: center;
        padding: 2.5rem;
        height: 100%;
        background-color: #010409;
        font-weight: 300;
        .el-tabs{
            span {
                text-align: start;
                font-style: italic;
                user-select: none;
                letter-spacing: 2px;
                color: rgba(169, 169, 169, 0.691);
                font-size: 15px;
                #desc-box {
                    margin: 0 0 1rem 2rem;
                    b{
                        margin-left: 1rem;
                        color: darkgray;
                    }
                }
            }
            .el-row{
                margin-bottom: 1rem;
            }
        }
    }
</style>