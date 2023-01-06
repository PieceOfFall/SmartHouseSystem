<template>
    <div id="mode-container">
        <!-- 面包屑 -->
        <Breadcrumb bread-route="场景切换"/>
        <el-row justify="space-evenly">
            <el-col :span="8">
                <!-- 回家 -->
                <el-card>
                    <template #header>
                        <div class="card-header">
                          <span>回 家 模 式</span>
                        </div>
                    </template>

                    <el-icon size="8rem">
                        <HomeFilled class="change_mode animate__animated 
                        animate__fadeIn animate__faster"/>
                    </el-icon>
                </el-card>
            </el-col>

            <el-col :span="8">
                <!-- 离家 -->
                <el-card>
                    <template #header>
                        <div class="card-header">
                          <span>离 家 模 式</span>
                        </div>
                    </template>

                    <el-icon size="8rem">
                        <Promotion class="change_mode animate__animated 
                        animate__fadeIn animate__faster"/>
                    </el-icon>
                </el-card>
            </el-col>

        </el-row>
    </div>
</template>

<script setup lang="ts">
import { HomeFilled,Promotion } from '@element-plus/icons-vue'

</script>

<style lang="less" scoped>
#mode-container{
    text-align: center;
    padding: 2.5rem;
    height: 100%;
    background-color: #010409;
    font-weight: 300;
    .el-row{
        margin-top: 8rem;
        .el-card {
        user-select: none;
        width:300px;
            .change_mode {
                color:gray;
            }
            .change_mode:hover {
                color:lightgray;
                cursor: pointer;
            }
            .change_mode:active {
                color:gray;
                cursor: pointer;
                width: 100px;
            }
        }
    }
}
</style>