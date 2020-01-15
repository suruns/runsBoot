package com.suruns.runsSpringBoot.exception;

import com.suruns.runsSpringBoot.common.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: suruns
 * @Date: 2019年02月25日 10:42
 * @Desc: 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @Autowired
    private ErrorResponseFactory errorResponseFactory;

    /**
     * 其他时异常
     *
     * @param e        异常请求
     * @param request  请求信息
     * @param response 响应信息
     * @return 封装后的响应异常信息
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ErrorResponse exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        return errorResponseFactory.getErrorResponse(e, request, response);
    }
}
