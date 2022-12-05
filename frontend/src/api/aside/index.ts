import request from '../../utils/request';
import { AxiosPromise } from 'axios';
import {MenuItem} from './types';

// 获取菜单列表
export async function getAsideList():AxiosPromise<MenuItem[]> {
    return await request({
        url:'/smart_house/get_menu',
        method:'get'
    })
}