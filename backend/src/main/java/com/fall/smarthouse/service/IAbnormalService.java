package com.fall.smarthouse.service;

import com.fall.smarthouse.model.Abnormal;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2022/12/14 20:17
 */
public interface IAbnormalService {
    /**
     * @description: TODO
     * @author xiaoQe
     * @date 2022/12/14 20:18
     * @version 1.0
     */
    Boolean insertAbnormal(Abnormal abnormal);

    /**
     * @description: 修改数据的方法
     * @author xiaoQe
     * @date 2022/12/14 20:23
     * @version 1.0
     */
    Boolean updateAbnormal(Abnormal abnormal);
}
