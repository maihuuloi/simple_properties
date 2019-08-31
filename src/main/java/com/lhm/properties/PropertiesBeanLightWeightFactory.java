package com.lhm.properties;


import com.lhm.properties.exception.PropertiesConfigException;

import java.util.HashMap;
import java.util.Map;

/**
 * A properties bean type will load once only, it will be stored and returned for next time the same type get called
 */
public class PropertiesBeanLightWeightFactory {
    private static PropertiesBeanLightWeightFactory instance;
    private PropertiesBeanFactory propertiesBeanFactory;

    //Lightweight
    private Map<String, Object> propertyObjects = new HashMap<String, Object>();

    private PropertiesBeanLightWeightFactory() {
        PropertiesConfiguration configuration = new PropertiesConfiguration();
        java.util.Properties properties = configuration.getProperties();
        propertiesBeanFactory = new PropertiesBeanFactory(properties);
    }

    public static PropertiesBeanLightWeightFactory getInstance() {
        if (instance == null) {
            synchronized (PropertiesBeanLightWeightFactory.class) {
                if (instance == null) {
                    instance = new PropertiesBeanLightWeightFactory();
                }
            }
        }

        return instance;
    }

    public static <T> T createFromBeanType(Class<T> clazz) throws PropertiesConfigException {
        return PropertiesBeanLightWeightFactory.getInstance().create(clazz);
    }

    public <T> T create(Class<T> clazz) {
        T t = (T) propertyObjects.get(clazz.getName());

        if (t == null) {
            t = propertiesBeanFactory.createFromBeanType(clazz);
            propertyObjects.put(clazz.getName(), t);
        }

        return t;
    }

}
