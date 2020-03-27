package com.mickey.generator.core;

/**
 * @author J·K
 * @Description: 任务链表
 * @date 2020/3/27 11:13 上午
 */
public interface Task {
    /**
     * 执行
     * @param context
     * @return
     */
    boolean perform(ApplicationContext context);

    /**
     * 是否有下一个
     * @return
     */
    boolean hasNext();

    /**
     * 注册下一个任务
     * @param nextTask
     */
    void registerNextTask(Task nextTask);

    /**
     * 获取下一个任务
     * @return
     */
    Task next();
}
