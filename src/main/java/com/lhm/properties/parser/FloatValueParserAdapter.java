package com.lhm.properties.parser;

class FloatValueParserAdapter extends ValueParserAdapter<Float> {
    @Override
    public Float parse(String value) {
        return Float.parseFloat(value);
    }
}
