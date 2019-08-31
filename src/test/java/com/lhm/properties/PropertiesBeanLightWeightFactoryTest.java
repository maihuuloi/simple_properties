package com.lhm.properties;

import com.lhm.properties.testbean.OKBeanProperties;
import com.lhm.properties.util.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PropertiesBeanLightWeightFactoryTest {

    @Before
    public void setup() {
        System.setProperty(PropertiesConfiguration.PROPERTY_FILE_PATHS, System.getProperty("user.dir") + "/src/test/resources/configuration_test.properties");
    }

    @Test
    public void testMappingOK() {
        OKBeanProperties fromBeanType = PropertiesBeanLightWeightFactory.createFromBeanType(OKBeanProperties.class);
        Assert.assertEquals(fromBeanType.getBooleanField(), false);
        Assert.assertNotNull(fromBeanType.getDateFieldWithFormat());
        Assert.assertTrue(StringUtils.isNotEmptyString(fromBeanType.getWithoutAnnotationField()));
    }
}