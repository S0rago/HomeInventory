package com.sorago.homeinv.controller;

import com.sorago.homeinv.data.request.LoginRequest;
import com.sorago.homeinv.data.response.base.Response;
import com.sorago.homeinv.service.UserService;
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
    public ResponseEntity<Response> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout() {
        return ResponseEntity.ok(userService.logout());
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register() {
        return ResponseEntity.ok(userService.logout());
    }

}
