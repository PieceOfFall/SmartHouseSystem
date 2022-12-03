<template>
    <div class="main-container">
        <el-container>
            <!-- 顶部 -->
            <el-header height="70px">
                <Header />
            </el-header>

            <el-container>
                <!-- 侧边栏 -->
                <el-aside :width='hasCollapse?"64px":"198px"'>
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
        defineComponent,
        onMounted,
        ref,
        watch
    } from 'vue'
    import useAsideStore from '../store';
    import {
        storeToRefs
    } from 'pinia';

    export default defineComponent({
        components: {
            Aside,
            Header
        },
        setup() {
            // pinia
            const store = useAsideStore().aside
            const {
                isStoreCollapse
            } = storeToRefs(store)

            // 等待侧边栏收起，主体再向左靠拢
            const hasCollapse = ref(false)
            onMounted(()=>{
                hasCollapse.value = isStoreCollapse.value
            })
            watch(isStoreCollapse,()=>{
                if(isStoreCollapse.value) {
                    setTimeout(()=>{
                        hasCollapse.value = true
                    },300)
                } else{
                    hasCollapse.value = false
                }
            })

            return {
                hasCollapse
            }
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
                user-select: none;
            }

            :deep(.el-aside) {
                overflow-x: hidden;
                user-select: none;
            }

            :deep(.el-main) {
                padding: 0;
            }
        }
    }
</style>