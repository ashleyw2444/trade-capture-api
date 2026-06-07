package com.example.tradecapture.dto;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    
    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String path;

    public ErrorResponseDTO(){}

    public ErrorResponseDTO(LocalDateTime timestamp, 
        String message, int status, String path){
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.path = path;
    }

    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }

    public String getMessage(){
        return this.message;
    }

    public int getStatus(){
        return this.status;
    }

    public String getPath(){
        return this.path;
    }
}
