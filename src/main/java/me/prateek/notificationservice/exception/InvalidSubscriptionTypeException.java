package me.prateek.notificationservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidSubscriptionTypeException extends RuntimeException{

    public InvalidSubscriptionTypeException(String subType)
    {
        super("Invalid Subscription type: " + subType + ". Must be one of: [gold, silver, platinum]");
    }
}
