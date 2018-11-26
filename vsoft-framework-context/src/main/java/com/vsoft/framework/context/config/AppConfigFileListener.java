package com.vsoft.framework.context.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vsoft.framework.context.VsoftFrameworkContextApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.env.EnvironmentPostProcessor;

import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.vsoft.framework.context.CompanyLogo;

/**
 * appconf配置文件加载
 *
 * @author LiuPeng
 *
 */
@Component
public class AppConfigFileListener implements EnvironmentPostProcessor, Ordered, ApplicationListener<ApplicationEvent> {

	private static final DeferredLog logger = new DeferredLog();
	//private final static Log logger = LogFactory.getLog(VsoftFrameworkContextApplication.class);
	//logger.
	private final static String LOCATION = "file:appconf/config-*.properties,classpath:/appconf/config-*.properties";
	PropertiesPropertySourceLoader propertiesLoader = new PropertiesPropertySourceLoader();

	private ConfigurableEnvironment environment;

	private ResourceLoader resourceLoader;

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment,  SpringApplication application) {
		logger.info(CompanyLogo.companyString);
		System.out.println("---------------------------postProcessEnvironment--1---执行了");
		logger.warn("---------------------------postProcessEnvironment-2----执行了");
		logger.error("---------------------------postProcessEnvironment-3----执行了");
		// 此处可以http方式 到配置服务器拉取一堆公共配置+本项目个性配置的json串,拼到Properties里
		this.environment = environment;
		try {
			Set<String> set = new HashSet<>();
			MutablePropertySources propertySources = this.environment.getPropertySources();
			ResourceLoader resourceLoader = application.getResourceLoader();
			resourceLoader = resourceLoader == null ? new DefaultResourceLoader() : resourceLoader;
			this.resourceLoader = resourceLoader;
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(
					this.resourceLoader);
			String[] locations = StringUtils.commaDelimitedListToStringArray(LOCATION);
			for (String location : locations) {
				Resource[] resources = resourcePatternResolver.getResources(location);
				for (Resource resource : resources) {
					if (set.contains(resource.getFilename())) {
						continue;
					}
					//PropertySource<?> propertySource = this.propertiesLoader.load(resource);
					List<PropertySource<?>> liu123 = this.propertiesLoader.load(resource.getFilename(), resource);
                    for (int i = 0; i < liu123.size(); i++) {
                        PropertySource<?> propertySource =  liu123.get(i);
                        logger.info("加载平台配置文件 - [" + propertySource.getName() + "]");
                        propertySources.addLast(propertySource);
                        set.add(resource.getFilename());
                    }

				}
			}
			//logger.switchTo(AppConfigFileListener.class);

		} catch (IOException e) {
			logger.error("加载平台配置文件失败", e);
		}
	}

	@Override
	public int getOrder() {
		// +1 保证application.propertie里的内容能覆盖掉本配置文件中默认的
		// 如果不想被覆盖 可以 -1
		return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
	}

	/*
	 * @param event
	 *
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.
	 * springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("----------0");
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            System.out.println("----------1");
			ApplicationEnvironmentPreparedEvent e = (ApplicationEnvironmentPreparedEvent) event;
			postProcessEnvironment(e.getEnvironment(), e.getSpringApplication());
		}
		if (event instanceof ApplicationPreparedEvent) {
            System.out.println("---------2");
			logger.replayTo(AppConfigFileListener.class);
		}
        if (event instanceof ApplicationReadyEvent) {
            System.out.println("---------3");
            logger.replayTo(AppConfigFileListener.class);
        }
        System.out.println("---"+event.getClass().getName());//1ServletWebServerInitializedEvent 2ApplicationStartedEvent 3ApplicationReadyEvent
        logger.replayTo(AppConfigFileListener.class);
	}
}