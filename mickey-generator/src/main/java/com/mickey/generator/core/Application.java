package com.mickey.generator.core;

import com.google.common.collect.Lists;
import com.mickey.core.exception.NoveSystemException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * @author J·K
 * @Description: 启动程序
 * @date 2020/3/27 11:10 上午
 */
@Slf4j
@Data
public class Application {
    //当前上下文
    private ApplicationContext context;
    //应用名称
    private String applicationName;
    //开始执行时间
    private Date startTime;
    //执行结束时间
    private Date endTime;
    //花费时长
    private long excuteTime;
    //任务列表
    private List<Task> list = Lists.newArrayList();

    public Application(ApplicationContext context) {
        this.context = context;
        this.applicationName = Application.class.getSimpleName();
    }

    public Application(ApplicationContext context, String applicationName) {
        this.context = context;
        this.applicationName = applicationName;
    }

    public void start() {
        this.startTime = new Date();
        startTask();
        this.endTime = new Date();
        this.excuteTime = (endTime.getTime() - startTime.getTime());
        log.info("应用程序{}执行总耗时:{}ms", this.applicationName, this.excuteTime);
    }

    public Application registerTask(Class<? extends Task> clazz) {
        Task task = null;
        try {
            task = clazz.newInstance();
        } catch (Exception e) {
            throw new NoveSystemException("500","获取task实例报错");
        }
        return this.registerTask(task);
    }

    public Application registerTask(Task task) {
        if (null == task || list.contains(task)) {
            log.info("注册的类{}为空，或者已经存在",task.getClass().getSimpleName());
            return this;
        }
        if (hasTasks()) {
            val lastTask = list.get(list.size() - 1);
            lastTask.registerNextTask(task);
        }
        this.list.add(task);
        return this;
    }

    private boolean hasTasks() {
        return !list.isEmpty();
    }

    private void startTask() {
        if (hasTasks()) {
            // 是否执行成功
            boolean complate = true;
            Task task = list.get(0);
            complate = task.perform(this.context);
            while (complate && task.hasNext()) {
                task = task.next();
                complate = task.perform(this.context);
            }
            if (!complate) {
                log.error("任务执行出错！当前执行任务为：{}", task.getClass().getName());
            }
        } else {
            log.error("未给当前应用程序注册执行任务");
        }
    }
}
