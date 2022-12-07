/*
   获取所有灯光状态
*/
export interface LightsState {
    lightBedA: number,
    lightBedB: number,
    lightLivingRoom: number,
    lightBathroom: number
}

/*
   获取所有开关状态
*/
export interface SwitchState {
    switchA: number,
    switchB: number,
    switchC: number
}

/*
   获取所有窗帘状态
*/
export interface CurtainState {
    curtainA: number,
    curtainB: number
}

/*
   获取警报状态
*/
export interface WarnState {
    warnLight: number
}

export interface ElectricAppliance extends WarnState,CurtainState,SwitchState,LightsState { }