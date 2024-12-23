package org.example.server_mobile.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(1001, "User not found", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1001, "User not existed", HttpStatus.NOT_FOUND),
    USER_EXISTED(1001, "User existed", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(1002, "Email already exists", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1003, "User not authenticated", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1004, "User not permission", HttpStatus.FORBIDDEN),
    INVALID_PASSWORD(1005, "Password must be between 8 and 20 characters", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(1005, "Invalid request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(1006, "Internal server error", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    TOKEN_EXPIRED(1007,"token expired", HttpStatus.BAD_REQUEST),;

    private final int code;
    private final String message;

    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
