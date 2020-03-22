package com.mickey.core.utils.common;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * @Description: 扩展spring配置读取方式，在容器启动时优先加载配置文件到内存，不需要单独读取配置
 * @author J·K
 * @date 2018/5/7 15:41
 */
public class PropertyConfigUtils extends PropertyResourceConfigurer {

	private Logger log = LoggerFactory.getLogger(PropertyConfigUtils.class);

	private PathMatchingResourcePatternResolver resourceLoader;

	public PropertyConfigUtils() {
		this.resourceLoader = new PathMatchingResourcePatternResolver();
	}

	private static Map<String, String> ctxPropertiesMap;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {

		log.debug("PropertyConfigure execute ........");
		InputStream in = null;
		Properties properties = new Properties();
		try {
			Resource[] resources = resourceLoader.getResources("classpath:/**/*.properties");
			for (Resource resource : resources) {
				in = resource.getInputStream();
				properties.load(in);
			}
		} catch (IOException e) {
			log.error("load properties error!!!");
		}

		ctxPropertiesMap = new HashMap<>();
		for (Entry<Object, Object> key : properties.entrySet()) {
			String keyStr = (String) key.getKey();
			String value = (String) key.getValue();
			ctxPropertiesMap.put(keyStr, value);
			log.debug("PropertyConfigure load K[{}] V[{}]", keyStr, value);
		}
		log.info("PropertyConfigure load finish,size:{}", ctxPropertiesMap.size());
	}

	public static String getProperty(String name) {

		return ctxPropertiesMap.get(name);
	}

	public static String getProperty(String name, String value) {

		String v = ctxPropertiesMap.get(name);
		if (StringUtils.isEmpty(v)) {
			return value;
		}
		return ctxPropertiesMap.get(name);
	}

	public static void setProperties(String key,String val){
		ctxPropertiesMap.put(key,val);
	}


	public static void putAll(Map map){
		if(MapUtils.isNotEmpty(map)){
			ctxPropertiesMap.putAll(map);
		}
	}

}
