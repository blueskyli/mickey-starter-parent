package com.mickey.mybatis.plus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.mickey.mybatis.plus.injector.methods.InsertBatchMethod;
import com.mickey.mybatis.plus.injector.methods.UpdateBatchMethod;

import java.util.List;

/**
 * @author J·K
 * @description: 自定义方法SQL注入器
 * @date 2021/6/4 10:03 上午
 */
public class MickeySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new InsertBatchMethod(x->!(x.getProperty().equals("createTime") || x.getProperty().equals("updateTime"))));
        methodList.add(new UpdateBatchMethod(x->!(x.getProperty().equals("createTime") || x.getProperty().equals("updateTime"))));
        return methodList;
    }
}
