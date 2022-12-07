package com.fall.smarthouse.filter;

import com.fall.smarthouse.bean.ResBean;
import com.fall.smarthouse.util.JWTUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author FAll
 * @date 2022/12/4 16:18
 */

@Slf4j
@WebFilter(urlPatterns = {"/electric/*", "/sensor/*"}, filterName = "SmartHouseFilter")
public class SmartHouseFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * @description: 验证token是否有效
     * @author xiaoQe
     * @date 2022/12/6 16:48
     * @version 1.0
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("token");
        boolean needUpdate = JWTUtil.isNeedUpdate(token);
        if (needUpdate) {
            ObjectMapper mapper = new ObjectMapper();
            returnJSON(servletResponse, mapper.writeValueAsString(ResBean.unauthorized("登录失效，请重新登录")));
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public void returnJSON(ServletResponse response,String json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;character=utf-8");
        try {
            writer = response.getWriter();
            writer.write(json);
        } catch (IOException e) {
            log.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
