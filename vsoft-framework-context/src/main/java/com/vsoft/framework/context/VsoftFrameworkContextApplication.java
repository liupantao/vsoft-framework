package com.vsoft.framework.context;

import com.vsoft.framework.context.annotation.ModuleScan;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@ModuleScan
public class VsoftFrameworkContextApplication {
    private final static Log logger = LogFactory.getLog(VsoftFrameworkContextApplication.class);
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(VsoftFrameworkContextApplication.class, args);
        logger.info("****************項目啟動");
        ConfigurableEnvironment environment = context.getEnvironment();
        String property1 = environment.getProperty("server.ports");
        String property2 = environment.getProperty("localhost.server.ports");
        MutablePropertySources propertySources = environment.getPropertySources();
        //propertySources.get
        PropertySource<?> propertySource = propertySources.get("config-remote.properties");
        Object property = propertySource.getProperty("config-remote.properties");
        Object source = propertySource.getSource();
        logger.info("****************項目啟動"+source.toString());
    }
}
