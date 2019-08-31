package com.lhm.properties.exception;

public class PropertiesConfigException extends RuntimeException
{
  public PropertiesConfigException()
  {
  }

  public PropertiesConfigException(String message)
  {
    super(message);
  }

  public PropertiesConfigException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public PropertiesConfigException(Throwable cause)
  {
    super(cause);
  }


}
