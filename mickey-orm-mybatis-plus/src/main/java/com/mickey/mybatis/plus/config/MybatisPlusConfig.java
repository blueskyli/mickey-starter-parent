package com.mickey.mybatis.plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.mickey.mybatis.plus.injector.MickeySqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public MickeySqlInjector mickeySqlInjector() {
        return new MickeySqlInjector();
    }
}
