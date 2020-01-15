package com.suruns.runsSpringBoot.exception;

import com.suruns.runsSpringBoot.common.ErrorResponse;

/**
 * @Author: suruns
 * @Date: 2020年01月14日 16:55
 * @Desc:
 */
public interface ConfigurationErrorResponseFactory extends ErrorResponseFactory {
    void registerErrorResponse(String name, ErrorResponse response);

    void registerDefaultErrorResponse(ErrorResponse response);
}
