package tpu.teamwork.tinder.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequestDTO(
    @Size(min = 5, max = 64, message = "Username must be between 5 and 64 characters long.")
    @NotBlank(message = "Password cannot be empty")
    String username,
    @Size(max = 32, message = "The password length must be no more than 255 characters.")
    @NotBlank(message = "Username cannot be empty")
    String password,
    @Size(max = 32, message = "The password length must be no more than 255 characters.")
    @NotBlank(message = "Username cannot be empty")
    String confirmPassword,
    @Email
    String email
) {}
