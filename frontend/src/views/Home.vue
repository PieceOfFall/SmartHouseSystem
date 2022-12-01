<template>
    <div class="main-container">
        <el-container>
            <!-- 顶部 -->
            <el-header height="70px">
                <Header />
            </el-header>

            <el-container>
                <!-- 侧边栏 -->
                <el-aside width="198px">
                    <Aside />
                </el-aside>
                <!-- 主体 -->
                <el-main>
                    <router-view key="1"></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script lang="ts">
    import Aside from '../components/Aside.vue';
    import Header from '../components/Header.vue';
    import {
        defineComponent
    } from 'vue'

    export default defineComponent({
        components: {
            Aside,
            Header
        },
        setup() {

            return {}
        }
    })
</script>

<style lang="less" scoped>
    .main-container {
        width: 100%;
        height: 100vh;
        background-color: #3d4043;

        .el-container {
            height: 100%;
            :deep(.el-header) {
                padding: 0;
            }
            :deep(.el-aside) {
                overflow-x: hidden;
            }
        }
    }
</style>