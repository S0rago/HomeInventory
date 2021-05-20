package ru.sorago.homeinv.data.response.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class BaseResponse {
    private String message = "";
    private long timestamp = Instant.now().getEpochSecond();
    private boolean success = true;

    public BaseResponse(String message) {
        success = false;
        this.message = message;
    }
}
