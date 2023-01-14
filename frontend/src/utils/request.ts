import {
  router
} from './../router/index';
import {
  Token
} from './../api/login/types';
import axios, {
  AxiosRequestConfig,
  AxiosResponse
} from 'axios';
import {
  ElMessage,
  ElMessageBox
} from 'element-plus';

// 设置允许跨域
axios.defaults.withCredentials = true;

// 创建 axios 实例
const service = axios.create({
  baseURL: "http://localhost:8081",
  timeout: 50000,
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  },
});

// 是否已经显示过期提醒
let isShowTip = false

// 请求拦截器
service.interceptors.request.use(
  (config: AxiosRequestConfig) => {

    if (!config.headers) {
      throw new Error(
        `Expected 'config' and 'config.headers' not to be undefined`
      )
    }
    if (config.url ?.indexOf('/smart_house') != -1) {
      return config
    } else {
      const token: Token = localStorage.getItem("authorization") as string
      config.headers.Authorization = token
      return config
    }

  },
  (error: any) => {
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse) => {
    if(response.data.status === 401) {
      logOut()
    }
    
    return response.data;
  },
  (error: any) => {
    console.log(error);
      if(error && error.code === "ERR_NETWORK") {
        ElMessage({
          message: '系统错误',
          type: 'error'
        })
        return
      }

    if (!error.response) {
      logOut()
      return Promise.reject(error.message)
    }
    if (error.response.data) {
      const {
        status,
        msg
      } = error.response.data
      // token 过期,重新登录
      if (status === 403) {
        logOut()
      } else {
        ElMessage({
          message: msg || '系统错误',
          type: 'error'
        })
      }
    }
    return Promise.reject(error.message)
  }
)

// 退出登录
async function logOut() {
  if(isShowTip || router.currentRoute.value.fullPath === '/login') {
    return
  }
 isShowTip = true
  window.localStorage.clear();
  sessionStorage.clear()
  ElMessageBox.confirm('当前页面已失效，请重新登录', '提示', {
    confirmButtonText: 'OK',
    type: 'warning'
  }).then(() => {
    isShowTip = false
    router.push('/login')
  })
}

// 导出 axios 实例
export default service;