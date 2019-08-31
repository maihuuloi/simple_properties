package com.lhm.properties;

import com.lhm.properties.annotation.*;
import com.lhm.properties.exception.*;
import com.lhm.properties.parser.ValueParserAdapter;
import com.lhm.properties.parser.ValueParserFactory;
import com.lhm.properties.parser.exception.ParseException;
import com.lhm.properties.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Properties;

/**

 */
public class PropertiesBeanFactory {
    /**
     * The pattern for property nam can contain '.' or '-' between character.
     * Example: my-object.my-property
     */
    public static final String PROPERTY_PATTERN = "([a-zA-Z0-9]{1,30}[\\.-])*[a-zA-Z0-9]{1,30}$";

    private Properties properties;

    public PropertiesBeanFactory(Properties properties) {
        this.properties = properties;
    }

    public <T extends BeanProperties> T createFromBeanType(Class<T> clazz) {
        T propertyBean = createBeanFromType(clazz);

        fillBeanFieldsWithPropertyValues(propertyBean);

        return propertyBean;
    }

    private <T extends BeanProperties> void fillBeanFieldsWithPropertyValues(T propertyBean) {
        Class clazz = propertyBean.getClass();
        String propertyPrefix = getPropertyNamePrefixFromClassAnnotation(clazz);

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                String propertyName = getPropertyNameFromFieldWithPrefix(field, propertyPrefix);

                String value = properties.getProperty(propertyName);

                validateRequiredFieldWithValue(field, value);

                if (StringUtils.isEmptyString(value)) {
                    value = getDefaultValueFromAnnotation(field);
                }

                if (StringUtils.isNotEmptyString(value)) {
                    Object parsedValue = parseValueAgainstField(value, field);
                    setValueToFieldForBean(parsedValue, field, propertyBean);
                }

            } finally {
                field.setAccessible(false);
            }
        }
    }

    private String getPropertyNameFromFieldWithPrefix(Field field, String propertyPrefix) {
        String propertyName = getPropertyNameFromField(field);

        if (StringUtils.isNotEmptyString(propertyPrefix)) {
            propertyName = propertyPrefix + "." + propertyName;
        }
        return propertyName;
    }

    private <T> T createBeanFromType(Class<T> clazz) {
        try {
            Constructor<?> ctor = clazz.getConstructor();
            return (T) ctor.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException(e);
        }
    }


    private <T extends BeanProperties> void setValueToFieldForBean(Object parsedValue, Field field, T propertyBean) {
        try {
            field.set(propertyBean, parsedValue);
        } catch (IllegalAccessException e) {
            throw new SetValueToFieldFailedException(e);//NOPE
        }
    }


    private Object parseValueAgainstField(String value, Field field) {
        try {
            return parseValueAgainstFieldDefinition(value, field);
        } catch (ParseException e) {
            throw new FieldParsingFailedException(String.format("Can not parse value '%s' for field %s[%s]", value, field.getDeclaringClass().getName(), field.getName()), e);
        }
    }

    private Object parseValueAgainstFieldDefinition(String value, Field field) throws ParseException {

        ValueParserAdapter valueParser = ValueParserFactory.createFromType(field.getType());

        PropertyFormat propertyFormat = field.getAnnotation(PropertyFormat.class);
        if (propertyFormat != null) {
            String format = propertyFormat.value();
            return valueParser.parseAgainstFormat(value, format);
        } else {
            return valueParser.parse(value);
        }
    }

    private void validateRequiredFieldWithValue(Field field, String value) {
        boolean isRequired = isRequiredField(field);
        if (isRequired && StringUtils.isEmptyString(value)) {
            throw new RequiredFieldEmptyException(String.format("Missing property value found for required field %s[%s]", field.getDeclaringClass().getName(), field.getName()));
        }
    }


    private boolean isRequiredField(Field field) throws RequiredFieldEmptyException {
        Required required = field.getAnnotation(Required.class);
        boolean isRequired = required != null ? required.value() : false;
        return isRequired;
    }

    private String getPropertyNameFromField(Field field) throws KeyNameInvalidFormatException {
        PropertyName propertyNameAnnotation = field.getAnnotation(PropertyName.class);
        String propertyName = null;

        if (propertyNameAnnotation != null) {
            propertyName = getPropertyNameAnnotationValue(propertyNameAnnotation);
        } else {
            propertyName = field.getName();
        }

        return propertyName;
    }

    private String getPropertyNameAnnotationValue(PropertyName propertyNameAnnotation) {
        String propertyName = propertyNameAnnotation.value();
        if (StringUtils.isEmptyString(propertyName)) {
            throw new KeyNameInvalidFormatException(String.format("Key name cannot be empty"));

        }

        boolean isValidKeyName = propertyName.matches(PROPERTY_PATTERN);
        if (!isValidKeyName) {
            throw new KeyNameInvalidFormatException(String.format("Invalid key name %s", propertyName));
        }
        return propertyName;
    }

    private <T> String getPropertyNamePrefixFromClassAnnotation(Class<T> clazz) {
        Prefix prefix = clazz.getAnnotation(Prefix.class);
        if (prefix == null) {
            return "";
        }

        String prefixValue = prefix.value();

        return prefixValue;

    }


    private String getDefaultValueFromAnnotation(Field field) {
        DefaultValue defaultValue = field.getAnnotation(DefaultValue.class);
        if (defaultValue != null) {
            return defaultValue.value();
        } else {
            return null;
        }
    }

}
