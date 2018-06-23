package me.prateek.notificationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NullKeyException extends RuntimeException {

    public NullKeyException(String resource) {
        super("Value of " + resource + " must not be NULL");
    }
}
