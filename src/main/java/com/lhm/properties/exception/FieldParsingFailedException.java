package com.lhm.properties.exception;

public class FieldParsingFailedException extends PropertiesConfigException
{
  public FieldParsingFailedException()
  {
  }

  public FieldParsingFailedException(String message)
  {
    super(message);
  }

  public FieldParsingFailedException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public FieldParsingFailedException(Throwable cause)
  {
    super(cause);
  }

}
