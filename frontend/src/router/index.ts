import {
    createRouter,
    createWebHistory,
    RouteRecordRaw
} from 'vue-router'

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
        }, {
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
        }]
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