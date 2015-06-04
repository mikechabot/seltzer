package com.seltzer.config;

import com.seltzer.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum AppProperties {

    INSTANCE;

    private static final Logger log = Logger.getLogger(AppProperties.class);

    private final String path = "/bootstrap.properties";
    private Properties properties;

    private AppProperties() throws ExceptionInInitializerError {
        properties = getProperties();
    }

    /**
     * Load the configuration file
     * @return Properties
     * @throws ExceptionInInitializerError
     */
    private Properties getProperties() throws ExceptionInInitializerError {
        InputStream inputStream = null;
        try {
            properties = new Properties();
            inputStream = Configuration.class.getResourceAsStream(path);
            properties.load(inputStream);
        } catch (NullPointerException e) {
            throw new ExceptionInInitializerError("Unable to locate file " + path);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error reading file " + path);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("Error closing InputStream", e);
                }
            }
        }
        return properties;
    }

    private String getProperty(String key) {
        if (StringUtil.isEmpty(key)) throw new IllegalArgumentException("Property key cannot be null");

        String value = properties.getProperty(key.toLowerCase());
        if (value != null) {
            value = StringUtil.removeWhitespaces(value);
        }
        return value;
    }

    public String getRequiredString(String key) {
        String value = getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing required String for property '" + key + "'");
        }
        return value;
    }

    public int getRequiredInt(String key) {
        String value = getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Missing required int for property '" + key + "'");
        }
    }

    public int getOptionalInt(String key, int optionalValue) {
        String value = getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return optionalValue;
        }
    }

}
