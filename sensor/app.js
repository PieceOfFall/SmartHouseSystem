const express =require('express');
const app=express()
const axios = require('axios').default
const CronJob = require('cron').CronJob;
const {getSensors,index}=require('./sensors')

// 初始化定时器
let job = new CronJob(
        '* * * * * *',
        async function() {
            //TODO: 每秒调用网关api获取传感器数据并上传
            const ret = await getSensors()
            axios.post('http://localhost:8081/sensor/add_sensor',
            {
                "gas": ret[index.GAS],
                "humidity": ret[index.HUMIDITY],
                "shake": ret[index.SHAKE],
                "smog": ret[index.SMOG],
                "temperature": ret[index.TEMPERATURE],
                "time": ret[index.TIME]
            })
        },
        null,
        true,
        'Asia/Shanghai'
)

// 开启定时器
job.start()

process.env.PORT = 7100

const server = app.listen(7100,()=>{

})

