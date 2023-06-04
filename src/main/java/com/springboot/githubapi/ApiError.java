package com.springboot.githubapi;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
public class ApiError {

    private HttpStatus status;
    private Integer statusValue;
    private String message;


    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.statusValue = status.value();
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
