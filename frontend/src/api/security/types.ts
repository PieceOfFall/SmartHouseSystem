/*
   查询间隔类型
*/
export type queryType = 
| 's' // 秒
| 'm' // 分
| 'h' // 时
| 'd' // 天

/*
   传感器类型
*/
export type sensorType = 
| 'gas' 
| 'shake' 
| 'smog'
| 'humidity'
| 'temperature'

/*
   单条燃气记录
*/
export interface GasData {
   gas: number
   time: number
}

/*
   单条震动记录
*/
export interface ShakeData {
   shake: number
   time: number
}

/*
   单条烟雾记录
*/
export interface SmogData {
   smog: number
   time: number
}

/*
   单条温度记录
*/
export interface TemperatureData {
   temperature: number
   time: number
}

/*
   单条湿度记录
*/
export interface HumidityData {
   humidity: number,
   time: number
}

/*
   温湿度记录 Promise.all
*/
export type HumidityAndTemperaturePromise = [TemperatureData[],HumidityData[]]

/*
   单条任意传感器记录
*/
export interface SensorData extends GasData, ShakeData, SmogData,TemperatureData,HumidityData {}

/*
   传感器数据查询结果集
*/
export interface SensorDataSet {
   endRow: number,
   hasNextPage: boolean,
   hasPreviousPage: boolean,
   isFirstPage: boolean,
   isLastPage: boolean,
   list: SensorData[]
   navigateFirstPage: number,
   navigateLastPage: number,
   navigatePages: number,
   navigatepageNums: number[],
   nextPage: number,
   pageNum: number,
   pageSize: number,
   pages: number,
   prePage: number,
   size: number,
   startRow: number,
   total: number
}

/*
   传感器异常数据结果集
*/
export interface AbnormalDataSet {
   endRow: number,
   hasNextPage: boolean,
   hasPreviousPage: boolean,
   isFirstPage: boolean,
   isLastPage: boolean,
   list: SensorData[]
   navigateFirstPage: number,
   navigateLastPage: number,
   navigatePages: number,
   navigatepageNums: number[],
   nextPage: number,
   pageNum: number,
   pageSize: number,
   pages: number,
   prePage: number,
   size: number,
   startRow: number,
   total: number
}

/*
   用户数据
*/
export interface UserData {
   account: string,
   password?:null,
   creatTime:number,
   role:number,
   email:string
}

/*
   用户渲染数据
*/
export interface UserRenderData {
   account: string,
   createdTime:string,
   role:
   |'root'
   |'master'
   |'user',
   email:string
}

/*
   首页温湿度结果
*/
export type HumidityAndTemperature = {
   bar:number[],
   line:number[]
}