<template>
    <div>
        <el-date-picker
        @change="confirmDate"
        v-model="value"
        :type="type?type:'datetimerange'"
        :shortcuts="shortcuts"
        range-separator="to"
        start-placeholder="开始时间"
        end-placeholder="结束时间"
        />
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
// 确认事件定义
const emit = defineEmits(['change'])

const props = defineProps<{
    // 选择器类型
    type?: "date" | "year" | "month" | "dates" | "week" | "datetime" | "datetimerange" | "daterange" | "monthrange"
}>()

// 日期确认并格式化
function confirmDate(date:[]){
    emit('change',date.map((currentValue:Date,index:number,array)=>{
        return currentValue.getTime()
    }))
}

// 绑定数据
const value = ref<[Date,Date]>()

// 快捷日期
const shortcuts = [
    {
        text: '过去三天',
        value: () => {
          const end = new Date()
          const start = new Date()
          start.setTime(start.getTime() - 3600 * 1000 * 24 * 3)
          return [start, end]
        },
    },
    {
        text: '上一周',
        value: () => {
          const end = new Date()
          const start = new Date()
          start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
          return [start, end]
        },
    },
    {
        text: '上个月',
        value: () => {
          const end = new Date()
          const start = new Date()
          start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
          return [start, end]
        },
    },
    {
        text: '过去三个月',
        value: () => {
          const end = new Date()
          const start = new Date()
          start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
          return [start, end]
        },
    },
]

</script>

<style lang="less" scoped>


</style>