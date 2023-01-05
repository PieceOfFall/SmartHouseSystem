<template>
    <div class="curtain-container">
        <!-- 面包屑 -->
        <Breadcrumb bread-route="窗帘控制"/>
        
        <!-- 窗帘总状态 -->
        <el-icon size="5rem">
              <Sunny v-if="atLeastOne" class="animate__animated animate__fadeIn animate__fast"/>
              <Moon v-else class="animate__animated animate__fadeIn animate__fast"/>
        </el-icon>

        <!-- 窗帘控制 -->
        <el-row :gutter="20" justify="space-evenly">
            <el-col :span="6">
                <div class="switch-title">
                    窗帘A
                </div>
                <el-select :suffix-icon="Refresh" v-model="curtainAValue" size="large">
                    <el-option
                        v-for="item in curtainAOption"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    />
                </el-select>
            </el-col>
            <el-col :span="6">
                <div class="switch-title">
                    窗帘B
                </div>
                <el-select :suffix-icon="Refresh" v-model="curtainBValue"  size="large">
                    <el-option
                        v-for="item in curtainAOption"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    />
                </el-select>
            </el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import { ref,onMounted,watch } from 'vue';
import { Refresh,Sunny,Moon } from '@element-plus/icons-vue'
import {getCurtains,setAppliance} from '../../api/electric/index';
import {CurtainState,ElectricAppliance} from '../../api/electric/types';

/*
   页面加载时请求并渲染窗帘状态
*/
const curtainAValue = ref('')
const curtainBValue = ref('')
const atLeastOne = ref(true)
const curtainAOption = [
  {
    value: 0,
    label: '关',
  },
  {
    value: 1,
    label: '开',
  }
]

const curtainBOption = [
  {
    value: 0,
    label: '关',
  },
  {
    value: 1,
    label: '开',
  }
]

/*
   初始化获取窗帘数据
*/
onMounted(async()=>{
    const responseState:CurtainState = await (await getCurtains()).data

    atLeastOne.value = ((responseState.curtainA+responseState.curtainB)>0) ? true : false

    curtainAValue.value = responseState.curtainA ===0? '关' : '开'
    curtainBValue.value = responseState.curtainB ===0? '关' : '开'
})

/*
   状态变化时提交窗帘数据改动
*/
let isAInitial:boolean = false;
// 窗帘A
watch(curtainAValue,async()=>{
    if(!isAInitial){
        isAInitial = true;
        return
    }
    if(typeof curtainAValue.value ==="string") {
        return
    }
    if(typeof curtainBValue.value ==="string") {
        const curtainB:number=curtainBValue.value === '开'? 1 : 0
        const curtainA:number = curtainAValue.value
        await setAppliance({
            curtainA,
            curtainB
        } as ElectricAppliance).then(()=>{
            atLeastOne.value = ((curtainA+curtainB)>0) ? true : false
        })
    } else {
        const curtainA:number = curtainAValue.value
        const curtainB:number=curtainBValue.value
        await setAppliance({
            curtainA,
            curtainB
        } as ElectricAppliance).then(()=>{
            atLeastOne.value = ((curtainA+curtainB)>0) ? true : false
        })
    }
    
})

//窗帘B
let isBInitial:boolean = false;
watch(curtainBValue,async()=>{
    if(!isBInitial){
        isBInitial = true;
        return
    }
    if(typeof curtainBValue.value ==="string") {
        return
    }
    if(typeof curtainAValue.value ==="string") {
        const curtainA:number=curtainAValue.value==='开'? 1 : 0
        const curtainB:number = curtainBValue.value
        await setAppliance({
            curtainA,
            curtainB
        } as ElectricAppliance).then(()=>{
            atLeastOne.value = ((curtainA+curtainB)>0) ? true : false
        })
    } else {
        const curtainA:number = curtainAValue.value
        const curtainB:number = curtainBValue.value
        await setAppliance({
            curtainA,
            curtainB
        } as ElectricAppliance).then(()=>{
            atLeastOne.value = ((curtainA+curtainB)>0) ? true : false
        })
    }
})

</script>

<style lang="less" scoped>
    .curtain-container {
        text-align: center;
        padding: 2.5rem;
        height: 100%;
        background-color: #010409;
        font-weight: 300;
        .el-icon{
            margin-top: 1rem;
            border-color: rgba(169, 169, 169, 0.074);
            border-style: solid;
            border-radius: 20%;
            padding: 0.5rem;
        }
        .el-row{
            margin-top: 5rem;
            .switch-title{
                letter-spacing: 0.5rem;
                user-select: none;
                margin-bottom: 1rem;
                font-size: large;
            }

        }
    }
</style>