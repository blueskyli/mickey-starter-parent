package com.mickey.generator.task;

import com.mickey.core.exception.NoveSystemException;
import com.mickey.generator.core.AbstractTask;
import com.mickey.generator.core.ApplicationContext;
import com.mickey.generator.entity.EntityClass;
import com.mickey.generator.entity.MickeyConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author J·K
 * @Description: Mapper接口类
 * @date 2020/3/27 7:31 下午
 */
@Slf4j
public class MapperTask extends AbstractTask {

    private static String IMPL_FLT_NAME = "mapper.ftl";

    @Override
    protected boolean doInternal(ApplicationContext context) {
        MickeyConfig config = (MickeyConfig) context.getAttribute("config");
        if (config.getType().equals(MickeyConfig.TypeEnum.PO)) {
            return true;
        }
//        log.info("mapper task start");
        List<EntityClass> entitys = (List<EntityClass>) context.getAttribute("entitys");
        entitys.forEach(entityClass -> {
            exec(config, entityClass);
        });
//        log.info("mapper task end");
        return true;
    }

    private void exec(MickeyConfig config, EntityClass entityClass) {
        String mapperName = entityClass.getTableAlias() + config.getMapperSuffix();

        Map<String, Object> data = new HashMap<>();
        data.put("package", entityClass.getBasePackage() + "." + config.getChildPackageMapper());
        data.put("entityPackage", entityClass.getAllPackage() + "." + entityClass.getClassName());
        data.put("tableName", entityClass.getTableName());
        data.put("author", System.getProperty("user.name"));
        data.put("time", time);
        data.put("mapperName", mapperName);
        data.put("entityName", entityClass.getClassName());
        if (config.getType().equals(MickeyConfig.TypeEnum.MYBATIS_PLUS)) {
            IMPL_FLT_NAME = "mybatisPlusMapper.ftl";
        }else if (config.getType().equals(MickeyConfig.TypeEnum.MYBATIS_PLUS_EXT)) {
            IMPL_FLT_NAME = "mybatisPlusMapper-Ext.ftl";
        }

        File file = new File(
            config.getProjectPath() +
                config.getSaveBasePath() +
                config.getPackagePathMapper() + mapperName + ".java");
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
        log.info("{}.java 生成成功",mapperName);
    }
}
