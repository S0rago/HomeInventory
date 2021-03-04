package ru.sorago.homeinv.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class ApiError implements Serializable {
    public static final String INVALID_REQUEST = "invalid_request";

    String error;
    @JsonProperty("error_description")
    String errorDescription;
    String statusText;

    public ApiError(String error, String errorDescription, String statusText) {
        this.error = error;
        this.errorDescription = errorDescription;
        this.statusText = statusText;
    }

    public ApiError(String errorDescription, String statusText) {
        this(INVALID_REQUEST, errorDescription, statusText);
    }

    public ApiError(String errorDescription) {
        this(errorDescription, HttpStatus.BAD_REQUEST.toString());
    }

    public ApiError() {
        this("Bad request", HttpStatus.BAD_REQUEST.toString());
    }
}

