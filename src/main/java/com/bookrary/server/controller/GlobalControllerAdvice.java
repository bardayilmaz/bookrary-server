package com.bookrary.server.controller;

import com.bookrary.server.exception.BusinessException;
import com.bookrary.server.model.ErrorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorModel> customHandleBusinessException(BusinessException exception, WebRequest request) {
        logger.warn("Business Error: {}", exception.getMessage());
        ErrorModel error = ErrorModel.builder()
                .timestamp(ZonedDateTime.now())
                .status(exception.getErrorCode().getHttpCode())
                .error(exception.getErrorCode().toString())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.resolve(error.getStatus()));
    }

}
