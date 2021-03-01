package ru.sorago.homeinv.data.response;

import ru.sorago.homeinv.data.response.base.Response;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sorago.homeinv.data.type.LoggingUser;

@Data
@NoArgsConstructor
public class LoginResponse extends Response {
    private LoggingUser loggingUser;
    private String token;
}
