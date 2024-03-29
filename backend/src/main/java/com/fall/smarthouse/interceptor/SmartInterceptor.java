package com.fall.smarthouse.interceptor;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author FAll
 * @date 2022/12/12 12:10
 */
@Slf4j
@Component
public class SmartInterceptor implements HandlerInterceptor {
    // jackson
    private final ObjectMapper mapper;

    @Autowired
    public SmartInterceptor(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * @author FAll
     * @description 进入controller前预处理
     * @param request http请求
     * @param response http响应
     * @param handler 处理器
     * @return: boolean
     * @date 2022/12/12 12:44
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            log.trace("OPTIONS请求,放行");
            return true;
        }
        String token = request.getHeader("Authorization");
        boolean needUpdate = JWTUtil.isNeedUpdate(token);
        if (needUpdate) {
            returnJSON(response, mapper.writeValueAsString(ResBean.unauthorized("登录失效，请重新登录")));
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private void returnJSON(HttpServletResponse response, String json){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;character=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(json);
        } catch (IOException e) {
            log.error("response error", e);
        }
    }
}
