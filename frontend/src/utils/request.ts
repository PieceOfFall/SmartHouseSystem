import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';
import { ElMessage, ElMessageBox } from 'element-plus';

// 创建 axios 实例
const service = axios.create({
    baseURL: "http://127.0.0.1:8081",
    timeout: 50000,
    headers: { 'Content-Type': 'application/json;charset=utf-8' },
  });

  // 请求拦截器
service.interceptors.request.use(
    (config: AxiosRequestConfig) => {
    //   if (!config.headers) {
    //     throw new Error(
    //       `Expected 'config' and 'config.headers' not to be undefined`
    //     );
    //   }
    // TODO: 请求拦截
      return config;
    },
    (error: any) => {
      return Promise.reject(error);
    }
  );

  // 响应拦截器
service.interceptors.response.use(
    (response: AxiosResponse) => {
        return response.data;
    },
    (error: any) => {
      if (error.response.data) {
        const { status, msg } = error.response.data;
        
        // token 过期,重新登录
        if (status === 403) {       
          ElMessageBox.confirm('当前页面已失效，请重新登录', '提示', {
            confirmButtonText: 'OK',
            type: 'warning'
          }).then(() => {
            window.localStorage.clear();
            window.location.href = '/login';
          })
        } else {
          ElMessage({
            message: msg || '系统出错',
            type: 'error'
          })
          
        }
      }
      return Promise.reject(error.message)
    }
  )

  // 导出 axios 实例
export default service;