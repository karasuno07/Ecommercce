package com.techstore.ecommercce.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {}

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
