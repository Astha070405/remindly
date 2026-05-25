package com.notesapp.notes_app.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(
            EmailAlreadyExistsException.class
    )

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleEmailExists(
            EmailAlreadyExistsException ex
    ) {

        return Map.of(
                "success", "false",
                "message", ex.getMessage()
        );
    }
}
