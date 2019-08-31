package com.lhm.properties.parser;

class ByteValueParserAdapter extends ValueParserAdapter<Byte> {
    @Override
    public Byte parse(String value) {
        return Byte.parseByte(value);
    }
}
