package ru.sorago.homeinv.controller;

import ru.sorago.homeinv.data.request.LoginRequest;
import ru.sorago.homeinv.data.request.RegistrationRequest;
import ru.sorago.homeinv.data.response.base.RecordResponse;
import ru.sorago.homeinv.data.response.type.LoginData;
import ru.sorago.homeinv.data.response.type.ResponseMessage;
import ru.sorago.homeinv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecordResponse<LoginData>> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<RecordResponse<ResponseMessage>> logout() {
        return ResponseEntity.ok(userService.logout());
    }

    @PostMapping("/register")
    public ResponseEntity<RecordResponse<ResponseMessage>> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

}
