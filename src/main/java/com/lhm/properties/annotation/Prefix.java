package com.lhm.properties.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Place on the properties classes to add prefix for mapping
 */
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Prefix
{
  String value() default "";
}
