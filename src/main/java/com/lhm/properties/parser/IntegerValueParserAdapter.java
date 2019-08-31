package com.lhm.properties.parser;

class IntegerValueParserAdapter extends ValueParserAdapter<Integer> {
    @Override
    public Integer parse(String value) {
        return Integer.parseInt(value);
    }
}
