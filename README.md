# mickey-starter-parent

#### 项目介绍
mickey-starter-parent

#### 软件架构

1. 本项目集成了mybatis+druid 和 先阶段流行的mybatis-plus，可自行选择使用哪一种orm框架
2. 项目中的代码生成器可对各种orm进行无缝集成，提供了四种形式的代码格式选择
3. 代码生成器对数据库有一定的限制，目前只支持**mysql数据库**且**主键为自增长**形式，**且唯一**
4. 由于mybatis-plus存在多种根据条件修改、删除的封装（**Wrapper**），基于安全原因考虑已经底层方法删除，如果需要请自行编写
5. 本项目底层会自动对create_time，update_time 2个字段在插入、修改时进行过滤，请自行设置数据库默认时间

#### 数据库demo脚本 必备字段
```sql
CREATE TABLE `tb_table` (
  `tb_table_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志：0：未删除，1：已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
  PRIMARY KEY (`tb_table_id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

```

#### 上传私服
```
versions:set -DnewVersion=2.1.50.RELEASE
deploy
versions:set -DnewVersion=2.0.1
```
#版本介紹

##正式版本
* 1.0.0 初始化项目，基本工具类
* 1.0.2 新增db和mybatis转换类型
* 1.0.3 升级commons-codec为1.14版本
* 1.0.4 解决 Illegal DefaultValue null for parameter type integer
* 1.0.5 解决mybatis xml like 注入问题
* 1.0.6 修改Resp的返回data默认值为null
* 1.0.7 修改SignUtils 排序bug 添加 Micro 注解
* 1.0.8 (未同步其他分支)`1,修改NoveSqlInterceptor中引用类型被修改问题
         2,新增PagerModel工具类
         3,修改AbstractDruidDBConfig中pagehelper配置`
* 1.0.9 修改NoveSqlInterceptor为线程安全
* 1.1.0 新增hutool依赖，新增logback-spring.xml

* 2.0.0 `1、升级springboot版本为2.3.2.RELEASE 
                springcloud Hoxton.SR8
                springcloud-alibaba 2.2.5.RELEASE
         2、修改NoveServiceException构造方法，默认传递错误值为500`
  
* 2.0.1 `1、新增BaseTest类
         2、新增openfign-okhttp项目 
         3、新增日志AOP拦截：需增加配置`
  
``` yaml
aspect:
  pointcut:
    enabled: true
    execution: execution(public * com.ecej.member.server.controller..*.*(..))
```
* 2.0.2 新增mickey-orm-mybatis-plus项目

* 2.0.3 接入skywalking,升级fastjson版本
* 2.0.4 优化mybatis-plus性能问题
* 2.0.5 修改日志AOPbug，将springboot父项目修改为springboot依赖

##试运行版本
* 1.0.1.SNAPSHOT 修改po为可配置、扩展底层方法

##测试版本
* 1.0.SNAPSHOT

## 代码生成器
```text
<dependency>
    <groupId>com.mickey</groupId>
    <artifactId>mickey-generator</artifactId>
    <version>${last.version}</version>
</dependency>

MickeyConfig config = new MickeyConfig();
config.setBasePackage("com.ecej.member.server")
.setTableNames( //不设置则生成所有表
        Sets.newHashSet("member", "member_operation_log")
)
.setType(MickeyConfig.TypeEnum.MYBATIS_PLUS_EXT); //根据项目需求选择不同枚举
new CodeGenerator(dataSource, config)
        .start();
```

## web项目使用，统一项目版本
### 原始springboot父项目
```text
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.2.RELEASE</version>
    <relativePath /> <!-- lookup parent from repository -->
</parent>
```
### 方式一
```xml
<parent>
    <groupId>com.mickey</groupId>
    <artifactId>mickey-starter-parent</artifactId>
    <version>${last.version}</version>
</parent>
```
### 方式二
```xml
<dependency>
    <groupId>com.mickey</groupId>
    <artifactId>mickey-starter-parent</artifactId>
    <version>${last.version}</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```
### 使用框架提供的功能
```xml
<dependency>
    <groupId>com.mickey</groupId>
    <artifactId>mickey-spring-boot-starter</artifactId>
</dependency>
<!-- 如果不需要mybatis-plus则可以不引用 -->
<dependency>
    <groupId>com.mickey</groupId>
    <artifactId>mickey-orm-mybatis-plus</artifactId>
</dependency>
```