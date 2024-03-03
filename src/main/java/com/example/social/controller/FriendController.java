package com.example.social.controller;

import com.example.social.domain.dto.UserDto;
import com.example.social.domain.model.Friend;
import com.example.social.domain.model.User;
import com.example.social.service.FriendService;
import com.example.social.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
@Tag(name = "Контроллер друзей")
public class FriendController {

    private final FriendService friendService;
    private final JwtService jwtService;

    @Operation(summary = "Добавить друга")
    @PutMapping("/set/{userId}")
    public ResponseEntity<Long> addFriend(@RequestHeader("Authorization") String token, @PathVariable("userId") Long friendId) {
        Long userId = getUserByToken(token);
        Friend friend = friendService.saveFriend(userId, friendId);
        return ResponseEntity.ok(friend.getId());
    }

    @Operation(summary = "Удалить друга")
    @PutMapping("/delete/{userId}")
    public ResponseEntity<String> removeFriend(@RequestHeader("Authorization") String token, @PathVariable("userId") Long friendId) {
        Long userId = getUserByToken(token);
        friendService.deleteFriend(userId, friendId);
        return ResponseEntity.ok("Ok");
    }

    private Long getUserByToken(String token) {
        return jwtService.extractUserId(token.replace("Bearer ", ""));
    }

}
