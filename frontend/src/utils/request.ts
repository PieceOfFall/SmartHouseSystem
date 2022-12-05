import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';

// 创建 axios 实例
const service = axios.create({
    baseURL: "http://127.0.0.1:8081",
    timeout: 50000,
    headers: { 'Content-Type': 'application/json;charset=utf-8' }
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
    //TODO: 响应拦截
    (response: AxiosResponse) => {
      return response.data
    }
  );

  // 导出 axios 实例
export default service;