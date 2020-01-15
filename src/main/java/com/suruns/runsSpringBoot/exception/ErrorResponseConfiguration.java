package com.suruns.runsSpringBoot.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Author: suruns
 * @Date: 2020年01月15日 09:40
 * @Desc:
 */
@Configuration
public class ErrorResponseConfiguration {

    @Bean
    public ErrorResponseProperties errorResponseProperties(){
        return new ErrorResponseProperties();
    }

    @Bean
    public ErrorResponseFactory errorResponseFactory(ErrorResponseProperties errorResponseProperties) throws IOException {
        ErrorResponseFactory responseFactory = new DefaultErrorResponseFactory();
        ErrorResponseResolve responseResolve = new SpringErrorResponseResolve();
        ErrorResponseReader responseReader = new DefaultErrorResponseReader((ConfigurationErrorResponseFactory) responseFactory);
        responseReader.registerErrorResponse(responseResolve.getResource(errorResponseProperties.getLocation()));
        return responseFactory;
    }
}
