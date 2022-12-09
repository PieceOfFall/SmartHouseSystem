<template>
    <div class="curtain-container">
        <!-- 面包屑 -->
        <Breadcrumb bread-route="窗帘控制"/>
        
        <!-- 窗帘控制 -->
        <el-row :gutter="20" justify="space-evenly">
            <el-col :span="6">
                <el-icon size="5rem" :class="''" id="curtain-left"><DArrowLeft /></el-icon>
            </el-col>
            <el-col :span="6">
                <el-icon size="5rem" ><DArrowRight /></el-icon>
            </el-col>
        </el-row>

    </div>
</template>

<script setup lang="ts">
import {DArrowLeft,DArrowRight} from '@element-plus/icons-vue'

</script>

<style lang="less" scoped>
    .curtain-container {
        padding: 2.5rem;
        height: 100%;
        background-color: #010409;
        font-weight: 300;
        .el-row{
            margin-top: 5rem;

        }
    }
</style>