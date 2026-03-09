package tpu.teamwork.tinder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tpu.teamwork.tinder.dto.AuthRequestDTO;
import tpu.teamwork.tinder.dto.AuthResponseDTO;
import tpu.teamwork.tinder.dto.SignUpRequestDTO;
import tpu.teamwork.tinder.dto.UserResponseDTO;
import tpu.teamwork.tinder.service.auth.AuthService;

@RestController("api/v1/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDTO> createAuthToken(@RequestBody AuthRequestDTO authRequestDTO) {
        AuthResponseDTO authResponseDTO = authService.createAuthToken(authRequestDTO);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserResponseDTO> createNewUser(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        UserResponseDTO userResponseDTO = authService.createNewUser(signUpRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }
}
