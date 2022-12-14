package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.mapper.AbnormalMapper;
import com.fall.smarthouse.model.Abnormal;
import com.fall.smarthouse.service.IAbnormalService;
import com.fall.smarthouse.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/14 20:17
 */
@Service
public class IAbnormalServiceImpl implements IAbnormalService {

    @Autowired
    AbnormalMapper abnormalMapper;

    @Override
    public Boolean insertAbnormal(Abnormal abnormal) {
        Integer affectRows = abnormalMapper.insertAbnormal(
                new Abnormal(abnormal.getStartTime() / 1000,
                        abnormal.getEndTime() / 1000,
                        abnormal.getRiskIndex()));
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateAbnormal(Abnormal abnormal) {
        Integer affectRows = abnormalMapper.updateAbnormal(new Abnormal(
                abnormal.getStartTime() / 1000,
                abnormal.getEndTime() / 1000,
                abnormal.getRiskIndex()));
        if(affectRows == 0){
            return false;
        }
        return true;
    }

    @Override
    public List<Abnormal> restartSelectAbnormalData(String closeTime, String startTime) {
        Timestamp closeDate = DateConverter.StringToTimeStamp(closeTime);
        Timestamp startDate = DateConverter.StringToTimeStamp(startTime);
        List<Abnormal> abnormals = abnormalMapper.restartSelectAbnormalData(closeDate, startDate);
        return abnormals;
    }


}
