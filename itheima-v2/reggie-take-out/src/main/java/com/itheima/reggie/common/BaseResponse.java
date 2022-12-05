package com.itheima.reggie.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果
 * @param <T>
 */
@Data
public class BaseResponse<T> {

    // 1成功，0和其它数字为失败
    private Integer code;

    // 错误信息
    private String msg;

    // 返回数据
    private T data;

    // 返回的动态数据
    private Map<String, Object> map = new HashMap<>();

    public static <T> BaseResponse<T> success(T object) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.data = object;
        baseResponse.code = 1;
        return baseResponse;
    }

    public static <T> BaseResponse<T> error(String msg) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.msg = msg;
        baseResponse.code = 0;
        return baseResponse;
    }

    public BaseResponse<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
