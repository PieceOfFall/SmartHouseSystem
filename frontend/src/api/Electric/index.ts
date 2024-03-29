import request from '../../utils/request';
import {
    AxiosPromise
} from 'axios';
import {
    LightsState,
    ElectricAppliance,
    SwitchState,
    CurtainState
} from './types';
import { ElMessage, ElMessageBox } from 'element-plus';

/*
   修改电器数据
   appliance 电器
*/
export async function setAppliance(appliance: ElectricAppliance) {
    return await request({
        url: '/electric/set_appliance',
        method: 'post',
        data: {
            ...appliance
        }
    })
}

/*
   获取所有灯光
*/
export async function getLights(): AxiosPromise < LightsState > {
    return await request({
        url: '/electric/get_light',
        method: 'get'
    })
}

/*
   获取所有开关数据
*/
export async function getSwitches():AxiosPromise<SwitchState> {
    const response = await request({
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
   isOneSwitchOnExist 是否至少有一个开关打开
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

/*
   获取所有窗帘数据
*/
export async function getCurtains():AxiosPromise < CurtainState > {
    return await request({
        url:'/electric/get_curtain',
        method:'get'
    })
}

/*
   回家模式
*/
export async function toggleHomeMode() {
    const ret = await request({
        url:'/electric/set_home_mode',
        method:'post'
    })
    ElMessage({
        message: ret.status===200?'回家模式设置成功':'设置失败',
        type: ret.status===200?'success':'error'
    })
    return  
}

/*
   离家模式
*/
export async function toggleLeaveMode() {
    const ret = await request({
        url:'/electric/set_leave_home_mode',
        method:'post'
    })
    ElMessage({
        message: ret.status===200?'离家模式设置成功':'设置失败',
        type: ret.status===200?'success':'error'
    })
    return
}
