package com.suruns.runsSpringBoot.exception;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: suruns
 * @Date: 2020年01月15日 09:41
 * @Desc:
 */
@Data
@ConfigurationProperties(prefix = "runs.error-response")
public class ErrorResponseProperties {
    private String location;
}
