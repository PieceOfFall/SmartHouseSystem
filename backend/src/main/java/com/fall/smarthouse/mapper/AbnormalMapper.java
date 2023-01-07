package com.fall.smarthouse.mapper;

import com.fall.smarthouse.model.Abnormal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/14 19:44
 */
@Mapper
@Repository
public interface AbnormalMapper {
    /**
     * @description: 添加异常信息
     * @author xiaoQe
     * @date 2022/12/14 20:08
     */
    Integer insertAbnormal(Abnormal abnormal);

    /**
     * @description: 修改异常信息
     * @author xiaoQe
     * @date 2022/12/14 20:08
     */
    Integer updateAbnormal(Abnormal abnormal);

    /**
     * @description: 重启时启动的再次检查
     * @author xiaoQe
     * @date 2022/12/14 22:03
     */
    List<Abnormal> restartSelectAbnormalData(Timestamp closeTime, Timestamp startTime);

    /**
     * @description: 根据异常类型查询异常开始时间
     * @author xiaoQe
     * @date 2022/12/16 19:27
     */
    @Select("select UNIX_TIMESTAMP(start_time) from abnormal where risk_index & #{riskIndex} = #{riskIndex}")
    List<Long> selectStartTimeByRiskIndex(Integer riskIndex);
}
