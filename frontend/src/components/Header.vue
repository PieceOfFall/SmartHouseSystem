<template>
    <div class="header-container">
        <!-- 标题 -->
        <span>539 <b>智</b> 能家居</span>
    </div>
</template>

<script lang="ts">
    import {
        defineComponent
    } from 'vue'

    export default defineComponent({
        setup() {


            return {}
        }
    })
</script>

<style lang="less" scoped>
    .header-container {

        box-sizing: border-box;
        letter-spacing: 0.2rem;
        height: 100%;
        padding: 1.5rem 1rem;
        background-color: #141414;
        span {
            font-family: 'STHeiti';

            margin-left: 1rem;
            color: gainsboro;

            b {
                font-size: large;
                font-family: 'STXingkai';
                box-shadow: 0 0 1.1rem white;
                padding: 5px;
                border-radius: 50%;
            }

        }

    }
</style>