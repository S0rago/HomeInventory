package ru.sorago.homeinv.service;

import ru.sorago.homeinv.data.request.LoginRequest;
import ru.sorago.homeinv.data.request.RegistrationRequest;
import ru.sorago.homeinv.data.response.base.Response;

public interface UserService {
    Response login(LoginRequest request);

    Response logout();

    Response register(RegistrationRequest request);
}
