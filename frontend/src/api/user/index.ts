import request from '../../utils/request';
import { AxiosPromise } from 'axios';
import { operationResponse, OperationHistory } from './types';

/*
   获取用户注册时间戳
*/
export async function getUserCreateTimestamp():AxiosPromise<number> {
    return await request({
        url:'/user/get_user_create_time',
        method:'get'
    })
}

/*
   根据开始时间戳获取用户最新操作历史 (分页)
*/
export async function getUserLatestHistoryByTimestamp(
    startTime:number,
    pageNum:number,
    pageSize:number):AxiosPromise<operationResponse> {
    return await request({
        url:'/electric/get_history',
        method:'get',
        params:{
            startTime,
            pageNum,
            pageSize
        }
    })
}

/*
   获取最新7天的最新操作历史
*/
export async function getUserLatestHistory():AxiosPromise<operationResponse> {
    return await getUserLatestHistoryByTimestamp(
        (Math.floor((new Date().getTime()-7*24*3600*1000)/1000)),
        1,
        8
    )
}


