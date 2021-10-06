package io.catalyte.training.patienthealth.exceptions;

/**
 * A custom exception for unprocessable entity errors.
 */
public class UnprocessableEntityError extends RuntimeException {
  public UnprocessableEntityError() {
  }

  public UnprocessableEntityError(String message) {
    super(message);
  }
}
