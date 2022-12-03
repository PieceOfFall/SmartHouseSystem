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
    private int id;
    private String path;
    private String authName;
    @Nullable
    private MenuItem[] children;
}
