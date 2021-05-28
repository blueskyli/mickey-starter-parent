package com.mickey.generator.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

/**
 * @author J·K
 * @Description: 代码生成配置类
 * @date 2020/3/27 12:47 下午
 */
@Data
@Accessors(chain=true)
public class MickeyConfig {
    /**
     * po OR mapper
     */
    private TypeEnum type = TypeEnum.PO;
    /**
     * tableName,多个表的话 逗号分隔
     */
    private Set<String> tableNames = Sets.newHashSet();
    /**
     * 生成代码的主包
     */
    private String basePackage = "com.xxx.example";
    /**
     * 项目在硬盘上的基础路径
     * 建议传递:System.getProperty("user.dir")
     */
    private String projectPath = System.getProperty("user.dir");

    /**
     * 实体类后缀
     */
    private String entitySuffix = "Po";
    /**
     * Mapper\xml后缀
     */
    private String mapperSuffix = "Mapper";
    /**
     * service后缀
     */
    private String serviceSuffix = "Service";
    /**
     * serviceImpl后缀
     */
    private String serviceImplSuffix = "ServiceImpl";

    /**
     * 生成文件存放父路径
     */
    private String saveBasePath = "/src/test/java";

    /**
     * 生成的po存放路径
     */
    private String packagePathPo = "/generator/po/";
    /**
     * 生成的po存放路径
     */
    private String packagePathXml = "/generator/xml/";
    /**
     * 生成的service存放路径
     */
    private String packagePathService = "/generator/service/api/";
    /**
     * 生成的serviceImpl存放路径
     */
    private String packagePathImpl = "/generator/service/impl/";
    /**
     * 生成的mapper存放路径
     */
    private String packagePathMapper = "/generator/mapper/";

    /**
     * entity package
     * eg：basePackage + child_package_po
     *    com.xxx.example.entity.po
     */
    private String childPackagePo = "entity.po";
    /**
     * service 接口 package
     */
    private String childPackageService = "service.api";
    /**
     * service 实现类 package
     */
    private String childPackageImpl = "service.impl";
    /**
     * mapper package
     */
    private String childPackageMapper = "mapper";

    /**
     * po OR mapper
     */
    public enum TypeEnum{
        PO,
        MAPPER,
        MYBATIS_PLUS,
        MYBATIS_PLUS_DEFINED
    }
}
