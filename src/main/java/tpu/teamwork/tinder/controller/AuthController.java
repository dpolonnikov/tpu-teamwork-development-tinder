package tpu.teamwork.tinder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tpu.teamwork.tinder.dto.*;
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
    public ResponseEntity<String> requestToRegistration(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        StatusResponseDTO statusResponseDTO = authService.requestToRegistration(signUpRequestDTO);
        return ResponseEntity.status(statusResponseDTO.status()).body(statusResponseDTO.message());
    }

    @PostMapping("/confirm-registration")
    public ResponseEntity<UserResponseDTO> confirmRegistration(@RequestBody String data) {
        return ResponseEntity.ok(authService.confirmRegistration(data));
    }
}
