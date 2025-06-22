package com.develhope.co.biblioteca_prova.dto;

import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;

public class APIResponse {
    private boolean succes;
    private String message;

    private LocalDateTime currentTime = LocalDateTime.now();
    private List<ObjectError> objectErrorList;
    private Object content;

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public List<ObjectError> getObjectErrorList() {
        return objectErrorList;
    }

    public void setObjectErrorList(List<ObjectError> objectErrorList) {
        this.objectErrorList = objectErrorList;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public APIResponse(List<ObjectError> objectErrorList) {
        this.objectErrorList = objectErrorList;
        this.succes = false;
    }

    public APIResponse(Object content) {
        this.succes = true;
        this.content = content;
    }

    public APIResponse(String message){
        this.succes = false;
        this.message = message;
    }

    public APIResponse(){
        this.succes = true;

    }

}
