package com.mickey.mybatis.plus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.mickey.mybatis.plus.injector.methods.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author J·K
 * @description: 自定义方法SQL注入器
 * @date 2021/6/4 10:03 上午
 */
public class MickeySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        Predicate<TableFieldInfo> predicate = x -> !(x.getProperty().equals("createTime") || x.getProperty().equals("updateTime"));
        List<AbstractMethod> methodList = Stream.of(
            new Delete(),
            new DeleteByMap(),
            new DeleteById(),
            new DeleteBatchByIds(),
            new SelectById(),
            new SelectBatchByIds(),
            new SelectByMap(),
            new SelectOne(),
            new SelectCount(),
            new SelectMaps(),
            new SelectMapsPage(),
            new SelectObjs(),
            new SelectList(),
            new SelectPage()).collect(Collectors.toList());

        methodList.add(new InsertBatchMethod(predicate));
        methodList.add(new UpdateBatchMethod(predicate));
        methodList.add(new UpdateByIdMethod(predicate));
        methodList.add(new InsertMethod(predicate));
        methodList.add(new UpdateMethod(predicate));
        return methodList;
    }
}
