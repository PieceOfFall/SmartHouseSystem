<template>
    <div class="chart-container">
        <v-chart class="chart" :option="option" />
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import VChart from 'vue-echarts';


const props = defineProps<{
    xName?:string,
    yName?:string,
    xArray:string[],
    dataSource:number[]
}>()

const option = ref({
  xAxis: {
    type: 'category',
    data: props.xArray,
    name: props.xName
  },
  yAxis: {
    type: 'value',
    name:props.yName
  },
  series: [
    {
        animation:true,
        animationDuration:800,
        animationEasing:'quarticIn',
        color:['#559eff'],
        data: props.dataSource,
        type: 'line'
    }
  ]
})
</script>

<style lang="less" scoped>
.chart-container{
    height: 100%;
}

.chart {
  height: 80%;
}
</style>