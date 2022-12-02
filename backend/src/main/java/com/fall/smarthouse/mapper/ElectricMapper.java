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

}
