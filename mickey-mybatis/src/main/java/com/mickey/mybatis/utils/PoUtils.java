package com.mickey.mybatis.utils;

import com.mickey.core.exception.NoveSystemException;
import com.mickey.model.po.BasePo;

import javax.persistence.Id;
import java.lang.reflect.Field;

/**
 * @author J·K
 * @Description: Po工具类
 * @date 2020/3/24 10:38 上午
 */
public class PoUtils {

    /**
     * 根据PO中添加的@Id返回主键值，如有多个的只返回第一个
     * @param entity
     * @param effectRows
     * @param <E>
     * @return
     */
    public static  <E> Integer RetId(E entity, int effectRows) {
        if(effectRows <= 0){
            throw new NoveSystemException("500","插入数据异常");
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
                        throw new NoveSystemException("500","获取主键值报错");
                    }
                }
            }
            if(null == annotation){
                throw new NoveSystemException("500","实体未设置主键");
            }
        }
        throw new NoveSystemException("500","暂不支持的实体类型");
    }
}
