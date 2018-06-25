//POJO For a Response to be sent for an Exception
package me.prateek.notificationservice.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String status;
    private String error;
    private String message;

    public ExceptionResponse() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
