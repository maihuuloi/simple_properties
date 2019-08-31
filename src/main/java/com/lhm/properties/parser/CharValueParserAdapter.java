package com.lhm.properties.parser;

class CharValueParserAdapter extends ValueParserAdapter<Character> {
    @Override
    public Character parse(String value) {
        if (value.length() > 1) {
            throw new IllegalArgumentException(String.format("'%s' has more than one character.", value));
        }
        return value.charAt(0);
    }
}
