package com.suruns.runsSpringBoot.exception;

import java.io.InputStream;

/**
 * @Author: suruns
 * @Date: 2020年01月14日 16:52
 * @Desc:
 */
public interface ErrorResponseReader  {

    void registerErrorResponse(InputStream in);
}
