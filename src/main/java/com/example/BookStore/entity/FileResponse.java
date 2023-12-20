package com.example.BookStore.entity;

public class FileResponse {
    private  String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FileResponse(String message) {
        this.message = message;
    }

}
