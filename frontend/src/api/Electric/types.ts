/*
   所有灯光状态
*/
export interface LightsState {
    lightBedA: number,
    lightBedB: number,
    lightLivingRoom: number,
    lightBathroom: number
}

/*
   开关提交状态
*/
export interface SubmitSwitchState {
    switchA: number,
    switchB: number,
    switchC: number
}

/*
   所有开关状态
*/
export interface SwitchState {
    switchA: boolean,
    switchB: boolean,
    switchC: boolean
}

/*
   窗帘提交状态
*/
export interface SubmitCurtainState {
    curtainA: number,
    curtainB: number
}

/*
   所有窗帘状态
*/
export interface CurtainState {
    curtainA: number,
    curtainB: number
}

/*
   警报状态
*/
export interface WarnState {
    warnLight: number
}

/*
   电器总状态
*/
export interface ElectricAppliance extends WarnState,SubmitCurtainState,
   SubmitSwitchState,LightsState { }