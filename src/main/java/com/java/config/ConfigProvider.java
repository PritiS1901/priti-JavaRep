package com.java.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * define our config singleton and load configurations from the application_config.yaml file.
 */
public class ConfigProvider {

    static Logger logger = LoggerFactory.getLogger(ConfigProvider.class);

    private ApplicationConfig config;

    private ConfigProvider() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        try {
            config = mapper.readValue(new File("src/main/resources/application_config.yaml"), ApplicationConfig.class);
        } catch (IOException e) {
            logger.error(String.format("error while loading config : ", e.toString()));
        }
    }

    private static class ApplicationConfigHelper {
        private static final ConfigProvider INSTANCE = new ConfigProvider();
    }

    public static ApplicationConfig getConfig() {
        return ApplicationConfigHelper.INSTANCE.config;
    }

}