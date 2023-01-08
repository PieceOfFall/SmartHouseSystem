<template>
    <div id="chart-container">
        <v-chart class="chart" :option="option" />
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import VChart from 'vue-echarts';

const props = defineProps<{
    barName?:string,          // 柱状图名称
    lineName?:string,         // 折线图名称
    xAxis:number[]|string[],  // x轴
    yAxisBar?:{ min:number,max:number,interval:number }, // 柱状图y轴
    yAxisLine?:{ min:number,max:number,interval:number },// 折线图y轴(min,max)
    dataSource?:{ bar:number[],line:number[] } // 数据源
}>()

// echarts
const option = ref({
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'cross',
      crossStyle: {
        color: '#999'
      }
    }
  },
  toolbox: {
    feature: {
      dataView: { show: true, readOnly: false },
      magicType: { show: true, type: ['line', 'bar'] },
      restore: { show: true },
      saveAsImage: { show: true }
    }
  },
  legend: {
    data: [props.barName,props.lineName]
  },
  xAxis: [
    {
      type: 'category',
      data: props.xAxis,
      axisPointer: {
        type: 'shadow'
      }
    }
  ],
  yAxis: [
    {
      type: 'value',
      name: props.barName,
      min: props.yAxisBar?.min,
      max: props.yAxisBar?.max,
      interval: props.yAxisBar?.interval,
      axisLabel: {
        formatter: '{value}% '
      }
    },
    {
      type: 'value',
      name: props.lineName,
      min: props.yAxisLine?.min,
      max: props.yAxisLine?.max,
      interval: props.yAxisLine?.interval,
      axisLabel: {
        formatter: '{value} °C'
      }
    }
  ],
  series: [
    {
      name: props.barName,
      type: 'bar',
      tooltip: {
        valueFormatter: function (value:number) {
          return value as number;
        }
      },
      data: props.dataSource?.bar
    },
    {
      name: props.lineName,
      type: 'line',
      color:'#ef845d',
      yAxisIndex: 1,
      tooltip: {
        valueFormatter: function (value:number) {
          return value as number + ' °C';
        }
      },
      data: props.dataSource?.line
    }
  ]
})

</script>

<style lang="less" scoped>
    #chart-container {
    height: 100%;
    .chart {
      height: 80vh;
      width: 60vw;
    }
  }
</style>