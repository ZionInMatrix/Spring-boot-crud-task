package cz.homeoffice.funtaskproject.config;

import cz.homeoffice.funtaskproject.rest.models.ErrorBodyRest;
import cz.homeoffice.funtaskproject.services.exceptions.PersonalDataServiceException;
import cz.homeoffice.funtaskproject.services.exceptions.UserServiceException;
import cz.homeoffice.taskproject.rest.models.ErrorBodyDto;
import cz.homeoffice.taskproject.services.exceptions.UserServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Component
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({UserServiceException.class})
    public ResponseEntity<ErrorBodyDto> handleException(UserServiceException ex, WebRequest request) {
        ErrorBodyDto bodyOfResponse = new ErrorBodyDto();
        bodyOfResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        bodyOfResponse.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), request);
    }

    private ResponseEntity<ErrorBodyDto> handleExceptionInternal(UserServiceException ex, ErrorBodyDto bodyOfResponse, HttpHeaders httpHeaders, WebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(bodyOfResponse, httpHeaders, bodyOfResponse.getHttpStatus());
    }

    @ExceptionHandler({PersonalDataServiceException.class})
    public ResponseEntity<ErrorBodyDto> handleException(PersonalDataServiceException ex, WebRequest request) {
        ErrorBodyDto bodyOfResponse = new ErrorBodyDto();
        bodyOfResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        bodyOfResponse.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), request);
    }

    private ResponseEntity<ErrorBodyDto> handleExceptionInternal(PersonalDataServiceException ex, ErrorBodyDto bodyOfResponse, HttpHeaders httpHeaders, WebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(bodyOfResponse, httpHeaders, bodyOfResponse.getHttpStatus());
    }


}
