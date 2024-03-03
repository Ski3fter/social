package com.example.social.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Пост")
public class PostDto {

    @Schema(description = "Идентификатор поста", example = "1")
    private Long id;
    @Schema(description = "Текст поста", example = "Lorem")
    @Size(min = 1, max = 300, message = "Текст поста должен содержать от 1 до 300 символов")
    @NotBlank(message = "Текст поста не может быть пустыми")
    private String text;

    @Schema(description = "Идентификатор пользователя создавшего пост", example = "1")
    private Long userId;

}
