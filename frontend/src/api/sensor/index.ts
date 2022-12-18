import {
    AxiosPromise
} from 'axios';
import request from '../../utils/request';
import {
    SensorDataSet,
    queryType,
    sensorType
} from './types';

/*
   获取当前数据
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
    //获取范围: 当前以及五秒前    

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
   根据时间差返回指定区间的数据
   startTime 开始时间戳
   endTime   结束时间戳
   sensorType传感器类型
*/
export async function getDataByDifference(startTime: number, endTime: number, sensorType: sensorType): AxiosPromise < SensorDataSet > {
    let ret: any = undefined
    if (!startTime || !endTime) {
        console.error('缺少时间范围');
        return ret
    } else {
        const difference = endTime - startTime
        const startString = startTime.toString()
        const endString = endTime.toString()
        return await getCertainData(startString, endString, sensorType, getGapByDifference(difference) as queryType, 1, 60)
    }
}

/*
   根据时间差查询当前时间间隔
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