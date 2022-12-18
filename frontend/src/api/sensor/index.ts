import request from '../../utils/request';

/*
   获取当前数据
*/
export async function getCurrentData(sensorType:'gas'|'shake'|'smog') {
    //获取范围: 当前以及五秒前    
    const now: number = new Date().getTime()
    const startTime = (now - 6000).toString()
    const endTime = now.toString()

    return await request({
        url:`/sensor/get_${sensorType}_data`,
        method:'get',
        params:{
            minTime: startTime,
            maxTime: endTime,
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
    startTime:string, 
    endTime:string, 
    sensorType:'gas'|'shake'|'smog',
    pageNum:number,
    pageSize:number
    ) {
    //获取范围: 当前以及五秒前    

    return await request({
        url:`/sensor/get_${sensorType}_data`,
        method:'get',
        params:{
            minTime: startTime,
            maxTime: endTime,
            pageNum,
            pageSize
        }
    })
}