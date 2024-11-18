package com.tracker.tasktracker.response;

import lombok.Data;

@Data
// API response 
public class ApiResponse<T> {
    private String message;
    private T data;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
