package com.lhm.properties.parser;

import com.lhm.properties.parser.exception.ParseException;

public interface ValueParser<T> {
    T parse(String value) throws ParseException;

    T parseAgainstFormat(String value, String format) throws ParseException;
}
