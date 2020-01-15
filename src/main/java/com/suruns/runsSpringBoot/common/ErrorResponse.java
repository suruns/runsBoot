package com.suruns.runsSpringBoot.common;

/**
 * @Author: suruns
 * @Date: 2020年01月14日 10:02
 * @Desc:
 */
public interface ErrorResponse {
    /**
     * 错误码
     * @return
     */
    Object getCode();

    /**
     * 错误信息
     * @return
     */
    Object getMessage();
}
