# mickey-starter-parent

#### 项目介绍
mickey-starter-parent

#### 软件架构
软件架构说明

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

* 2.0.3 接入skywalking

##试运行版本
* 1.0.1.SNAPSHOT 修改po为可配置、扩展底层方法

##测试版本
* 1.0.SNAPSHOT

## 代码生成器
``` java
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

## web项目使用

```xml
<parent>
    <groupId>com.mickey</groupId>
    <artifactId>mickey-starter-parent</artifactId>
    <version>${last.version}</version>
</parent>

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