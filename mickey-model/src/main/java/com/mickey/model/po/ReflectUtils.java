package com.mickey.model.po;

import com.mickey.core.exception.NoveSystemException;

import javax.persistence.Id;
import java.lang.reflect.Field;

/**
 * @author J·K
 * @Description: Po工具类
 * @date 2020/3/24 10:38 上午
 */
public class ReflectUtils {

    /**
     * 给实体Po设置主键值
     *
     * @param entity
     * @param primaryKey
     * @param <T>
     */
    public static <T> void SetPrimaryKey(T entity, Integer primaryKey) {
        SetVal(entity,primaryKey,false);
    }

    /**
     * 组合逻辑删除实体
     * @param entity
     * @param primaryKey
     * @param <T>
     */
    public static <T> void SetDelVal(T entity, Integer primaryKey) {
        SetVal(entity,primaryKey,true);
    }

    /**
     * 给实体Po设置值
     *
     * @param entity
     * @param primaryKey
     * @param <T>
     */
    public static <T> void SetVal(T entity, Integer primaryKey,boolean isDel) {
        if (primaryKey == null) {
            throw new NoveSystemException("500", "SetPrimaryKey->primaryKey不能为null");
        }
        if (entity instanceof BasePo) {
            Class<?> cls = entity.getClass();
            Field[] fields = cls.getDeclaredFields();
            Id annotation = null;
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                annotation = field.getAnnotation(Id.class);
                if (annotation != null) {
                    try {
                        field.set(entity,primaryKey);
                    } catch (Exception e) {
                        throw new NoveSystemException("500", "设置主键值报错");
                    }
                }
                if(isDel && field.getName().equals("delFlag")){
                    try {
                        field.set(entity,true);
                    } catch (Exception e) {
                        throw new NoveSystemException("500", "设置delFlag报错");
                    }
                }
            }
        }else {
            throw new NoveSystemException("500", "暂不支持的实体类型");
        }
    }

    /**
     * 根据PO中添加的@Id返回主键值，如有多个的只返回第一个
     *
     * @param entity
     * @param effectRows
     * @param <T>
     * @return
     */
    public static <T> Integer RetId(T entity, int effectRows) {
        if (effectRows <= 0) {
            throw new NoveSystemException("500", "插入数据异常");
        }
        if (entity instanceof BasePo) {
            Class<?> cls = entity.getClass();
            Field[] fields = cls.getDeclaredFields();
            Id annotation = null;
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                annotation = field.getAnnotation(Id.class);
                if (annotation != null) {
                    try {
                        return Integer.parseInt(field.get(entity).toString());
                    } catch (Exception e) {
                        throw new NoveSystemException("500", "获取主键值报错");
                    }
                }
            }
            if (null == annotation) {
                throw new NoveSystemException("500", "实体未设置主键");
            }
        }
        throw new NoveSystemException("500", "暂不支持的实体类型");
    }
}
