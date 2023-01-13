import {
    AxiosPromise
} from 'axios';
import request from '../../utils/request';
import {
    SensorDataSet,
    queryType,
    sensorType,
    UserData,
    UserRenderData
} from './types';

/*
   获取当前数据
   sensorType 传感器类型
*/
export async function getCurrentData(sensorType: sensorType): AxiosPromise < SensorDataSet > {
    //获取范围: 当前以及五秒前    
    const now: number = new Date().getTime()
    const startTime = (now - 6000).toString()
    const endTime = now.toString()

    return await request({
        url: `/sensor/get_${sensorType}_data`,
        method: 'get',
        params: {
            minTime: startTime,
            maxTime: endTime,
            queryType: 's',
            pageNum: 1,
            pageSize: 6
        }
    })
}

/*
   获取指定时间的数据
   startTime: 开始时间戳
   endTime: 结束时间戳
   queryType: 时间分割类型
   sensorType: 传感器类型
   pageNum: 分页页码
   pageSize: 单页数据量
*/
export async function getCertainData(
    startTime: string,
    endTime: string,
    sensorType: sensorType,
    queryType: queryType,
    pageNum: number,
    pageSize: number
): AxiosPromise < SensorDataSet > {    

    return await request({
        url: `/sensor/get_${sensorType}_data`,
        method: 'get',
        params: {
            minTime: startTime,
            maxTime: endTime,
            queryType,
            pageNum,
            pageSize
        }
    })
}

/*
   根据时间差查询指定区间的数据
   startTime 开始时间戳
   endTime   结束时间戳
   sensorType传感器类型
*/
export async function getDataByDifference(startTime: number, endTime: number,
    sensorType: sensorType): AxiosPromise < SensorDataSet > {

    let ret: any = undefined
    if (!startTime || !endTime) {
        console.error('缺少时间范围');
        return ret
    } else {
        const difference = endTime - startTime
        const startString = startTime.toString()
        const endString = endTime.toString()
        return await getCertainData(startString, endString,
            sensorType, getGapByDifference(difference) as queryType, 1, 60)
    }

}

/*
    异常 1
    根据传感器对应的危险指数查询异常开始时间
    sensorType 传感器类型
*/
export async function getAbnormalByDifference(sensorType: sensorType):AxiosPromise < number[] > {
    return await request({
        url:'/sensor/get_abnormal_start_time_by_sensor_risk',
        params:{
            riskIndex:mapRiskIndexBySensorType(sensorType)
        }
    })
}

/*
    异常 2
    获取发生在指定时间的指定传感器异常发生时期数据
    sensorType   异常传感器类型
    startTime    异常开始时间
    queryType    时间分割类型
    pageSize     页数据量
    pageNum      页码
*/
export async function getCertainAbnormal(
    sensorType:sensorType,
    startTime:number,
    pageSize:number,
    pageNum:number,
    queryType:queryType):AxiosPromise<SensorDataSet> {
    
        return await request({
            url:`/sensor/get_abnormal_${sensorType}_data`,
            method:'get',
            params: {
                pageNum,
                pageSize,
                startTime,
                queryType
            }
        })
}

/*
   获取所有用户
*/
export async function getAllUsers():AxiosPromise<UserRenderData[]> {
    const users = await request({
        url:'/user/get_all_user',
        method:'get'
    })

    users.data =  users.data.map((currentValue:UserData,index:number)=>{
        const date = new Date(currentValue.creatTime as number)
        return {
            account:currentValue.account,
            createdTime:`${date.getFullYear()}年${date.getMonth()+1}月${date.getDate()}日`,
            role:currentValue.role===2?'root':currentValue.role===1?'master':'user',
            email:currentValue.email
        }
    })

    return users
}

/*
   修改用户
*/
export async function editUser(userInfo:UserData):AxiosPromise<boolean> {
    return await request({
        url:'/user/update_user',
        method:'post',
        data:{
            ...userInfo
        }
    })
    
}


/*
    传感器util
    根据时间差查询当前时间间隔
    difference 始末时间戳之差
*/
export function getGapByDifference(difference: number) {
    if (difference / (1000 * 60 * 60 * 24) >= 1) {
        // 按天查询
        return 'd'
    } else if (difference / (1000 * 60 * 60) >= 1) {
        // 按小时查询
        return 'h'
    } else if (difference / (1000 * 60) >= 1) {
        // 按分钟查询
        return 'm'
    } else if (difference / 1000 >= 1) {
        // 按秒查询
        return 's'
    } else {
        console.error('时间范围异常')
        return
    }
}

/*
    异常util
    根据异常传感器类型获取对应危险指数
*/
export function mapRiskIndexBySensorType(sensorType: sensorType): number {
    switch(sensorType) {
        case 'gas': {
            return 8
        }
        case 'shake': {
            return 16
        }
        case 'smog': {
            return 2
        }
        default:
            return -1
    }
}