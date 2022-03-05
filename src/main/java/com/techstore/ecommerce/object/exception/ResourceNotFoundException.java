package com.techstore.ecommerce.object.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {}

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
