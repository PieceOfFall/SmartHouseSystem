<template>
    <div id="switch-container">
        <!-- 面包屑 -->
        <Breadcrumb bread-route="开关控制"/>
        <div class="main-ctrl-box">
            <div @click="toggleAll">
                <el-icon size="5rem" :color="isOneSwitchOnExist?'green':'#f34c4a'">
                <SwitchButton/>      
            </el-icon>
            <div id="main-switch">
                总 开 关
            </div>
            </div>
            
            <div class="card-box">
                <el-row :gutter="20" justify="space-evenly">
                    <!-- 卡片 -->
                    <el-col :span="6" v-for = "(value,prop) in switchState" :key="prop">
                        <el-card>
                            <!-- 每张卡片 -->
                            <el-row :gutter="20" justify="space-between">
                                <!-- 开关名 -->
                                <el-col :span="8">
                                    <span>开关 {{prop[prop.length-1]}}</span>
                                </el-col>
                                <!-- 每个开关 -->
                                <el-col :span="7">
                                    <el-switch
                                        size="large"
                                        v-model="(switchState[prop])"
                                        class="ml-2"
                                        style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
                                    />
                                </el-col>
                            </el-row>
                        </el-card>
                    </el-col>
                </el-row>
            </div>
        </div>
        
    </div>


</template>

<script setup lang="ts">
import {SwitchButton} from '@element-plus/icons-vue'
import {getSwitches,setAppliance,toggleAllSwitch} from '../../api/electric/index'
import {SwitchState,SubmitSwitchState,ElectricAppliance} from '../../api/electric/types';
import { onMounted,ref,watch } from 'vue';

let isOneSwitchOnExist = ref(false);
const switchState = ref<SwitchState>({
    switchA: false,
    switchB: false,
    switchC: false
})

 async function getRenderAllSwitches() {
    switchState.value = await (await getSwitches()).data
    if(switchState.value.switchA||switchState.value.switchB||switchState.value.switchC) {
        isOneSwitchOnExist.value = true
    } else{
        isOneSwitchOnExist.value = false
    }
 }


/*
   初始化所有开关数据
*/
onMounted(async ()=>{
    getRenderAllSwitches()
})

/*
   总开关控制
*/
let toggleAllTimer:number; // 总开关防抖定时器序号
async function toggleAll() {
    if(toggleAllTimer) {
        clearTimeout(toggleAllTimer)
    }
    toggleAllTimer = setTimeout(async()=>{
        // 调用接口提交数据
        await toggleAllSwitch(isOneSwitchOnExist.value)
        getRenderAllSwitches()
    },700)
}

/*
   按钮状态改变时，提交最新改变
*/
let submitState:SubmitSwitchState = {
    switchA:0,
    switchB:0,
    switchC:0
}

let timer:number; // 定时器序号
let isSwitchInit:boolean = false;
watch(switchState,async ()=>{
    if(!isSwitchInit){
        isSwitchInit = true;
        return
    }
    // 防抖
    if(timer) {
        clearTimeout(timer)
    }
    timer = setTimeout(async()=>{
        // 调用接口提交数据
        submitState = {
            switchA:switchState.value.switchA === false?0:1,
            switchB:switchState.value.switchB === false?0:1,
            switchC:switchState.value.switchC === false?0:1
        }   
        await setAppliance({...submitState as ElectricAppliance}).then(()=>{
            if(switchState.value.switchA||switchState.value.switchB||switchState.value.switchC) {
                isOneSwitchOnExist.value = true
            } else {
                isOneSwitchOnExist.value = false
            }
        })
    },700)

},{deep:true})

</script>

<style lang="less" scoped>
    #switch-container {
        padding: 2.5rem;
        height: 100%;
        background-color: #010409;
        font-weight: 300;
        .main-ctrl-box{
            text-align: center;
            font-size: larger;
            .el-icon{
                cursor: pointer;
                margin-top: 0.8rem;
                padding: 10px;
                background-color: rgba(221, 221, 221, 0.067);
                border-radius: 15%;
            }
            .el-icon:hover{
                cursor: pointer;
                margin-top: 0.8rem;
                padding: 10px;
                background-color: #1d1e1f;
                border-radius: 15%;
            }

            #main-switch{
                margin-top: 1.5rem;
                margin-bottom: 1.5rem;
            }
            .card-box{
                .el-card{
                    text-align:initial;
                    font-size: large;
                }
            }
        }
    }
</style>