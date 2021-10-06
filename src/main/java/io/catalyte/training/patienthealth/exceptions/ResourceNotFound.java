package io.catalyte.training.patienthealth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A custom exception for resource not found.
 */
public final class ResourceNotFound extends RuntimeException {
  public ResourceNotFound() {
  }

  public ResourceNotFound(String message) {
    super(message);
  }
}
