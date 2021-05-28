<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#assign right="{" />
<#assign left="}" />
<#assign pk=pkName />
<mapper namespace="${xmlNameSpace}">

  <!-- 通用查询映射结果 -->
  <resultMap id="BaseResultMap" type="${entityPath}">
    <#list fields as field>
      <#if field.fieldName == pk>
        <#assign pkPropName="${field.fieldName}" />
        <#assign pkColumnName="${field.columnName}" />
        <id column="${field.columnName}" property="${field.fieldName}"/>
      <#else>
        <result column="${field.columnName}" property="${field.fieldName}"/>
      </#if>
    </#list>
  </resultMap>

</mapper>