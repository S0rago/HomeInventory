package ru.sorago.homeinv.data.response.type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginData {
    private String name;
    private String email;
    private String token;
}
