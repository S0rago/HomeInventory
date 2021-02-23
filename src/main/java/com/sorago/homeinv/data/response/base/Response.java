package com.sorago.homeinv.data.response.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class Response {

    private String message;
    private long timestamp;
    private boolean success;

    public Response(String message) {
        success = true;
        this.message = message;
    }

    public void setTimestamp() {
        this.timestamp = Instant.now().toEpochMilli();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
