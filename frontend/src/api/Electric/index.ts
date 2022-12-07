import request from '../../utils/request';
import { AxiosPromise } from 'axios';
import {LightsState,ElectricAppliance} from './types';

// 获取所有灯光
export async function getLights():AxiosPromise<LightsState> {
    return await request({
        url:'/electric/get_light',
        method:'get'
    })
}

// 修改电器数据
export async function setAppliance(appliance:ElectricAppliance) {
    return await request({
        url:'/electric/set_appliance',
        method:'post',
        data:{
            ...appliance
        }
    })
}