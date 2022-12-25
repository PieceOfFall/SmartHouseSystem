<template>
    <div>
        <el-date-picker
        v-model="value"
        @change="confirmDate"
        type="date"
        placeholder="开始时间"
        />
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

/*
   选择单个日期
*/
// 接收选择结果
const value = ref<Date>()
// 定义emit
const emit = defineEmits(['change'])
// 确认选择
function confirmDate(date:Date) {
    emit('change',date.getTime())
}

</script>

<style lang="less" scoped>
    
</style>