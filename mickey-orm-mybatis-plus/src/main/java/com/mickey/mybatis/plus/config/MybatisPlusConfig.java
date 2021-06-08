package com.mickey.mybatis.plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.mickey.mybatis.plus.injector.MickeySqlInjector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;

/**
 * @author J·K
 * @description: MybatisPlusConfig
 * @date 2021/5/28 1:44 下午
 */
@Configuration
public class MybatisPlusConfig {

    public MybatisPlusConfig(MybatisPlusProperties mybatisPlusProperties) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
            .setDbConfig(new GlobalConfig.DbConfig()
                .setLogicDeleteField(LogicDeleteConfig.delFiledName)
                .setLogicDeleteValue(LogicDeleteConfig.delValue)
                .setLogicNotDeleteValue(LogicDeleteConfig.notDelValue));
        mybatisPlusProperties.setGlobalConfig(globalConfig);
        mybatisPlusProperties.setMapperLocations(new String[]{"classpath*:/mybatis/**/*.xml"});
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //攻击 SQL 阻断解析器,防止全表更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean(MickeySqlInjector.class)
    public MickeySqlInjector mickeySqlInjector() {
        Predicate<TableFieldInfo> predicate = x -> !(x.getProperty().equals("createTime") || x.getProperty().equals("updateTime"));
        return new MickeySqlInjector(predicate);
    }
}
