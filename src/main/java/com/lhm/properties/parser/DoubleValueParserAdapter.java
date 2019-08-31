package com.lhm.properties.parser;

class DoubleValueParserAdapter extends ValueParserAdapter<Double> {
    @Override
    public Double parse(String value) {
        return Double.parseDouble(value);
    }
}
