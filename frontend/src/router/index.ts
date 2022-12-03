import {
    createRouter,
    createWebHistory,
    RouteRecordRaw
} from 'vue-router';

const routes: Array < RouteRecordRaw > = [{
    path: '/',
    redirect: '/home'
}, {
    path: '/home',
    name: "Home",
    redirect: '/homepage',
    component: () => import('../views/Home.vue'),
    children: [{
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    }, {
        path: '/homepage',
        name: 'HomePage',
        component: () => import('../views/HomePage.vue')
    }, {
        path: '/light_ctrl',
        name: 'LightCtrl',
        component: ()=> import('../views/LightCtrl.vue')
    }]
}]

export const router = createRouter({
    history: createWebHistory(),
    routes,
})