package com.lhm.properties.parser;

import com.lhm.properties.parser.exception.ParseException;

public abstract class ValueParserAdapter<T> implements ValueParser {
    public T parseAgainstFormat(String value, String format) throws ParseException {
        throw new UnsupportedOperationException();
    }
}
