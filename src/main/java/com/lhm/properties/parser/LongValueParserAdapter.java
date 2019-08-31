package com.lhm.properties.parser;

class LongValueParserAdapter extends ValueParserAdapter<Long> {
    @Override
    public Long parse(String value) {
        return Long.parseLong(value);
    }
}
