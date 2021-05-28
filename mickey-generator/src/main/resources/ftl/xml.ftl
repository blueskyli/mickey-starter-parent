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

  <sql id="Table_Name">
    ${tableName}
  </sql>

  <sql id="Base_Column">
    <#list fields as field>
      <#if field_has_next>
        ${field.columnName},
      <#else>
        ${field.columnName}
      </#if>
    </#list>
  </sql>

  <sql id="Base_Where">
    <trim suffixOverrides="AND">
      <#list fields as field>
        <if test="${field.fieldName} != null">
          <#if field.columnName == "create_time" || field.columnName = "update_time">
           ${field.columnName} <![CDATA[ >= ]]> #${right}${field.fieldName}${left}
          <#else>
           ${field.columnName}=#${right}${field.fieldName}${left}
          </#if>
          <#if field_has_next>
           AND
          </#if>
        </if>
      </#list>
    </trim>
  </sql>

  <sql id="Base_Select">
    select
    <include refid="Base_Column"/>
    from
    <include refid="Table_Name"/>
    <where>
      <include refid="Base_Where"/>
    </where>
  </sql>

  <!-- 单条插入 -->
  <insert id="insert" parameterType="${entityPath}" useGeneratedKeys="true" keyProperty="${pkPropName}">
    insert into
    <include refid="Table_Name"/>
    <trim prefix="(" suffix=")" suffixOverrides=",">
      <@generateInsertColumn/>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
      <@generateInsertValue/>
    </trim>
  </insert>

  <!-- 批量插入 -->
  <insert id="insertList" parameterType="java.util.List">
    insert into
    <include refid="Table_Name"/>
    <trim prefix="(" suffix=")" suffixOverrides=",">
      <@generateInsertListColumn/>
    </trim>
    values
    <foreach collection="list" item="item" separator=",">
      <trim prefix="(" suffix=")" suffixOverrides=",">
        <@generateInsertListValue/>
      </trim>
    </foreach>
  </insert>

  <!-- 根据主键更新 -->
  <update id="update" parameterType="${entityPath}">
    update
    <include refid="Table_Name"/>
    <set>
      <#list fields as field>
        <#if field.fieldName != pk && field.columnName != "create_time" && field.columnName != "update_time">
          <if test="${field.fieldName} != null">
            ${field.columnName}=#${right}${field.fieldName}${left},
          </if>
        </#if>
      </#list>
    </set>
    where
    <#list fields as field>
      <#if field.fieldName == pk>
        ${field.columnName}=#${right}${field.fieldName}${left}
      </#if>
    </#list>
  </update>

  <!-- 根据主键批量更新 -->
  <update id="updateList" parameterType="java.util.List">
    <foreach collection="list" item="item" separator=";">
      update
      <include refid="Table_Name"/>
      <set>
        <#list fields as field>
          <#if field.fieldName != pk && field.columnName != "create_time" && field.columnName != "update_time">
            <if test="item.${field.fieldName} != null">
              ${field.columnName}=#${right}item.${field.fieldName}${left},
            </if>
          </#if>
        </#list>
      </set>
      where
      <#list fields as field>
        <#if field.fieldName == pk>
          ${field.columnName}=#${right}item.${field.fieldName}${left}
        </#if>
      </#list>
    </foreach>
  </update>

  <!--
  <delete id="delete" parameterType="${entityPath}">
      delete from
      <include refid="Table_Name"/>
      <where>
      <#list fields as field>
        <#if field.fieldName == pk>
          ${field.columnName}=#${right}${field.fieldName}${left}
        </#if>
      </#list>
      </where>
  </delete>

  <delete id="deleteList" parameterType="java.util.List">
      delete from
      <include refid="Table_Name"/>
      where ${pkColumnName} in
      <foreach collection="list" item="item" index="index" open="(" separator="," close=")">
          #${right}item.${pk}${left}
      </foreach>
  </delete>
  -->

  <!-- 查询单表符合条件总条数 -->
  <select id="count" parameterType="${entityPath}" resultType="int">
      select count(1) from
      <include refid="Table_Name"/>
      <where>
          <include refid="Base_Where"/>
      </where>
  </select>

  <!-- 查询符合条件的一条记录 -->
  <select id="selectOne" parameterType="${entityPath}" resultMap="BaseResultMap">
      <include refid="Base_Select"/>
      limit 1
  </select>

  <!-- 查询符合条件的记录 -->
  <select id="selectList" parameterType="${entityPath}" resultMap="BaseResultMap">
      <include refid="Base_Select"/>
  </select>

  <select id="selectListByIds" parameterType="java.util.List" resultMap="BaseResultMap">
      select
      <include refid="Base_Column"/>
      from
      <include refid="Table_Name"/>
      where ${pkColumnName} in
      <foreach collection="list" item="item" index="index" open="(" separator="," close=")">
          #${right}item${left}
      </foreach>
  </select>

  <!-- 查询符合条件的记录  锁定符合条件的行-->
  <select id="selectForUpdate" parameterType="${entityPath}" resultMap="BaseResultMap">
      <include refid="Base_Select"/>
      for update
  </select>

  <#macro generateInsertColumn>
    <#list fields as field>
      <#if field.columnName != "create_time" && field.columnName != "update_time">
        <if test="${field.fieldName} != null">
          ${field.columnName},
        </if>
      </#if>
    </#list>
  </#macro>

  <#macro generateInsertValue>
    <#list fields as field>
      <#if field.columnName != "create_time" && field.columnName != "update_time">
        <if test="${field.fieldName} != null">
          #${right}${field.fieldName}${left},
        </if>
      </#if>
    </#list>
  </#macro>

  <#macro generateInsertListColumn>
    <#list fields as field>
      <#if field.fieldName != pk && field.columnName != "create_time" && field.columnName != "update_time">
        ${field.columnName},
      </#if>
    </#list>
  </#macro>

  <#macro generateInsertListValue>
    <#list fields as field>
      <#if field.fieldName != pk && field.columnName != "create_time" && field.columnName != "update_time">
        #${right}item.${field.fieldName}${left},
      </#if>
    </#list>
  </#macro>
</mapper>