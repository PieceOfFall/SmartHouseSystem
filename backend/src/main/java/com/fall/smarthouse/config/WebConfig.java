package com.fall.smarthouse.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author FAll
 * @date 2022/12/2 15:48
 */
@Configuration
@EnableSwagger2
public class WebConfig implements WebMvcConfigurer {

    /**
     * @author FAll
     * @description Swagger配置
     * @return: springfox.documentation.spring.web.plugins.Docket
     * @date 2022/12/3 11:44
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fall.smarthouse.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @author FAll
     * @description 允许跨域请求配置
     * @param registry
     * @date 2022/12/3 11:48
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOriginPatterns("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

    /**
     * @author FAll
     * @description swagger页面信息配置
     * @return: springfox.documentation.service.ApiInfo
     * @date 2022/12/3 11:45
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("539智能家居接口文档")
                .description("提供前端使用接口")
                .contact(new Contact("FAll", "", "940313262@qq.com"))
                .version("1.0")
                .build();
    }

}
