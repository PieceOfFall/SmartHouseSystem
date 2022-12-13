
exports.index = {
    TIME:0,
    GAS:1,
    SMOG:2,
    TEMPERATURE:3,
    HUMIDITY:4,
    SHAKE:5
}

/*
   虚拟网关api: 获取所有传感器数据
*/
exports.getSensors = async function getSensors() {
    return await Promise.all([
        /*
           获取当前时间戳
        */
        new Promise((resolve) => {
            // 返回当前时间戳
            resolve(new Date().getTime())
        }),

        /*
           获取当前空气中天然气浓度
        */
        new Promise((resolve) => {
            // 返回常规值0.001~0.01
            
            resolve((Math.floor(Math.random()*10)/1000))
        }),

        /*
           获取当前烟雾浓度
        */
        new Promise((resolve) => {
            // 返回正常值 60~70
            resolve((60 + (Math.floor(Math.random()*10)/10) * 10))
        }),

        /*
           获取当前温度
        */
        new Promise((resolve) => {
            // 返回正常值 28~29
            resolve((28 + (Math.floor(Math.random()*10)/10)))
        }),

        /*
           获取当前湿度
        */
        new Promise((resolve) => {
            // 返回舒适值 0.3~0.6
            resolve((30 + (Math.floor(Math.random()*10)/10) * 30)/100)
        }),

        /*
           获取当前震感
        */
        new Promise((resolve) => {
            // 返回正常值 0~1
            resolve((Math.floor(Math.random()*10)/10))
        })
    ])
}