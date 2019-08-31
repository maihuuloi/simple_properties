package com.lhm.properties.parser;

import com.lhm.properties.parser.exception.ParseException;

class BooleanValueParserAdapter extends ValueParserAdapter<Boolean> {
    @Override
    public Boolean parse(String value) {
        if (!value.toUpperCase().equals("TRUE") && !value.toUpperCase().equals("FALSE")) {
            throw new IllegalArgumentException("Not boolean type");
        }
        return Boolean.parseBoolean(value);
    }


}
