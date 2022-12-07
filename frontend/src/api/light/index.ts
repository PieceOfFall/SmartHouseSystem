import request from '../../utils/request';
import { AxiosPromise } from 'axios';
import {LightsState} from './types';

// 获取所有灯光
export async function getLights():AxiosPromise<LightsState> {
    return await request({
        url:'/electric/get_light',
        method:'get'
    })
}