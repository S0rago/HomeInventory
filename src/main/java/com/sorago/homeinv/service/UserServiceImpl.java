package com.sorago.homeinv.service;

import com.sorago.homeinv.core.JwtUtils;
import com.sorago.homeinv.data.UserPrincipal;
import com.sorago.homeinv.data.request.LoginRequest;
import com.sorago.homeinv.data.request.RegistrationRequest;
import com.sorago.homeinv.data.response.LoginAttempt;
import com.sorago.homeinv.data.response.base.Response;
import com.sorago.homeinv.exception.ApiError;
import com.sorago.homeinv.exception.BadRequestException;
import com.sorago.homeinv.model.User;
import com.sorago.homeinv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final CryptoService cryptoService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public Response login(LoginRequest request) {
        LoginAttempt loginAttempt = new LoginAttempt();
        try {
            Authentication authentication
                    = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            loginAttempt.setToken(jwt);

        } catch (Exception ex) {
            loginAttempt.setSuccess(false);
        }
        return loginAttempt;
    }

    @Override
    public Response logout() {
        return new Response("ok");
    }

    @Override
    public Response register(RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException(new ApiError("Email already exists", HttpStatus.BAD_REQUEST.toString()));
        }
        if (!(request.getPassword().equals(request.getPasswordValidation()))) {
            throw new BadRequestException(new ApiError("Passwords don't match", HttpStatus.BAD_REQUEST.toString()));
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(String.valueOf(cryptoService.encode(request.getPassword())));

        userRepository.save(user);

        return new Response("Registration complete!");
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        return new UserPrincipal(user.get());
    }
}
