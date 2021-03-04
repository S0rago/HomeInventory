package ru.sorago.homeinv.data.response.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class Response {
    private String error = "";
    private long timestamp = Instant.now().getEpochSecond();
    private boolean success;

    public Response(String error) {
        success = true;
        this.error = error;
    }
}
