package com.lhm.properties;

import com.lhm.properties.exception.ConfigurationException;
import com.lhm.properties.exception.PropertyFileReadException;
import com.lhm.properties.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfiguration {
    public static final String PROPERTY_FILE_PATHS = "properties-config.file-paths";
    public static final String PROPERTY_RESOURCE_FILE_PATHS = "properties-config.resource-paths";
    private Properties propertiesContainer = new Properties();

    public PropertiesConfiguration() {
        loadProperties();
    }

    public Properties getProperties() {
        return propertiesContainer;
    }

    private Properties loadProperties() {
        String configFilePaths = System.getProperty(PROPERTY_FILE_PATHS);
        String configResourcePaths = System.getProperty(PROPERTY_RESOURCE_FILE_PATHS);

        boolean isNoConfigurationSet = StringUtils.isEmptyString(configFilePaths) && StringUtils.isEmptyString(configResourcePaths);

        if (isNoConfigurationSet) {
            throw new ConfigurationException("Configuration files is not provided");
        }

        if (StringUtils.isNotEmptyString(configFilePaths)) {
            loadConfigFromFilePaths(configFilePaths);
        }

        if (StringUtils.isNotEmptyString(configResourcePaths)) {
            loadConfigFromResource(configResourcePaths);

        }


        return this.propertiesContainer;
    }

    private void loadConfigFromResource(String configResourcePaths) {
        String[] resourcePaths = configResourcePaths.split(";");
        Properties resourceProperties = loadPropertiesFromResourceFiles(resourcePaths);
        this.propertiesContainer.putAll(resourceProperties);
    }

    private void loadConfigFromFilePaths(String configFilePaths) {
        String[] filePaths = configFilePaths.split(";");
        Properties filePathsProperties = loadPropertiesFromFiles(filePaths);
        this.propertiesContainer.putAll(filePathsProperties);
    }


    private Properties loadPropertiesFromResourceFiles(String[] resourcePaths) {
        Properties properties = new Properties();

        for (String resourcePath : resourcePaths) {
            try {
                InputStream targetStream = PropertiesConfiguration.class.getResourceAsStream(resourcePath);
                properties.load(targetStream);
            } catch (IOException e) {
                throw new PropertyFileReadException(e);
            }

        }
        return properties;
    }


    public Properties loadPropertiesFromFiles(String[] paths) {
        Properties properties = new Properties();
        for (String path : paths) {
            properties.putAll(loadPropertiesFromFile(path));
        }
        return properties;
    }


    private Properties loadPropertiesFromFile(String path) {
        try {
            Properties properties = new Properties();
            File initialFile = new File(path);
            InputStream targetStream = new FileInputStream(initialFile);
            properties.load(targetStream);
            return properties;
        } catch (IOException e) {
            throw new PropertyFileReadException(e);
        }
    }
}
