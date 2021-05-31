package com.mickey.generator.task;

import com.mickey.core.exception.NoveSystemException;
import com.mickey.generator.core.AbstractTask;
import com.mickey.generator.core.ApplicationContext;
import com.mickey.generator.entity.EntityClass;
import com.mickey.generator.entity.MickeyConfig;
import com.mickey.generator.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author J·K
 * @Description: service实现类
 * @date 2020/3/27 7:49 下午
 */
@Slf4j
public class ServiceImplTask extends AbstractTask {
    private static String IMPL_FLT_NAME = "serviceImpl.ftl";

    @Override
    protected boolean doInternal(ApplicationContext context) {
//        log.info("serviceImpl task start");
        MickeyConfig config = (MickeyConfig) context.getAttribute("config");
        List<EntityClass> entitys = (List<EntityClass>) context.getAttribute("entitys");
        entitys.forEach(entityClass -> {
            exec(config, entityClass);
        });
//        log.info("serviceImpl task end");
        return true;
    }

    private void exec(MickeyConfig config, EntityClass entityClass) {
        String type = config.getType().toString().toLowerCase();

        String serviceName = entityClass.getTableAlias() + config.getServiceSuffix();
        String mapperName = entityClass.getTableAlias() + config.getMapperSuffix();

        String implName = entityClass.getTableAlias() + config.getServiceImplSuffix();

        Map<String, Object> data = new HashMap<>();
        data.put("package", entityClass.getBasePackage() + "." + config.getChildPackageImpl());
        data.put("entityPackage", entityClass.getAllPackage() + "." + entityClass.getClassName());
        data.put("type", type);
        data.put("servicePackage", entityClass.getBasePackage() + "." + config.getChildPackageService() + "." + serviceName);
        data.put("tableName", entityClass.getTableName());
        data.put("author", System.getProperty("user.name"));
        data.put("time", time);
        data.put("iocName", ConvertUtils.lowerFirst(serviceName));
        data.put("implName", implName);
        data.put("entityName", entityClass.getClassName());
        data.put("serviceName", serviceName);
        if (config.getType().equals(MickeyConfig.TypeEnum.MAPPER)) {
            IMPL_FLT_NAME = "serviceXmlImpl.ftl";
            data.put("mapperPackage", entityClass.getBasePackage() + "." + config.getChildPackageMapper() + "." + mapperName);
            data.put("mapperName", mapperName);
        }
        else if (config.getType().equals(MickeyConfig.TypeEnum.MYBATIS_PLUS)) {
            IMPL_FLT_NAME = "mybatisPlusServiceImpl.ftl";
            data.put("mapperPackage", entityClass.getBasePackage() + "." + config.getChildPackageMapper() + "." + mapperName);
            data.put("mapperName", mapperName);
        }
        else if (config.getType().equals(MickeyConfig.TypeEnum.MYBATIS_PLUS_EXT)) {
            IMPL_FLT_NAME = "mybatisPlusServiceImpl-Ext.ftl";
            data.put("mapperPackage", entityClass.getBasePackage() + "." + config.getChildPackageMapper() + "." + mapperName);
            data.put("mapperName", mapperName);
        }

        File file = new File(
            config.getProjectPath() +
                config.getSaveBasePath() +
                config.getPackagePathImpl() + implName + ".java");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            freemarker.template.Configuration cfg = getConfiguration();
            cfg.getTemplate(IMPL_FLT_NAME).process(data,
                new FileWriter(file));
        } catch (Exception e) {
            log.error("{}", e);
            throw new NoveSystemException("500", "XML文件生成报错");
        }
        log.info("{}.java 生成成功", implName);
    }
}
