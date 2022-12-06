package com.fall.smarthouse.mapper;

import com.fall.smarthouse.model.ElectricAppliance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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

}
