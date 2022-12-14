package com.fall.smarthouse.mapper;

import com.fall.smarthouse.model.Abnormal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
     * @version 1.0
     */
    Integer insertAbnormal(Abnormal abnormal);

    /**
     * @description: 修改异常信息
     * @author xiaoQe
     * @date 2022/12/14 20:08
     * @version 1.0
     */
    Integer updateAbnormal(Abnormal abnormal);
}
