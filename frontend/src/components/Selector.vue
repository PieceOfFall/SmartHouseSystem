<template>
  <div>
    <el-select 
    v-model="value" 
    @change="changeSelect"
    placeholder="传感器类型"  >
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled"
      />
    </el-select>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

// 当前选项
const value = ref('')

// emit方法声明
const emit = defineEmits(['change'])

// 选项卡
const options = [
  {
    value: 'gas',
    label: '燃气',
    disabled: false,
  },
  {
    value: 'smog',
    label: '烟雾',
    disabled: false,
  },
  {
    value: 'shake',
    label: '震动',
    disabled: false,
  }
]

// 选项变动
function changeSelect(selectItem:string) {
  options.forEach(e => {
    if(e.value === selectItem) {
      e.disabled = true
      emit('change',e.value)
    } else {
      e.disabled = false
    }
  });
}
</script>

<style lang="less" scoped>
  .el-select {
    width: 7.5rem;
  }
</style>