package ru.sorago.homeinv.service;

import org.springframework.security.authentication.BadCredentialsException;
import ru.sorago.homeinv.core.JwtUtils;
import ru.sorago.homeinv.core.MimeEncoder;
import ru.sorago.homeinv.data.UserPrincipal;
import ru.sorago.homeinv.data.request.LoginRequest;
import ru.sorago.homeinv.data.request.RegistrationRequest;
import ru.sorago.homeinv.data.response.LoginResponse;
import ru.sorago.homeinv.data.response.base.Response;
import ru.sorago.homeinv.data.type.LoggingUser;
import ru.sorago.homeinv.exception.ApiError;
import ru.sorago.homeinv.exception.BadRequestException;
import ru.sorago.homeinv.model.User;
import ru.sorago.homeinv.repository.UserRepository;
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
        LoginResponse loginResponse = new LoginResponse();
        try {
            Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                    Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getEmail(), MimeEncoder.decode(request.getPassword()))
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String jwt = jwtUtils.generateJwtToken(authentication);

                    LoggingUser loggingUser = new LoggingUser();
                    loggingUser.setName(user.getName());
                    loggingUser.setEmail(user.getEmail());

                    loginResponse.setToken(jwt);
                    loginResponse.setLoggingUser(loggingUser);
                    loginResponse.setMessage("Login successful");
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        } catch (Exception ex) {
            loginResponse.setSuccess(false);
            loginResponse.setMessage(ex.getMessage());
        }
        loginResponse.setTimestamp();
        return loginResponse;
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
        user.setPasswordHash(cryptoService.encode(request.getPassword()));

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
