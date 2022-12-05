package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.service.ISensorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

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
    public ResBean AddSensor(@NotEmpty @RequestParam("time")String time,
                             @NotEmpty @RequestParam("gas")Double gas,
                             @NotEmpty @RequestParam("smog")Double smog,
                             @NotEmpty @RequestParam("temperature")Double temperature,
                             @NotEmpty @RequestParam("humidity")Double humidity,
                             @NotEmpty @RequestParam("shake")Double shake,
                             HttpServletResponse response) throws ParseException {
        boolean insertToSensor = sensorService.insertToSensor(time, gas, smog, temperature, humidity, shake);
        response.setStatus(200);
        return ResBean.ok("ok");
    }

    @ApiOperation("查询燃气传感器数据")
    @GetMapping("get_gas_datas")
    public ResBean GetGasData(@NotEmpty @RequestParam("minTime") String minTime,
                               @NotEmpty @RequestParam("maxTime") String maxTime,
                               HttpServletResponse response) throws ParseException {
        List<Double> gasSensorData = sensorService.getGasSensorData(minTime, maxTime);
        response.setStatus(200);
        return ResBean.ok("ok",gasSensorData);
    }
}
