package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.mapper.AbnormalMapper;
import com.fall.smarthouse.model.Abnormal;
import com.fall.smarthouse.service.IAbnormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
