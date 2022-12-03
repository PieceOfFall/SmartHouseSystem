import {
    createApp
} from 'vue'
import {
    createPinia
} from 'pinia'
import {
    router
} from './router/index';
import axios from '../src/utils/axiosInstance';
import App from './App.vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const pinia = createPinia()

const app = createApp(App)
app.config.globalProperties.$axios = axios
app.use(ElementPlus)
    .use(pinia)
    .use(router)
    .mount('#app')