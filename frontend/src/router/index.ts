import {
    Token
} from './../api/login/types';
import {
    createRouter,
    createWebHistory,
    RouteRecordRaw
} from 'vue-router'
import {
    checkLogin
} from '../api/login/index';
import {
    ElMessageBox
} from 'element-plus';

const routes: Array < RouteRecordRaw > = [{
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/',
        redirect: '/homepage'
    }, {
        path: '/home',
        name: "Home",
        redirect: '/homepage',
        component: () => import('../views/Home.vue'),
        children: [{
            path: '/homepage',
            name: 'HomePage',
            component: () => import('../views/HomePage.vue')
        }, 
        /*
           电器
        */
        {
            path: '/light_ctrl',
            name: 'LightCtrl',
            component: () => import('../views/ElectricCtrl/LightCtrl.vue')
        }, {
            path: '/switch_ctrl',
            name: 'SwitchCtrl',
            component: () => import('../views/ElectricCtrl/SwitchCtrl.vue')
        }, {
            path: '/curtain_ctrl',
            name: 'CurtainCtrl',
            component: () => import('../views/ElectricCtrl/CurtainCtrl.vue')
        },
        /*
           传感器
        */
       {
        path: '/gas_detect',
        name: 'GasDetect',
        component: () => import('../views/SecurityCtrl/Gas.vue'),
        children:[{
            path:'query_certain_gas_data',
            name:'QueryCertainGasData',
            component:()=>import('../components/sensor/QueryCertainData.vue'),
        }]
       },
       {
        path: '/smog_detect',
        name: 'SmogDetect',
        component: () => import('../views/SecurityCtrl/Smog.vue'),
        children:[{
            path:'query_certain_smog_data',
            name:'QueryCertainSmogData',
            component:()=>import('../components/sensor/QueryCertainData.vue'),
        }]
       },
       {
        path: '/shake_detect',
        name: 'ShakeDetect',
        component: () => import('../views/SecurityCtrl/Shake.vue'),
        children:[{
            path:'query_certain_shake_data',
            name:'QueryCertainShakeData',
            component:()=>import('../components/sensor/QueryCertainData.vue'),
        }]
       },
       {
        path: '/warn_light',
        name: 'Guard',
        component: () => import('../views/SecurityCtrl/Guard.vue')
       },
    ]
    }
]

export const router = createRouter({
    history: createWebHistory(),
    routes,
    // 刷新时，滚动条位置还原
    scrollBehavior: () => ({
        left: 0,
        top: 0
    })
})

// 登录拦截
router.beforeEach(async (to, from, next) => {
    // 判断是否去登录页
    if (to.name == "Login") {
        next()
    } else {
        // 判断是否第一次登录
        const token: Token | null = localStorage.getItem('authorization')
        if (token === undefined || token === null || token ?.trim() === "") {
            ElMessageBox.confirm('当前页面已失效，请重新登录', '提示', {
                confirmButtonText: 'OK',
                type: 'warning'
            }).then(() => {
                localStorage.clear()
                sessionStorage.clear()
                router.push('/login')
            })
        } else {
            // token存在,校验token
            await checkLogin(token as string)
            /*
               如果token已经过期,封装的request API会检测到403状态码，自动返回登录页面
            */

            // token未过期
            next()
        }
    }

});