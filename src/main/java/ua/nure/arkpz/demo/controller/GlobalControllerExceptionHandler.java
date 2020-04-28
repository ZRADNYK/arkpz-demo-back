package ua.nure.arkpz.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.nure.arkpz.demo.exception.EntitySearchFailedException;
import ua.nure.arkpz.demo.exception.ValidateException;

import java.util.Map;

@ControllerAdvice
@RequestMapping(produces = "application/json")
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({ValidateException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Map<String, String> handleValidationConflict(ValidateException ex) {
        return ex.getErrorsMap();
    }

    @ExceptionHandler({EntitySearchFailedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> handleSearchConflict(ValidateException ex) {
        return ex.getErrorsMap();
    }
}
