package com.mickey.generator.core;

import com.mickey.generator.utils.DateUtils;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * @author J·K
 * @Description: 任务抽象类
 * @date 2020/3/27 11:18 上午
 */
public abstract class AbstractTask implements Task {
    private static final String TEMPLATE_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/ftl";//模板位置
    public static final String time = DateUtils.getFormatDate(new Date(), "yyyy年MM月dd日");

    private Task nextTask= null;
    private boolean hasNext = false;

    public freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    protected abstract boolean doInternal(ApplicationContext context);

    @Override
    public boolean perform(ApplicationContext context) {
        return doInternal(context);
    }

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public void registerNextTask(Task nextTask) {
        this.nextTask = nextTask;
        this.hasNext = !(null == nextTask);
    }

    @Override
    public Task next() {
        return this.nextTask;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }
}
