package com.fall.smarthouse.service.exception;

import com.fall.smarthouse.bean.ResBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author FAll
 * @date 2022/12/4 15:22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @author FAll
     * @description 请求参数缺失
     * @param e
     * @param response
     * @return: ResBean
     * @date 2022/12/4 15:23
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResBean missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletResponse response, HttpServletRequest request) {
        log.warn("请求参数缺失", e);
        response.setStatus(405);
        return ResBean.badRequest(String.format("请求参数缺失:%s", e.getParameterName()));
    }

    /**
     * @author FAll
     * @description 请求参数类型不匹配
     * @param e
     * @param response
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:23
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResBean methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,HttpServletResponse response) {
        log.warn("参数类型不匹配", e);
        response.setStatus(405);
        return ResBean.badRequest(String.format("参数类型不匹配:%s", e.getMessage()));
    }

    /**
     * @author FAll
     * @description 参数校验错误
     * @param e
     * @param response
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:23
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResBean methodArgumentNotValidException(MethodArgumentNotValidException e,HttpServletResponse response) {
        log.warn("参数校验错误", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        response.setStatus(405);
        return ResBean.badRequest(String.format("参数校验错误:%s", fieldError.getDefaultMessage()));
    }

    /**
     * @author FAll
     * @description 请求地址不存在 (已弃用)
     * @param e
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:23
     */
    @Deprecated
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResBean noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        log.warn("请求地址不存在", e);
        return ResBean.badRequest(404, String.format("请求地址 %s 不存在", e.getRequestURL()));
    }

    /**
     * @author FAll
     * @description 请求方式错误
     * @param e
     * @param response
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:24
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResBean httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e, HttpServletResponse response) {
        log.warn("请求方式错误", e);
        response.setStatus(405);
        return ResBean.badRequest(405, String.format("请求方法不正确:%s", e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResBean httpMessageNotReadableException(HttpMessageNotReadableException e,HttpServletResponse response) {
        log.warn("请求参数不可读",e);
        response.setStatus(400);
        return ResBean.badRequest(400,String.format("请求参数不可读:%s", e.getMessage()));
    }

    /**
     * @author FAll
     * @description 兜底捕获其它异常
     * @param e
     * @param response
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:24
     */
    @ExceptionHandler(Exception.class)
    public ResBean exceptionHandler(Exception e,HttpServletResponse response) {
        log.error(" 内部错误: {}", e.getMessage(), e);
        response.setStatus(500);
        return ResBean.internalError("内部错误");
    }

}
