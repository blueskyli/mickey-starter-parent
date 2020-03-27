package com.mickey.generator.core;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 当前应用程序上下文
 */
@Data
public abstract class ApplicationContext
{
    protected Map<String, Object> ctx = new ConcurrentHashMap<>();
    
    public abstract void setAttribute(String key, Object obj);
    
    public abstract Object getAttribute(String key);
    
}
