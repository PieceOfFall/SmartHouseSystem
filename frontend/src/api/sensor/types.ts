/*
   查询间隔类型
*/
export type queryType = 's' | 'm' | 'h' | 'd'

/*
   传感器类型
*/
export type sensorType = 'gas' | 'shake' | 'smog'

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
   单条任意传感器记录
*/
export interface SensorData extends GasData, ShakeData, SmogData {}

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