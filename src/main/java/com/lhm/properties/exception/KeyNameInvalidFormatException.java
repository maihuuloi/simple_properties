package com.lhm.properties.exception;

public class KeyNameInvalidFormatException extends PropertiesConfigException
{
  public KeyNameInvalidFormatException()
  {
  }

  public KeyNameInvalidFormatException(String message)
  {
    super(message);
  }

  public KeyNameInvalidFormatException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public KeyNameInvalidFormatException(Throwable cause)
  {
    super(cause);
  }

}
