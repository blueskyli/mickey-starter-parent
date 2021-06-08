package com.mickey.generator.task;

import com.mickey.core.exception.NoveSystemException;
import com.mickey.generator.core.AbstractTask;
import com.mickey.generator.core.ApplicationContext;
import com.mickey.generator.entity.EntityClass;
import com.mickey.generator.entity.MickeyConfig;
import com.mickey.generator.entity.XmlClass;
import com.mickey.generator.handler.XmlHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author J·K
 * @Description: xml文件
 * @date 2020/3/27 7:02 下午
 */
@Slf4j
public class XmlTask extends AbstractTask {

    private static String IMPL_FLT_NAME = "xml.ftl";

    @Override
    protected boolean doInternal(ApplicationContext context) {
//        log.info("xml task start");
        MickeyConfig config = (MickeyConfig) context.getAttribute("config");
        List<EntityClass> entitys = (List<EntityClass>) context.getAttribute("entitys");
        entitys.forEach(entityClass -> {
            String xmlNameSpace = "";
            //此处路径为po或者mapper
            if(config.getType().equals(MickeyConfig.TypeEnum.PO)) {
                xmlNameSpace = entityClass.getAllPackage() + "." +entityClass.getClassName();
            } else {
                String mapperName = entityClass.getTableAlias() + config.getMapperSuffix();
                xmlNameSpace = entityClass.getBasePackage() + "." + config.getChildPackageMapper() + "." + mapperName;
            }
            XmlClass xmlClass = XmlHandler.combineInfo(entityClass,xmlNameSpace);

            exec(config,entityClass, xmlClass);
        });
//        log.info("xml task end");
        return true;
    }

    private void exec(MickeyConfig config,EntityClass entityClass, XmlClass xmlClass) {
        String xmlName = entityClass.getTableAlias() + config.getMapperSuffix();

        Map<String, Object> data = new HashMap<>();
        data.put("xmlNameSpace", xmlClass.getXmlNameSpace());
        data.put("entityPath", xmlClass.getEntityPath());
        data.put("tableName", xmlClass.getTableName());
        data.put("pkName", xmlClass.getPkName());
        data.put("fields", xmlClass.getFields());
        if (config.getType().equals(MickeyConfig.TypeEnum.MYBATIS_PLUS) || config.getType().equals(MickeyConfig.TypeEnum.MYBATIS_PLUS_EXT)) {
            IMPL_FLT_NAME = "mybatisPlusXml.ftl";
        }

        File file = new File(
            config.getProjectPath() +
                config.getSaveBasePath() +
                config.getPackagePathXml() + xmlName + ".xml");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            freemarker.template.Configuration cfg = getConfiguration();
            cfg.getTemplate(IMPL_FLT_NAME).process(data,
                new FileWriter(file));
        } catch (Exception e) {
            log.error("{}",e);
            throw new NoveSystemException("500","XML文件生成报错");
        }
        log.info("{}.xml 生成成功",xmlName);
    }
}
