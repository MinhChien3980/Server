package org.example.server_mobile.exception;

import org.example.server_mobile.dto.request.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
//        ApiResponse response = new ApiResponse();
//        response.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
//        response.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
//        return ResponseEntity.badRequest().body(response);
//    }
    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse response = new ApiResponse();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(response);
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException e) {
//
//        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
//        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(ApiResponse.builder()
//                .code(errorCode.getCode())
//                .message(errorCode.getMessage())
//                .build());
//
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        try {

            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException ex){
            // Do nothing
        }
        ApiResponse response = new ApiResponse();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
