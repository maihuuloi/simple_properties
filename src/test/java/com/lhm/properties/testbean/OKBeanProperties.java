package com.lhm.properties.testbean;

import com.lhm.properties.BeanProperties;
import com.lhm.properties.annotation.Prefix;
import com.lhm.properties.annotation.PropertyFormat;
import com.lhm.properties.annotation.PropertyName;

import java.util.Date;

@Prefix(value = "app")
public class OKBeanProperties implements BeanProperties{

    private String withoutAnnotationField;

    @PropertyName("boolean-field")
    private Boolean booleanField;

    @PropertyFormat("dd/MM/yyyy")
    @PropertyName("date-field-with-format")
    private Date dateFieldWithFormat;

    public String getWithoutAnnotationField() {
        return withoutAnnotationField;
    }

    public void setWithoutAnnotationField(String withoutAnnotationField) {
        this.withoutAnnotationField = withoutAnnotationField;
    }

    public Boolean getBooleanField() {
        return booleanField;
    }

    public void setBooleanField(Boolean booleanField) {
        this.booleanField = booleanField;
    }

    public Date getDateFieldWithFormat() {
        return dateFieldWithFormat;
    }

    public void setDateFieldWithFormat(Date dateFieldWithFormat) {
        this.dateFieldWithFormat = dateFieldWithFormat;
    }
}
