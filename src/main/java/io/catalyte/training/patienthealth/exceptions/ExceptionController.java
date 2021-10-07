package io.catalyte.training.patienthealth.exceptions;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import static io.catalyte.training.patienthealth.constants.StringConstants.*;

/**
 * A controller advice allows you to use exactly the same exception handling techniques but apply
 * them across the whole application, not just to an individual controller. You can think of them as
 * an annotation driven interceptor. More info: https://www.baeldung.com/exception-handling-for-rest-with-spring
 *
 * <p>Handles exception responses for HTTP codes 400 (Bad Request), 404(Not Found), 409 (Conflict),
 * and 500(Server Error).
 */
@ControllerAdvice
public class ExceptionController {

  private final Logger logger = LogManager.getLogger();

  /**
   * @param exception response thrown
   * @return string NOT_FOUND, date, and exception message
   */
  @ExceptionHandler(ResourceNotFound.class)
  protected ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFound exception) {
    ExceptionResponse response =
        new ExceptionResponse(NOT_FOUND, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  /**
   * @param exception response thrown
   * @return string constant SERVER_ERROR, date, and exception message
   */
  @ExceptionHandler(ServerError.class)
  protected ResponseEntity<ExceptionResponse> serverError(ServerError exception) {
    ExceptionResponse response =
        new ExceptionResponse(SERVER_ERROR, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * @param exception response thrown
   * @return string constant UNPROCESSABLE_ENTITY, date, and exception message
   */
  @ExceptionHandler(UnprocessableEntityError.class)
  protected ResponseEntity<ExceptionResponse> unprocessableEntityError(
      UnprocessableEntityError exception) {
    ExceptionResponse response =
        new ExceptionResponse(UNPROCESSABLE_ENTITY, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
  }


  /**
   * @param exception response thrown
   * @return string constant CONFLICT_ERROR, date, and exception message
   */
  @ExceptionHandler(ConflictException.class)
  protected ResponseEntity<ExceptionResponse> conflictException(
          ConflictException exception) {
    ExceptionResponse response =
            new ExceptionResponse(CONFLICT, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }



  /**
   * @return string constant BAD_REQUEST, date, and exception message
   */
  @ExceptionHandler(BadRequest.class)
  protected ResponseEntity<ExceptionResponse> badRequest(BadRequest exception) {
    ExceptionResponse response =
        new ExceptionResponse(BAD_REQUEST, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * @param ex exception response.
   * @return the fields that caused the response as a string.
   */
  private String parseMessage(MethodArgumentNotValidException ex) {
    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    StringBuilder message = new StringBuilder();
    for (FieldError err : errors) {
      message.append(err.getDefaultMessage());
      message.append(" ");
    }
    return message.toString().trim();
  }
}
