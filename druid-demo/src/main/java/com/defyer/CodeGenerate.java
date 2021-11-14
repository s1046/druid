package com.defyer;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * defyer
 **/
public class CodeGenerate {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:wrap-jdbc:jdbc:mysql://192.168.58.132/da_dmp_tidb", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("defyer") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\mydeveloperworkspace\\ideaspace\\druid-master\\druid-demo\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.defyer") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\mydeveloperworkspace\\ideaspace\\druid-master\\druid-demo\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("table1");// 设置需要生成的表名

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}