package com.example;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
//mybatis-plus自动生成代码代码
public class CodeGenerator {

    public static void main(String[] args) {
        System.out.println("请输入表格名,多个表格名用逗号隔开:");
        Scanner scan = new Scanner(System.in);
        String tableName = scan.nextLine();

        AutoGenerator map = new AutoGenerator();
        /**
         * 数据源配置
         */
        map.setDataSource(new DataSourceConfig()
                .setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("123456"));
        // 全局配置
        map.setGlobalConfig(new GlobalConfig()
                .setAuthor("Fly")
                .setOpen(false)
                .setOutputDir("D://Test//src//main//java"));
        // 包配置
        map.setPackageInfo(new PackageConfig()
                .setParent("com.example")
                .setEntity("bo")
                .setMapper("mapper")
                .setService("service").setServiceImpl("service.impl").setController("controller"));
        // 策略配置
        map.setStrategy(new StrategyConfig()
                        .setNaming(NamingStrategy.underline_to_camel)
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        .setEntityLombokModel(true)
                        .setChainModel(true)
                        .setRestControllerStyle(true)
                        .setInclude(tableName));
        //模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
        map.setTemplateEngine(new FreemarkerTemplateEngine());
        map.execute();
        /*AutoGenerator.create("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8",
                        "root", "123456")
                // 全局配置
                .globalConfig(builder -> builder.author("Tom").fileOverride().outputDir("D://Test//src//main//java"))
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.example")
                            .entity("bo")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,"D://Test//src//main//resources//mapper"));
                })
                // 策略配置
                .strategyConfig(builder -> {
                    //需要生成的表名
                    builder.addInclude("user")
                            .addTablePrefix("sys_");
                })
                *//*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine()) *//*
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();*/


    }
    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
