package ru.sorago.homeinv.service;

import ru.sorago.homeinv.data.request.LoginRequest;
import ru.sorago.homeinv.data.request.RegistrationRequest;
import ru.sorago.homeinv.data.response.base.RecordResponse;
import ru.sorago.homeinv.data.response.type.LoginData;
import ru.sorago.homeinv.data.response.type.ResponseMessage;

public interface UserService {
    RecordResponse<LoginData> login(LoginRequest request);

    RecordResponse<ResponseMessage> logout();

    RecordResponse<ResponseMessage> register(RegistrationRequest request);
}
