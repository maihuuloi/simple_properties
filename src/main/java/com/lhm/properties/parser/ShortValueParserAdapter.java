package com.lhm.properties.parser;

class ShortValueParserAdapter extends ValueParserAdapter<Short> {
    @Override
    public Short parse(String value) {
        return Short.parseShort(value);
    }
}
