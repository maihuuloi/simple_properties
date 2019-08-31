package com.lhm.properties.exception;

public class RequiredFieldEmptyException extends PropertiesConfigException
{
  public RequiredFieldEmptyException()
  {
  }

  public RequiredFieldEmptyException(String message)
  {
    super(message);
  }

  public RequiredFieldEmptyException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public RequiredFieldEmptyException(Throwable cause)
  {
    super(cause);
  }
}
