package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.model.Sensor;
import com.fall.smarthouse.service.ISensorService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/5 15:40
 */
@RequestMapping("/sensor")
@RestController
public class SensorController {
    @Autowired
    ISensorService sensorService;

    @ApiOperation("添加传感器数据")
    @PostMapping("add_sensor")

    public ResBean AddSensor( @RequestBody Sensor sensor,
                             HttpServletResponse response) throws ParseException {
        boolean insertToSensor = sensorService.insertToSensor(sensor);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("查询燃气传感器数据")
    @GetMapping("get_gas_data")
    public ResBean GetGasData(@NotEmpty @RequestParam("minTime") String minTime,
                               @NotEmpty @RequestParam("maxTime") String maxTime,
                               @NotEmpty @RequestParam("pageNum") Integer pageNum,
                               @NotEmpty @RequestParam("pageSize") Integer pageSize,
                               HttpServletResponse response) throws ParseException {
        PageInfo<Double> gasSensorData = sensorService.getGasSensorData(minTime, maxTime, pageNum, pageSize);
        response.setStatus(200);
        return ResBean.ok("ok",gasSensorData);
    }

    @ApiOperation("查询烟雾传感器数据")
    @GetMapping("get_smog_data")
    public ResBean getSmogData(@NotEmpty @RequestParam("minTime") String minTime,
                               @NotEmpty @RequestParam("maxTime") String maxTime,
                               @NotEmpty @RequestParam("pageNum") Integer pageNum,
                               @NotEmpty @RequestParam("pageSize") Integer pageSize,
                               HttpServletResponse response) throws ParseException {
        PageInfo<Double> smogSensorData = sensorService.getSmogSensorData(minTime, maxTime, pageNum, pageSize);
        response.setStatus(200);
        return ResBean.ok("ok",smogSensorData);
    }

    @ApiOperation("查询温度传感器数据")
    @GetMapping("get_temperature_data")
    public ResBean getTemperatureData(@NotEmpty @RequestParam("minTime") String minTime,
                                      @NotEmpty @RequestParam("maxTime") String maxTime,
                                      @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                      @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                      HttpServletResponse response) throws ParseException {
        PageInfo<Double> temperatureSensorData = sensorService.getTemperatureSensorData(minTime, maxTime, pageNum, pageSize);
        response.setStatus(200);
        return ResBean.ok("ok",temperatureSensorData);
    }

    @ApiOperation("查询湿度传感器数据")
    @GetMapping("get_humidity_data")
    public ResBean getHumidityData(@NotEmpty @RequestParam("minTime") String minTime,
                                   @NotEmpty @RequestParam("maxTime") String maxTime,
                                   @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                   @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                   HttpServletResponse response) throws ParseException {
        PageInfo<Double> humiditySensorData = sensorService.getHumiditySensorData(minTime, maxTime, pageNum, pageSize);
        response.setStatus(200);
        return ResBean.ok("ok",humiditySensorData);
    }

    @ApiOperation("查询震动传感器数据")
    @GetMapping("get_shake_data")
    public ResBean getShakeData(@NotEmpty @RequestParam("minTime") String minTime,
                                @NotEmpty @RequestParam("maxTime") String maxTime,
                                @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                HttpServletResponse response) throws ParseException {
        PageInfo<Double> shakeSensorData = sensorService.getShakeSensorData(minTime, maxTime, pageNum, pageSize);
        response.setStatus(200);
        return ResBean.ok("ok",shakeSensorData);
    }

    @ApiOperation("检测传感器是否异常")
    @GetMapping("/safety_inspection")
    public ResBean getSafetyInspection(@NotEmpty @RequestParam("time") String time,HttpServletResponse response){
        Map<String, Object> map = sensorService.safetyInspection(time);
        if(map.isEmpty()){
            response.setStatus(200);
            return ResBean.ok("ok");
        }else {
            response.setStatus(200);
            return ResBean.ok("Abnormal sensor",map);
        }
    }
}
