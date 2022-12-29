export interface OperationHistory {
    time:number,
    electricType:
    |'light'
    |'switch'
    |'curtain'
    |''
    operationType:
    |'close'
    |'small'
    |'big'
    |'full'
    |'on'
    |'off'
    |''
    electricId:string
}

export interface OperationHistoryRender {
    time:string,
    electricType:
    |'灯光'
    |'开关'
    |'窗帘'
    |''
    operationType:
    |'关闭'
    |'较小'
    |'正常'
    |'全开'
    |'开'
    |'关'
    |''
    electricId:string
    color?:
    |'yellow'
    |'lightgreen'
    |'cyan'
    |''
}

export interface operationResponse {
    total:number,
    list:OperationHistory[],
    pageNum: number,
    pageSize:number,
    size:number,
    startRow:number,
    endRow:number,
    pages:number,
    prePage:number,
    nextPage:number,
    isFirstPage:boolean,
    isLastPage:boolean,
    hasPreviousPage:boolean,
    hasNextPage:boolean,
    navigatePages:number,
    navigatepageNums:number[],
    navigateFirstPage:number,
    navigateLastPage:number
}