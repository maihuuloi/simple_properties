package com.lhm.properties.parser;

import com.lhm.properties.parser.exception.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValueParserAdapter extends ValueParserAdapter<Date> {
    @Override
    public Date parse(String value) throws ParseException {
        try {
            return parseDateWithDefaultFormat(value);
        } catch (java.text.ParseException e) {
            throw new ParseException(String.format("Can not parse value '%s' with default format", value), e);
        }

    }

    private Date parseDateWithDefaultFormat(String value) throws java.text.ParseException {
        SimpleDateFormat dateDateFormat = new SimpleDateFormat();
        dateDateFormat.setLenient(false);
        return dateDateFormat.parse(value);
    }

    @Override
    public Date parseAgainstFormat(String value, String format) throws ParseException {
        try {
            return parseDateWithFormat(value, format);
        } catch (java.text.ParseException e) {
            throw new ParseException(String.format("Can not parse value '%s' against format '%s'", value, format), e);
        }
    }

    private Date parseDateWithFormat(String value, String format) throws java.text.ParseException {
        SimpleDateFormat dateDateFormat = new SimpleDateFormat(format);
        dateDateFormat.setLenient(false);
        return dateDateFormat.parse(value);
    }


}
