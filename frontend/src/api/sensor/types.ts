export interface GasData {
    gas: number
    time: number
}

export interface ShakeData {
    shake: number
    time: number
}

export interface SmogData {
    smog: number
    time: number
}

export interface SensorData extends GasData, ShakeData {}