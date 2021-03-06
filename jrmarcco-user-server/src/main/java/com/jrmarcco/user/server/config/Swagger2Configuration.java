package com.jrmarcco.user.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hongjc
 * @version 1.0  2019/1/12
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    private static final String BASE_PACKAGES = "com.jrmarcco.user";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGES))
                .paths(PathSelectors.any())
                .build();
    }

    // ====================================================================================================
    //                                   Private Method
    // ====================================================================================================
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Jrmarcco User Server Restful Apis")
                .description("用户中心Api文档")
                .version("1.0-SNAPSHOT")
                .build();
    }
}
