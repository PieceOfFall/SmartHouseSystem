package com.fall.smarthouse.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author FAll
 * @date 2022/12/3 9:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBean {

    private Integer status;

    private String msg;

    private Object data;

    public static ResBean ok(String msg) {
        return new ResBean(200, msg, null);
    }

    public static ResBean ok(String msg, Object obj) {
        return new ResBean(200, msg, obj);
    }

    public static ResBean badRequest(int status ,String msg) {
        return new ResBean(status, msg, null);
    }

    public static ResBean badRequest(String msg) {
        return new ResBean(400, msg, null);
    }

    public static ResBean badRequest(String msg, Object obj) {
        return new ResBean(400, msg, obj);
    }

    public static ResBean unauthorized(String msg) {
        return new ResBean(401, msg, null);
    }

    public static ResBean internalError(String msg) {
        return new ResBean(500, msg, null);
    }

    public static ResBean forbidden(String msg) {
        return new ResBean(403, msg, null);
    }

}
