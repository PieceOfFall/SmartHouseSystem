package com.fall.smarthouse.mapper;

import com.fall.smarthouse.model.ElectricAppliance;
import com.fall.smarthouse.model.SqlHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author FAll
 * @date 2022/12/2 17:58
 */
@Mapper
@Repository
public interface ElectricMapper {

    @Select("select * from electric_appliance")
    ElectricAppliance getAppliance();

    @Select("select light_bed_a,light_bed_b,light_living_room,light_bathroom from electric_appliance")
    ElectricAppliance getLight();

    @Select("select switch_a,switch_b,switch_c from electric_appliance")
    ElectricAppliance getSwitch();

    @Select("select curtain_a,curtain_b from electric_appliance")
    ElectricAppliance getCurtain();

    @Select("select warn_light from electric_appliance")
    ElectricAppliance getWarnLight();

    Integer updateElectricAppliance(ElectricAppliance electricAppliance);

    /**
     * @description: 添加历史记录的方法
     * @author xiaoQe
     * @date 2022/12/22 19:10
     */
    Integer insertElectricHistory(Timestamp time, String account, ElectricAppliance electricAppliance);

    /**
     * @description: 查询历史记录
     * @author xiaoQe
     * @date 2022/12/22 19:13
     */
    List<SqlHistory> selectElectricHistory(String account, Timestamp startTime, Timestamp endTime);
}
