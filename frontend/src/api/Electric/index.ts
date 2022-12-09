import request from '../../utils/request';
import {
    AxiosPromise
} from 'axios';
import {
    LightsState,
    ElectricAppliance,
    SwitchState
} from './types';

// 修改电器数据
export async function setAppliance(appliance: ElectricAppliance) {
    return await request({
        url: '/electric/set_appliance',
        method: 'post',
        data: {
            ...appliance
        }
    })
}

// 获取所有灯光
export async function getLights(): AxiosPromise < LightsState > {
    return await request({
        url: '/electric/get_light',
        method: 'get'
    })
}

// 获取所有开关数据
export async function getSwitches(): AxiosPromise < SwitchState > {
    let response = await request({
        url: '/electric/get_switch',
        method: 'get'
    })

    // 将数值0映射为false，1映射为true
    response.data.switchA=response.data.switchA === 0?false:true
    response.data.switchB=response.data.switchB === 0?false:true
    response.data.switchC=response.data.switchC === 0?false:true
    
    return response
}

/*
   总开关控制
*/
export async function toggleAllSwitch(isOneSwitchOnExist:boolean) {
    if(isOneSwitchOnExist) {
        return await setAppliance({
            switchA:0,
            switchB:0,
            switchC:0
        } as ElectricAppliance)
    } else {
        return await setAppliance({
            switchA:1,
            switchB:1,
            switchC:1
        } as ElectricAppliance)
    }

}
