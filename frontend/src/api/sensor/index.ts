import request from '../../utils/request';
import {
    AxiosPromise
} from 'axios';


/*
   获取当前燃气数据
*/
export async function getCurrentGas(){
    
    const now: number = new Date().getTime()
    const startTime = (now - 6000).toString()
    const endTime = now.toString()

    return await request({
        url:'/sensor/get_gas_data',
        method:'get',
        params:{
            minTime: startTime,
            maxTime: endTime,
            pageNum: 1,
            pageSize: 6
        }
    })
}
