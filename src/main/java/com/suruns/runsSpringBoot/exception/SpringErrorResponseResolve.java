package com.suruns.runsSpringBoot.exception;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: suruns
 * @Date: 2020年01月14日 15:06
 * @Desc:
 */
public class SpringErrorResponseResolve implements ErrorResponseResolve{

    private PathMatchingResourcePatternResolver pathResolver;

    public SpringErrorResponseResolve(){
        this.pathResolver = new PathMatchingResourcePatternResolver();
    }

    @Override
    public InputStream getResource(String path) throws IOException {
        return this.pathResolver.getResource(path).getInputStream();
    }
}
