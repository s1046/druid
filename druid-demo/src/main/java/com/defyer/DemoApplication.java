package com.defyer;

import com.defyer.config.DruidConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages ={"com.defyer.system.mapper"} )
@EnableConfigurationProperties(DruidConfig.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    public DruidConfig druidConfig(){
        DruidConfig druidConfig= new DruidConfig();
        return druidConfig;
    }

}
