package com.mickey.generator.handler;

import com.mickey.generator.entity.EntityClass;
import com.mickey.generator.entity.XmlClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author J·K
 * @Description: xml文件生产
 * @date 2020/3/26 2:39 下午
 */
@Slf4j
public class XmlHandler {

    /**
     * 组装xml信息
     * @param entityClass
     * @param nameSpace xml namespace
     * @return
     */
    public static XmlClass combineInfo(EntityClass entityClass, String nameSpace)
    {
        XmlClass xmlClass = new XmlClass();
        xmlClass.setXmlNameSpace(nameSpace);
        xmlClass.setEntityPath(entityClass.getAllPackage() + "." + entityClass.getClassName());
        xmlClass.setTableName(entityClass.getTableName());
        xmlClass.setPkName(entityClass.getPkName());
        xmlClass.setFields(entityClass.getFields());
        return xmlClass;
    }
}
