<template>
    <div class="light-container">
        <!-- 面包屑 -->
        <el-breadcrumb :separator-icon="ArrowRight">
            <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
            <el-breadcrumb-item>灯光控制</el-breadcrumb-item>
        </el-breadcrumb>

        <!-- 分割线 -->
        <el-divider/>

        <!-- 卡片 -->
        <el-card>
            <!-- 控制容器 -->
            <el-row :gutter="10" justify="space-around">
                <el-col :span="3" v-for="(value,prop) in lights" :key="prop">
                    <div class="control-box">
                        <!-- 控制项 -->
                        <div v-if="prop==='lightBedA'">
                            主卧灯光
                        </div>
                        <div v-else-if="prop==='lightBedB'">
                            次卧灯光
                        </div>
                        <div v-else-if="prop==='lightLivingRoom'">
                            客厅灯光
                        </div>
                        <div v-else-if="prop==='lightBathroom'">
                            浴室灯光
                        </div>

                            <!-- 图标 -->
                            <div v-if="(value===0)">
                                <el-icon size="5rem" color="grey">
                                    <Moon/>
                                </el-icon>
                            </div>

                            <div v-if="(value===1)">
                                <el-icon size="5rem" color="#fff799">
                                    <Cloudy/>
                                </el-icon>
                            </div>

                            <div v-if="(value===2)">
                                <el-icon size="5rem" color="#ffee6f">
                                    <Sunrise/>
                                </el-icon>
                            </div>

                            <div v-if="(value===3)">
                                <el-icon size="5rem" color="#ecd452">
                                    <Sunny/>
                                </el-icon>
                            </div>
                        
                        <!-- 控制条 -->
                        <el-slider
                        v-model="lights[prop]"
                        :max="3" 
                        :step="1"/>
                    </div>
                </el-col>
            </el-row>
            

        </el-card>


    </div>
</template>

<script setup lang="ts">
import { ArrowRight,Moon,Cloudy,Sunrise,Sunny } from '@element-plus/icons-vue'
import {getLights,setAppliance} from '../../api/Electric/index';
import {LightsState,ElectricAppliance} from '../../api/Electric/types';
import { onMounted, ref, watch } from 'vue';

/*
   获取所有灯光，并渲染
*/
let lights = ref<LightsState>({
    lightBedA:0,
    lightBedB:0,
    lightLivingRoom:0,
    lightBathroom:0
})
onMounted(async()=>{
    lights.value = await (await getLights()).data
})

/*
   控制灯光，延迟同步
*/
// 上次切换时间
let switchTime:number = new Date().getTime()
// 本次点击时的时间
let thisTime:number;
watch(lights,async ()=>{
    
    // 防抖
    thisTime = new Date().getTime() // 进入监听时更新本次点击时间
    if(thisTime-switchTime<1000) {  // 如果距离上次提交时间不到1秒，则直接返回
        return
    } 
    // 调用接口提交数据
    await setAppliance(lights.value as ElectricAppliance)
    switchTime = new Date().getTime() // 数据提交完成，更新切换时间
},
// 开启深度监听
{deep:true})






</script>

<style lang="less" scoped>

.light-container {
    padding: 2.5rem;
    height: 100%;
    background-color: #010409;
    font-weight: 300;
    .control-box {
        text-align: center;
        line-height: 4rem;
    }

}


</style>