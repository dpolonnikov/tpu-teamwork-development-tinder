package tpu.teamwork.tinder.service.auth;

import tpu.teamwork.tinder.dto.AuthRequestDTO;
import tpu.teamwork.tinder.dto.AuthResponseDTO;
import tpu.teamwork.tinder.dto.SignUpRequestDTO;
import tpu.teamwork.tinder.dto.UserResponseDTO;

public interface AuthService {
    AuthResponseDTO createAuthToken(AuthRequestDTO authRequestDTO);
    UserResponseDTO createNewUser(SignUpRequestDTO signUpRequestDTO);
}
