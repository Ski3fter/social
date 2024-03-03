package com.example.social.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    @Schema(description = "Никнейм", example = "Jon")
    @Size(min = 5, max = 50, message = "Никнейм должен содержать от 5 до 50 символов")
    @NotBlank(message = "Никнейм не может быть пустыми")
    private String username;

    @Schema(description = "Имя пользователя", example = "Jon")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Grohotan")
    @Size(min = 5, max = 50, message = "Фамилия пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Фамилия пользователя не может быть пустыми")
    private String lastName;

    @Schema(description = "Дата рождения", example = "02-23-2233")
    private LocalDate birthdate;

    @Schema(description = "Хобби пользователя", example = "Спорт")
    @Size(max = 300, message = "Хобби пользователя должно содержать до 300 символов")
    @NotBlank(message = "Хобби пользователя не может быть пустыми")
    private String biography;

    @Schema(description = "Город пользователя", example = "Grohotan")
    @Size(max = 100, message = "Город пользователя должно содержать до 100 символов")
    @NotBlank(message = "Город пользователя не может быть пустыми")
    private String city;

    @Schema(description = "Адрес электронной почты", example = "jondoe@gmail.com")
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(max = 255, message = "Длина пароля должна быть не более 255 символов")
    private String password;
}
