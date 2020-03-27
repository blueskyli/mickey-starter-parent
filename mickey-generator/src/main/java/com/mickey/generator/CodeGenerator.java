package com.mickey.generator;

import com.alibaba.druid.pool.DruidDataSource;
import com.mickey.generator.core.Application;
import com.mickey.generator.core.ApplicationContext;
import com.mickey.generator.core.SimpleApplicationContext;
import com.mickey.generator.core.Task;
import com.mickey.generator.entity.MickeyConfig;
import com.mickey.generator.task.*;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author J·K
 * @Description: 启动程序
 * @date 2020/3/27 12:00 下午
 */
@Data
public class CodeGenerator {
    private ApplicationContext context = new SimpleApplicationContext();
    private MickeyConfig config = new MickeyConfig();
    private DruidDataSource dataSource;
    private Application application;
    //是否自定义生成类
    private boolean customDefined = false;

    {
        application = new Application(context, CodeGenerator.class.getSimpleName());
        application
            .registerTask(InitTask.class)
            .registerTask(EntityTask.class);
    }

    public CodeGenerator(DruidDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CodeGenerator(DruidDataSource dataSource, MickeyConfig config) {
        this.dataSource = dataSource;
        this.config = config;
    }

    public void start() {
        context.setAttribute("dataSource", dataSource);
        //设置配置信息
        context.setAttribute("config", config);
        if(!customDefined) {
            application
                .registerTask(XmlTask.class)
                .registerTask(MapperTask.class)
                .registerTask(ServiceTask.class)
                .registerTask(ServiceImplTask.class);
        }
        application.start();
    }

    @SneakyThrows
    public CodeGenerator registerTask(Class<? extends Task> clazz) {
        customDefined = true;
        application.registerTask(clazz);
        return this;
    }
}
