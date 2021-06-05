package com.mickey.mybatis.plus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.mickey.mybatis.plus.injector.methods.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author J·K
 * @description: 自定义方法SQL注入器
 * @date 2021/6/4 10:03 上午
 */
@NoArgsConstructor
@AllArgsConstructor
public class MickeySqlInjector extends DefaultSqlInjector {

    @Setter
    @Accessors(chain = true)
    private Predicate<TableFieldInfo> predicate;

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = Stream.of(
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

        methodList.add(new InsertMethod(predicate));
        methodList.add(new InsertBatchMethod(predicate));
        methodList.add(new UpdateBatchMethod(predicate));
        methodList.add(new UpdateByIdMethod(predicate));
        return methodList;
    }
}
