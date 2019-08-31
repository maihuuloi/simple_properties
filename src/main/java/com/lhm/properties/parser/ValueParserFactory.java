package com.lhm.properties.parser;

import java.util.Date;

public class ValueParserFactory {

    public static ValueParserAdapter createFromType(Class<?> cl)  {
        if (cl.isAssignableFrom(Integer.class) || cl.isAssignableFrom(int.class)) {
            return new IntegerValueParserAdapter();
        } else if (cl.isAssignableFrom(Long.class) || cl.isAssignableFrom(long.class)) {
            return new LongValueParserAdapter();
        } else if (cl.isAssignableFrom(Short.class) || cl.isAssignableFrom(short.class)) {
            return new ShortValueParserAdapter();
        } else if (cl.isAssignableFrom(Float.class) || cl.isAssignableFrom(float.class)) {
            return new FloatValueParserAdapter();
        } else if (cl.isAssignableFrom(Double.class) || cl.isAssignableFrom(double.class)) {
            return new DoubleValueParserAdapter();
        } else if (cl.isAssignableFrom(Boolean.class) || cl.isAssignableFrom(boolean.class)) {
            return new BooleanValueParserAdapter();
        } else if (cl.isAssignableFrom(char.class) || cl.isAssignableFrom(Character.class)) {
            return new CharValueParserAdapter();
        } else if (cl.isAssignableFrom(Byte.class) || cl.isAssignableFrom(byte.class)) {
            return new ByteValueParserAdapter();
        } else if (cl.isAssignableFrom(String.class)) {
            return new StringValueParserAdapter();
        } else if (cl.isAssignableFrom(Date.class)) {
            return new DateValueParserAdapter();
        } else {
            throw new IllegalArgumentException("Only following types are allow: Integer, Long, Short, Float, Double, Boolean, char, Byte, String, Date");
        }

    }
}
