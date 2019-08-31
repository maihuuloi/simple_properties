package com.lhm.properties.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Key name of property, after {@link Prefix} if it is present. If not present, the key will be generated based on the unqualified
 * field name.
 *

 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface PropertyName
{
  String value();
}
