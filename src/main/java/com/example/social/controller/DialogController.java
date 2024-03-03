package com.example.social.controller;

import com.example.social.domain.dto.MessageDto;
import com.example.social.domain.model.Dialog;
import com.example.social.service.DialogService;
import com.example.social.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dialog")
@RequiredArgsConstructor
@Tag(name = "Чаты")
public class DialogController {

    private final JwtService jwtService;

    private final DialogService dialogService;

    @Operation(summary = "Отправка сообщения в чат")
    @PostMapping("/{userId}/send")
    public ResponseEntity<Long> sendMessage(@RequestHeader("Authorization") String token,
                                            @PathVariable("userId") Long friendId,
                                            @RequestBody MessageDto messageDto) {
        Long userId = getUserByToken(token);
        Dialog send = dialogService.send(friendId, userId, messageDto.getText());
        return ResponseEntity.ok(send.getId());
    }

    @Operation(summary = "Отправка сообщения в чат")
    @GetMapping("/{userId}/list")
    public ResponseEntity<List<MessageDto>> list(@RequestHeader("Authorization") String token,
                                            @PathVariable("userId") Long friendId) {
        Long userId = getUserByToken(token);
        List<Dialog> dialogs = dialogService.getDialogs(friendId, userId);
        List<MessageDto> messages = dialogs.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(messages);
    }

    private MessageDto convertToDto(Dialog dialog) {
        MessageDto messageDto = new MessageDto();
        messageDto.setFrom(dialog.getFrom());
        messageDto.setTo(dialog.getTo());
        messageDto.setText(dialog.getText());
        return messageDto;
    }

    private Long getUserByToken(String token) {
        return jwtService.extractUserId(token.replace("Bearer ", ""));
    }

}
