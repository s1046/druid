package com.defyer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * defyer
 **/
@Data
@ConfigurationProperties("spring.datasource.druid")
public class DruidConfig {

    protected volatile int                             initialSize                               = 1;
    protected volatile int                             maxActive                                 = 1;
    protected volatile int                             minIdle                                   = 1;
    protected volatile int                             maxIdle                                   = 1;
    protected volatile long                            maxWait                                   = 1;

}