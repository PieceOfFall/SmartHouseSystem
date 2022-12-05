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

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/dark/css-vars.css'

const pinia = createPinia()

const app = createApp(App)

app.use(ElementPlus)
    .use(pinia)
    .use(router)
    .mount('#app')