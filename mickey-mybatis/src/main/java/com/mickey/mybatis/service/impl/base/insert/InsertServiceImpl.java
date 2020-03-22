package com.mickey.mybatis.service.impl.base.insert;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.annotation.Id;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.service.impl.base.delete.DeleteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author J·K
 * @Description: InsertServiceImpl
 * @date 2020/3/22 3:41 下午
 */
@Slf4j
public abstract class InsertServiceImpl<T extends BasePo> extends DeleteServiceImpl<T> {
    @Override
    @Transactional
    public int insert(BasePo entity, IDataSource... args) {
        int effectRow = super.getBaseDao(args).insert(entity);
        return this.RetIdOrEffectRow(entity, effectRow);
    }

    @Override
    @Transactional
    public int insertList(List<T> list, IDataSource... args) {
        return super.getBaseDao(args).insert(list);
    }

    @Override
    @Transactional
    public <E> int insert(String statementPostfix, E entity, IDataSource... args) {
        int effectRow = super.getBaseDao(args).insert(statementPostfix, entity);
        return RetIdOrEffectRow(entity, effectRow);
    }

    @Override
    @Transactional
    public <E> int insertList(String statementPostfix, List<E> list, IDataSource... args) {
        return super.getBaseDao(args).insert(statementPostfix, list);
    }

    public <E> Integer RetIdOrEffectRow(E entity, int effectRow) {
        if (entity instanceof BasePo) {
            Class<?> cls = entity.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Id annotation = field.getAnnotation(Id.class);
                if (annotation != null) {
                    try {
                        return Integer.parseInt(field.get(entity).toString());
                    } catch (Exception e) {
                        log.error("获取主键值报错", e);
                        return effectRow;
                    }
                }
            }
        }
        return effectRow;
    }
}
