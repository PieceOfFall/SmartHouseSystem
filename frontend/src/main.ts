import {
    createApp
} from 'vue'
import {
    createPinia
} from 'pinia'
import {
    router
} from './router/index';

import App from './App.vue'

import 'echarts'
import ECharts from 'vue-echarts'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/dark/css-vars.css'
import 'animate.css';


const pinia = createPinia()

const app = createApp(App)

app .component('VueEcharts', ECharts)
    .use(ElementPlus)
    .use(pinia)
    .use(router)
    .mount('#app')