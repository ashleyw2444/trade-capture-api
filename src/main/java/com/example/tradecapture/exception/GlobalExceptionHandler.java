package com.example.tradecapture.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.tradecapture.dto.ErrorResponseDTO;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(TradeNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleTradeNotFound(
        TradeNotFoundException ex, HttpServletRequest request){
        ErrorResponseDTO response = new ErrorResponseDTO(LocalDateTime.now(), 
        ex.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(response);
    }
}
