package com.mickey.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import com.mickey.druid.interceptor.NoveSqlInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description: 提供基础数据源功能
 * @author J·K
 * @date 2020/3/20 6:52 下午
 */
@Configuration
@EnableConfigurationProperties(DruidDbProperties.class)
public abstract class AbstractDruidDBConfig {

	private Logger logger = LoggerFactory.getLogger(AbstractDruidDBConfig.class);

	@Resource
	private DruidDbProperties druidDbProperties;

	public DruidDataSource createDataSource(String url, String username, String password) {
		if (StringUtils.isEmpty(url)) {
			logger.error(
					"Your database connection pool configuration is incorrect! Please check your Spring profile! spring.datasource.url  is null");
			throw new ApplicationContextException(
					"Database connection pool is not configured correctly! spring.datasource.url is null");
		}

		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(druidDbProperties.getDriverClassName());
		datasource.setInitialSize(druidDbProperties.getInitialSize());
		datasource.setMinIdle(druidDbProperties.getMinIdle());
		datasource.setMaxActive(druidDbProperties.getMaxActive());
		datasource.setMaxWait(druidDbProperties.getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(druidDbProperties.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(druidDbProperties.getMinEvictableIdleTimeMillis());
		datasource.setValidationQuery(druidDbProperties.getValidationQuery());
		datasource.setTestWhileIdle(druidDbProperties.isTestWhileIdle());
		datasource.setTestOnBorrow(druidDbProperties.isTestOnBorrow());
		datasource.setTestOnReturn(druidDbProperties.isTestOnReturn());
		datasource.setAsyncInit(druidDbProperties.isAsyncInit());
		try {
			datasource.setFilters(druidDbProperties.getFilters());
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(druidDbProperties.getConnectionProperties());
		return datasource;

	}

	public DruidDataSource createDataSource(String url, String username, String password, String driverClass) {
		DruidDataSource datasource = createDataSource(url, username, password);
		if (StringUtils.isNotEmpty(driverClass)) {
            datasource.setDriverClassName(driverClass);
        }
		return datasource;

	}

	/**
	 * 加载默认mybatis xml配置文件，并初始化分页插件
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource, "classpath:mybatis/**/*.xml");
	}

	/**
	 * 加载mybatis xml配置文件，并初始化分页插件
	 * 
	 * @param dataSource
	 *            数据源
	 * @param mapperLocations
	 *            自定义xml配置路径
	 * @return
	 * @throws Exception
	 */
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, String mapperLocations) throws Exception {
		return createSqlSessionFactory(dataSource, mapperLocations);
	}

	private SqlSessionFactory createSqlSessionFactory(DataSource dataSource, String mapperLocations) throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		// mybatis分页
		Properties props = new Properties();
		props.setProperty("dialect", "mysql");
		props.setProperty("reasonable", "true");
		props.setProperty("supportMethodsArguments", "true");
		props.setProperty("returnPageInfo", "check");
		props.setProperty("params", "count=countSql");

		PageInterceptor pageInterceptor = new PageInterceptor();
		// 添加插件
		pageInterceptor.setProperties(props);

		sqlSessionFactoryBean.setPlugins(pageInterceptor,new NoveSqlInterceptor());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
		return sqlSessionFactoryBean.getObject();

	}
}
