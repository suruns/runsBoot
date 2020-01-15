package com.suruns.runsSpringBoot.exception;

import com.suruns.runsSpringBoot.common.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Author: suruns
 * @Date: 2020年01月14日 16:48
 * @Desc:
 */
public class DefaultErrorResponseFactory implements ConfigurationErrorResponseFactory {

    private HashMap<String, ErrorResponse> responseHashMap = new HashMap<>(256);

    @Override
    public void registerErrorResponse(String name, ErrorResponse response) {
        this.responseHashMap.put(name, response);
    }

    @Override
    public void registerDefaultErrorResponse(ErrorResponse response) {
        this.responseHashMap.put(ExceptionInheritUtil.EXCEPTION_CLASS_NAME, response);
    }

    @Override
    public ErrorResponse getErrorResponse(Exception e, HttpServletRequest request, HttpServletResponse response) {
        return responseHashMap.get(ExceptionInheritUtil.getLastInherit(responseHashMap.keySet(), e));
    }
}
