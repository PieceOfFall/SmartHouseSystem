import request from '../../utils/request';
import {
    AxiosPromise
} from 'axios';
import {
    Token
} from './types';

// 用户登录
export async function userLogin(account: String, password: String): AxiosPromise < Token > {
    return await request({
        url: '/smart_house/user_login',
        method: 'post',
        data: {
            account,
            password
        },
    })
}

// 用户token校验
export async function checkLogin(token: Token): AxiosPromise {
    return await request({
        url: '/smart_house/check_login',
        method: 'post',
        headers: {
            Authorization: token
        }
    })
}