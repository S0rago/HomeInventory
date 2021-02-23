package com.sorago.homeinv.data.response;

import com.sorago.homeinv.data.response.base.Response;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginAttempt extends Response {
    private String token;
}
