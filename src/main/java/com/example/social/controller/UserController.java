package com.example.social.controller;

import com.example.social.domain.dto.JwtAuthenticationResponse;
import com.example.social.domain.dto.SignInRequest;
import com.example.social.domain.dto.UserDto;
import com.example.social.domain.model.User;
import com.example.social.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Авторизация пользователя")
    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.getByUserId(id);
        return convertUserDto(user);
    }

    private UserDto convertUserDto(User user) {
        return UserDto.builder().id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .biography(user.getBiography())
                .email(user.getEmail())
                .birthdate(user.getBirthdate())
                .city(user.getCity())
                .build();
    }

    @Operation(summary = "Авторизация пользователя")
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> search(@RequestParam String firstName, @RequestParam String lastName) {

        if (firstName == null || lastName == null) {
            return ResponseEntity.badRequest().build();
        }

        List<User> users = userService.getFirstLastNameLike(firstName, lastName);
        return ResponseEntity.ok(users.stream().map(this::convertUserDto).collect(Collectors.toList()));
    }

}
