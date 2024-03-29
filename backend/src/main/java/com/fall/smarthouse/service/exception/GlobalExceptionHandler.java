package com.fall.smarthouse.service.exception;

import com.fall.smarthouse.bean.ResBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author FAll
 * @date 2022/12/4 15:22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // jackson
    private final ObjectMapper mapper;

    @Autowired
    public GlobalExceptionHandler(ObjectMapper mapper){
        this.mapper = mapper;
    }

    /**
     * @author FAll
     * @description 请求参数缺失
     * @param e 请求参数缺失异常
     * @param request http请求
     * @param response http响应
     * @return: ResBean
     * @date 2022/12/4 15:23
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResBean missingServletRequestParameterException(MissingServletRequestParameterException e,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) {
        logWarn(request);
        log.warn("请求参数缺失", e);
        response.setStatus(403);
        return ResBean.badRequest(String.format("请求参数缺失:%s", e.getParameterName()));
    }

    /**
     * @author FAll
     * @description 请求参数类型不匹配
     * @param e 请求参数类型不匹配异常
     * @param request http请求
     * @param response http响应
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:23
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResBean methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) {
        logWarn(request);
        log.warn("参数类型不匹配", e);
        response.setStatus(403);
        return ResBean.badRequest(String.format("参数类型不匹配:%s", e.getMessage()));
    }

    /**
     * @author FAll
     * @description 参数校验错误
     * @param e 参数校验错误异常
     * @param response http响应
     * @param request http请求
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:23
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResBean methodArgumentNotValidException(MethodArgumentNotValidException e,
                                                   HttpServletResponse response,
                                                   HttpServletRequest request) {
        logWarn(request);
        log.warn("参数校验错误", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        response.setStatus(403);
        return ResBean.badRequest(String.format("参数校验错误:%s", Objects.requireNonNull(fieldError).getDefaultMessage()));
    }

    /**
     * @author FAll
     * @description 请求地址不存在 (已弃用)
     * @param e 请求地址不存在异常
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
     * @param e 请求方式错误异常
     * @param response http响应
     * @param request http请求
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:24
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResBean httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e,
                                                                 HttpServletResponse response,
                                                                 HttpServletRequest request) {

        logWarn(request);
        log.warn("请求方式错误", e);
        response.setStatus(405);
        return ResBean.badRequest(405, String.format("请求方法不正确:%s", e.getMessage()));
    }

    /**
     * @author FAll
     * @description 请求参数不可读
     * @param e 请求参数不可读异常
     * @param response http响应
     * @param request http请求
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:24
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResBean httpMessageNotReadableException(HttpMessageNotReadableException e,
                                                   HttpServletResponse response,
                                                   HttpServletRequest request) {
        logWarn(request);
        log.warn("请求参数不可读",e);
        response.setStatus(400);
        return ResBean.badRequest(400,String.format("请求参数不可读:%s", e.getMessage()));
    }

    /**
     * @author FAll
     * @description 兜底捕获其它异常
     * @param e 当前异常
     * @param response http响应
     * @param request http请求
     * @return: com.fall.smarthouse.bean.ResBean
     * @date 2022/12/4 15:24
     */
    @ExceptionHandler(Exception.class)
    public ResBean exceptionHandler(Exception e,
                                    HttpServletResponse response,
                                    HttpServletRequest request) {
        logError(request);
        log.error(" 内部错误: {}", e.getMessage(), e);
        response.setStatus(500);
        return ResBean.internalError("内部错误");
    }

    /**
     * @author FAll
     * @description 输出错误接口
     * @param request http请求
     * @date 2022/12/29 16:22
     */
    private void logWarn(HttpServletRequest request) {
        log.warn("请求 URL: {}", request.getRequestURL());
        log.warn("请求 方式: {}", request.getMethod());
        log.warn("请求 IP: {}", request.getRemoteAddr());
        log.warn("请求 头: {}", request.getHeaderNames());
        log.warn("请求 参数: {}", request.getParameterMap());
    }

    /**
     * @author FAll
     * @description 输出告警接口
     * @param request http请求
     * @date 2022/12/29 16:23
     */
    private void logError(HttpServletRequest request) {
        try {
        log.error(" 请求 URL: {}", request.getRequestURL());
        log.error(" 请求 方式: {}", request.getMethod());
        log.error(" 请求 IP: {}", request.getRemoteAddr());
        Enumeration<String> headerNames = request.getHeaderNames();
        HashMap<String, String> headerMap = new HashMap<>();
        String headerName;
        while(headerNames.hasMoreElements()) {
            headerName = headerNames.nextElement();
            headerMap.put(headerName,request.getHeader(headerName));
        }
        log.error(" 请求 头: {}", mapper.writeValueAsString(headerMap));
        log.error(" 请求 参数: {}", mapper.writeValueAsString(request.getParameterMap()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
