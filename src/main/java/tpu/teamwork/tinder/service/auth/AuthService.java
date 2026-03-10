package tpu.teamwork.tinder.service.auth;

import tpu.teamwork.tinder.dto.*;
import tpu.teamwork.tinder.entity.User;

public interface AuthService {
    AuthResponseDTO createAuthToken(AuthRequestDTO authRequestDTO);
    StatusResponseDTO requestToRegistration(SignUpRequestDTO signUpRequestDTO);
    UserResponseDTO  confirmRegistration(String hash);
}
