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
