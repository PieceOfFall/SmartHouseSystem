package com.fall.smarthouse.controller;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.service.ISensorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.text.ParseException;

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
}
