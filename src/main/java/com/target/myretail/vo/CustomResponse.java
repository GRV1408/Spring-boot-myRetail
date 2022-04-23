package com.target.myretail.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomResponse {
    public enum Status {
        SUCCESS, FAIL
    };

    private String status;

    private String message;

    private LocalDateTime dateTime;

    public CustomResponse() {

    }

    public CustomResponse(Status status, String message) {
        super();
        this.status = status.toString();
        this.message = message;
    }

    public CustomResponse(Status status, String message, LocalDateTime dateTime) {
        super();
        this.status = status.toString();
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}