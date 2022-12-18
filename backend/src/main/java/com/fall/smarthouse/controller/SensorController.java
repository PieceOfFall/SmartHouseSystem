package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.model.Abnormal;
import com.fall.smarthouse.model.Sensor;
import com.fall.smarthouse.service.ISensorService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
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
                              @NotEmpty @RequestParam("queryType") Character queryType,
                               HttpServletResponse response){
        PageInfo<Map> gasSensorData = sensorService.getGasSensorData(minTime, maxTime, pageNum, pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",gasSensorData);
    }

    @ApiOperation("查询烟雾传感器数据")
    @GetMapping("get_smog_data")
    public ResBean getSmogData(@NotEmpty @RequestParam("minTime") String minTime,
                               @NotEmpty @RequestParam("maxTime") String maxTime,
                               @NotEmpty @RequestParam("pageNum") Integer pageNum,
                               @NotEmpty @RequestParam("pageSize") Integer pageSize,
                               @NotEmpty @RequestParam("queryType") Character queryType,
                               HttpServletResponse response){
        PageInfo<Map> smogSensorData = sensorService.getSmogSensorData(minTime, maxTime, pageNum, pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",smogSensorData);
    }

    @ApiOperation("查询温度传感器数据")
    @GetMapping("get_temperature_data")
    public ResBean getTemperatureData(@NotEmpty @RequestParam("minTime") String minTime,
                                      @NotEmpty @RequestParam("maxTime") String maxTime,
                                      @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                      @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                      @NotEmpty @RequestParam("queryType") Character queryType,
                                      HttpServletResponse response) {
        PageInfo<Map> temperatureSensorData = sensorService.getTemperatureSensorData(minTime, maxTime, pageNum, pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",temperatureSensorData);
    }

    @ApiOperation("查询湿度传感器数据")
    @GetMapping("get_humidity_data")
    public ResBean getHumidityData(@NotEmpty @RequestParam("minTime") String minTime,
                                   @NotEmpty @RequestParam("maxTime") String maxTime,
                                   @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                   @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                   @NotEmpty @RequestParam("queryType") Character queryType,
                                   HttpServletResponse response){
        PageInfo<Map> humiditySensorData = sensorService.getHumiditySensorData(minTime, maxTime, pageNum, pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",humiditySensorData);
    }

    @ApiOperation("查询震动传感器数据")
    @GetMapping("get_shake_data")
    public ResBean getShakeData(@NotEmpty @RequestParam("minTime") String minTime,
                                @NotEmpty @RequestParam("maxTime") String maxTime,
                                @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                @NotEmpty @RequestParam("queryType") Character queryType,
                                HttpServletResponse response) throws ParseException {
        PageInfo<Map> shakeSensorData = sensorService.getShakeSensorData(minTime, maxTime, pageNum, pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",shakeSensorData);
    }

    @ApiOperation("轮询检测传感器是否异常")
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

    @ApiOperation("通过异常表查询客户端断开连接是否有异常")
    @GetMapping("get_client_disconnect_safety")
    public ResBean getClientDisconnectSafety(@NotEmpty @RequestParam("closeTime") String closeTime,
                                           @NotEmpty @RequestParam("startTime") String startTime,
                                           HttpServletResponse response) {
        List<Abnormal> abnormals = sensorService.clientDisconnectSelectAbnormalData(closeTime, startTime);
        if(abnormals.isEmpty()){
            response.setStatus(200);
            return ResBean.ok("ok");
        }else {
            response.setStatus(200);
            return ResBean.ok("have abnormal",abnormals);
        }
    }

    @ApiOperation("根据时间查询传感器数据的接口")
    @GetMapping("get_sensor_data")
    public ResBean getSensorDataByTime(@NotEmpty @RequestParam("minTime") String minTime,
                                       @NotEmpty @RequestParam("maxTime") String maxTime,
                                       @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                       @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                       HttpServletResponse response){
        PageInfo<Sensor> sensorPageInfo = sensorService.selectSensorDataByTime(minTime, maxTime, pageNum, pageSize);
        response.setStatus(200);
        return ResBean.ok("ok",sensorPageInfo);
    }

    @ApiOperation("根据异常类型查询开始时间")
    @GetMapping("get_start_time")
    public ResBean getStartTime(@NotEmpty @RequestParam("riskIndex") Integer riskIndex,
                                HttpServletResponse response){
        List<Long> startTimeByRiskIndex = sensorService.getStartTimeByRiskIndex(riskIndex);
        response.setStatus(200);
        return ResBean.ok("ok",startTimeByRiskIndex);
    }

    @ApiOperation("根据开始时间获取异常燃气数据")
    @GetMapping("get_abnormal_gas_data")
    public ResBean getAbnormalGasData(@NotEmpty @RequestParam("startTime") String startTime,
                                      @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                      @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                      @NotEmpty @RequestParam("queryType") Character queryType,
                                      HttpServletResponse response){
        PageInfo<Map> abnormalGasData = sensorService.getAbnormalGasData(startTime,pageNum,pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",abnormalGasData);
    }

    @ApiOperation("根据开始时间获取异常烟雾数据")
    @GetMapping("get_abnormal_smog_data")
    public ResBean getAbnormalSmogData(@NotEmpty @RequestParam("startTime") String startTime,
                                       @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                       @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                       @NotEmpty @RequestParam("queryType") Character queryType,
                                       HttpServletResponse response){
        PageInfo<Map> abnormalSmogData = sensorService.getAbnormalSmogData(startTime,pageNum,pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",abnormalSmogData);
    }

    @ApiOperation("根据开始时间获取异常温度数据")
    @GetMapping("get_abnormal_temperature_data")
    public ResBean getAbnormalTemperatureData(@NotEmpty @RequestParam("startTime") String startTime,
                                              @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                              @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                              @NotEmpty @RequestParam("queryType") Character queryType,
                                       HttpServletResponse response){
        PageInfo<Map> abnormalTemperatureData = sensorService.getAbnormalTemperatureData(startTime,pageNum,pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",abnormalTemperatureData);
    }

    @ApiOperation("根据开始时间获取异常湿度数据")
    @GetMapping("get_abnormal_humidity_data")
    public ResBean getAbnormalHumidityData(@NotEmpty @RequestParam("startTime") String startTime,
                                           @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                           @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                           @NotEmpty @RequestParam("queryType") Character queryType,
                                              HttpServletResponse response){
        PageInfo<Map> abnormalHumidityData = sensorService.getAbnormalHumidityData(startTime,pageNum,pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",abnormalHumidityData);
    }

    @ApiOperation("根据开始时间获取异常震动数据")
    @GetMapping("get_abnormal_shake_data")
    public ResBean getAbnormalShakeData(@NotEmpty @RequestParam("startTime") String startTime,
                                        @NotEmpty @RequestParam("pageNum") Integer pageNum,
                                        @NotEmpty @RequestParam("pageSize") Integer pageSize,
                                        @NotEmpty @RequestParam("queryType") Character queryType,
                                           HttpServletResponse response){
        PageInfo<Map> abnormalShakeData = sensorService.getAbnormalShakeData(startTime,pageNum,pageSize,queryType);
        response.setStatus(200);
        return ResBean.ok("ok",abnormalShakeData);
    }
}
