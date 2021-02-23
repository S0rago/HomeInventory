package com.sorago.homeinv.service;

import com.sorago.homeinv.data.request.LoginRequest;
import com.sorago.homeinv.data.request.RegistrationRequest;
import com.sorago.homeinv.data.response.base.Response;

public interface UserService {
    Response login(LoginRequest request);

    Response logout();

    Response register(RegistrationRequest request);
}
