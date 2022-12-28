import request from '../../utils/request';
import { AxiosPromise } from 'axios';

// 获取用户注册时间戳
export async function getUserCreateTimestamp():AxiosPromise<number> {
    return await request({
        url:'/electric/get_user_create_time',
        method:'get'
    })
}
