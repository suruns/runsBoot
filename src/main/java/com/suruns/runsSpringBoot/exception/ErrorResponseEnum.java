package com.suruns.runsSpringBoot.exception;

import com.suruns.runsSpringBoot.common.ErrorResponse;
import com.suruns.runsSpringBoot.common.RunsResponse;

/**
 * @Author: suruns
 * @Date: 2020年01月14日 10:09
 * @Desc:
 */
public enum  ErrorResponseEnum implements ErrorResponse {
    /**
     * 异常类型枚举
     */
    UNAUTHORIZED(10001, "UNAUTHORIZED"),
    INTERNET_SERVER_ERROR(10002, "INTERNET_SERVER_ERROR");

    private Object code;
    private Object message;

    ErrorResponseEnum(Object code, Object message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Object getCode() {
        return this.code;
    }

    @Override
    public Object getMessage() {
        return this.message;
    }

    public RunsResponse getRunsResponse(){
        return RunsResponse.builder().instance(this).build();
    }
}
