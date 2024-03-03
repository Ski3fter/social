package com.example.social.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Чат")
public class MessageDto {
    @Schema(description = "Отправитель id", example = "1")
    Long from;
    @Schema(description = "Получатель id", example = "1")
    Long to;

    @Schema(description = "Текст сообщения", example = "Lorem")
    @Size(min = 1, max = 300, message = "Текст сообщения должен содержать от 1 до 300 символов")
    @NotBlank(message = "Текст сообщения не может быть пустыми")
    private String text;


}
