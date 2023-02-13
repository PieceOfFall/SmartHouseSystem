package com.fall.smarthouse.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * @author FAll
 * @date 2022/12/3 9:53
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MenuItem {
    // id
    private int id;
    // 路径
    private String path;
    // 选项名
    private String authName;
    // 子选项
    @Nullable
    private MenuItem[] children;
}
