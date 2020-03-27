package com.mickey.generator.task;

import com.google.common.collect.Lists;
import com.mickey.core.exception.NoveSystemException;
import com.mickey.generator.core.AbstractTask;
import com.mickey.generator.core.ApplicationContext;
import com.mickey.generator.entity.Column;
import com.mickey.generator.entity.EntityClass;
import com.mickey.generator.entity.MickeyConfig;
import com.mickey.generator.entity.Table;
import com.mickey.generator.handler.EntityHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author J·K
 * @Description: 实体
 * @date 2020/3/27 12:10 下午
 */
@Slf4j
public class EntityTask extends AbstractTask {
    @Override
    protected boolean doInternal(ApplicationContext context) {
//        log.info("entity task start");
        MickeyConfig config = (MickeyConfig)context.getAttribute("config");
        List<Table> tables = (List<Table>) context.getAttribute("tables");
        List<EntityClass> entitys = Lists.newArrayList();
        tables.forEach(x->{
            List<Column> collect = x.getColumns().stream().filter(y -> y.isPrimaryKey()).collect(Collectors.toList());
            log.info("{}表中主键为：{}",x.getTableName(),x.getPkName());
            if(collect.size()==0)
                throw new NoveSystemException("500",String.format("%s表未设置主键",x.getTableName()));
            if(collect.size()>1){
                throw new NoveSystemException("500",String.format("%s表有多个主键",x.getTableName()));
            }
            EntityClass entityClass = EntityHandler.combineInfo(x, config.getBasePackage(), config.getEntitySuffix());
            entityClass.setAllPackage(entityClass.getBasePackage() + "." + config.getChildPackagePo());
            entitys.add(entityClass);
            exec(config, x, entityClass);
        });
        context.setAttribute("entitys",entitys);
//        log.info("entity task end");
        return true;
    }

    private void exec(MickeyConfig config, Table x, EntityClass entityClass) {
        Map<String, Object> data = new HashMap<>();
        data.put("package", entityClass.getAllPackage());
        data.put("tableName",x.getTableName());
        data.put("className",entityClass.getClassName());
        data.put("tableAlias",entityClass.getTableAlias());
        data.put("author", System.getProperty("user.name"));
        data.put("time", time);

        data.put("import", EntityHandler.fillImport(entityClass.getImports()));
        data.put("properties",EntityHandler.fillField(entityClass.getFields()));

        File file = new File(
            config.getProjectPath() +
            config.getSaveBasePath() +
            config.getPackagePathPo() +
            entityClass.getClassName() + ".java");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            freemarker.template.Configuration cfg = getConfiguration();
            cfg.getTemplate("entity.ftl").process(data,
                new FileWriter(file));
        } catch (Exception e) {
            log.error("{}",e);
            throw new NoveSystemException("500","实体类生成报错");
        }
        log.info("{}.java 生成成功",entityClass.getClassName());
    }

}
