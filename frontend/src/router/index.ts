import {
    createRouter,
    createWebHistory,
    RouteRecordRaw
} from 'vue-router';

const routes: Array < RouteRecordRaw > = [{
    path: '/',
    redirect: '/home'
},{
    path: '/home',
    name: "home",
    component: () => import("../views/Home.vue"),
    children:[{
        path:'/login',
        name:'login',
        component: () => import("../views/Login.vue")
    }]
}]

export const router = createRouter({
    history:createWebHistory(),
    routes,
})
