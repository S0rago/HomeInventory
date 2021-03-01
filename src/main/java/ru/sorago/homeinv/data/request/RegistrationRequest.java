package ru.sorago.homeinv.data.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
    private String passwordValidation;
}
