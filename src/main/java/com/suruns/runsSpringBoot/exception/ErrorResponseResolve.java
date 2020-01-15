package com.suruns.runsSpringBoot.exception;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: suruns
 * @Date: 2020年01月14日 15:04
 * @Desc: 资源解析器
 */
public interface ErrorResponseResolve {
    /**
     * 根据路径获取文件资源
     * @param path
     * @return
     */
    InputStream getResource(String path) throws IOException;
}
