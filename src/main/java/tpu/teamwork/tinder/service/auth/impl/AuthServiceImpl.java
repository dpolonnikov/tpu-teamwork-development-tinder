package tpu.teamwork.tinder.service.auth.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tpu.teamwork.tinder.dto.AuthRequestDTO;
import tpu.teamwork.tinder.dto.AuthResponseDTO;
import tpu.teamwork.tinder.dto.SignUpRequestDTO;
import tpu.teamwork.tinder.dto.UserResponseDTO;
import tpu.teamwork.tinder.exception.UnauthorizedUserException;
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
    private final AuthenticationManager authenticationManager;

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
    public UserResponseDTO createNewUser(SignUpRequestDTO signUpRequestDTO) {
        if(!Objects.equals(signUpRequestDTO.password(), signUpRequestDTO.confirmPassword())) {
            throw new UnauthorizedUserException(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают");
        }
        if(userService.findByUsername(signUpRequestDTO.username()).isPresent()) {
            throw new UnauthorizedUserException(HttpStatus.BAD_REQUEST.value(), "Такой пользователь уже есть");
        }
        return userService.createNewUser(signUpRequestDTO);
    }
}
