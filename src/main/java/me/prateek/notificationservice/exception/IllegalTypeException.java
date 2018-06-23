package me.prateek.notificationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//This Exception is not Using Custom ExceptionHandlingController

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalTypeException extends RuntimeException {

    public IllegalTypeException(String key, String dataType) {
        super("Value of " + key + " must be a " + dataType);
    }
}
