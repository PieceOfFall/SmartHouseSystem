import { createApp } from 'vue'
import './style.css'
import {router} from './router/index';
import App from './App.vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'


createApp(App)
.use(ElementPlus)
.use(router)
.mount('#app')
