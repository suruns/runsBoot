package com.suruns.runsSpringBoot.exception;

import com.suruns.runsSpringBoot.common.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: suruns
 * @Date: 2020年01月14日 11:21
 * @Desc: 异常工厂
 */
public interface ErrorResponseFactory {
    /**
     * 获取异常响应
     * @param e
     * @param request
     * @param response
     * @return
     */
    ErrorResponse getErrorResponse(Exception e, HttpServletRequest request, HttpServletResponse response);
}
