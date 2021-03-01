package ru.sorago.homeinv.data.response.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        this.timestamp = Instant.now().getEpochSecond();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
