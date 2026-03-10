package tpu.teamwork.tinder.service.auth.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tpu.teamwork.tinder.config.kafka.KafkaProducer;
import tpu.teamwork.tinder.dto.*;
import tpu.teamwork.tinder.entity.User;
import tpu.teamwork.tinder.exception.UnauthorizedUserException;
import tpu.teamwork.tinder.mapper.UserConfirmMapper;
import tpu.teamwork.tinder.service.Base64Service;
import tpu.teamwork.tinder.service.UserService;
import tpu.teamwork.tinder.service.auth.AuthService;
import tpu.teamwork.tinder.utils.JwtTokenUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final Base64Service base64Service;
    private final KafkaProducer kafkaProducer;
    private final AuthenticationManager authenticationManager;
    private final String EMAIL_TOPIC = "email_topic";

    @Override
    public AuthResponseDTO createAuthToken(AuthRequestDTO authRequestDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.username(), authRequestDTO.password()));
        } catch (BadCredentialsException e) {
            throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.value(), "Не получилось авторизовать пользователя");
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequestDTO.username());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new AuthResponseDTO(token);
    }

    @Override
    public StatusResponseDTO requestToRegistration(SignUpRequestDTO signUpRequestDTO) {
        try {
            if(!Objects.equals(signUpRequestDTO.password(), signUpRequestDTO.confirmPassword())) {
                throw new UnauthorizedUserException(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают");
            }
            if(userService.findByUsername(signUpRequestDTO.username()).isPresent()) {
                throw new UnauthorizedUserException(HttpStatus.BAD_REQUEST.value(), "Такой пользователь уже есть");
            }
            var dataToSend = base64Service.encode(signUpRequestDTO);
            kafkaProducer.produce(EMAIL_TOPIC, new KafkaEmailDTO(signUpRequestDTO.email(), dataToSend));

            return new StatusResponseDTO(201, "Сообщение об отправке на почту создано");
        } catch (Exception e) {
            return new StatusResponseDTO(400, e.getMessage());
        }

    }

    @Override
    public UserResponseDTO confirmRegistration(String hash) {
        var userConfirmDTO = base64Service.decode(hash, UserConfirmRequestDTO.class);
        return userService.createNewUser(userConfirmDTO);
    }
}
