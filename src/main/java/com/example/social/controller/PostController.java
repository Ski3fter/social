package com.example.social.controller;

import com.example.social.domain.dto.PostDto;
import com.example.social.domain.model.Post;
import com.example.social.service.JwtService;
import com.example.social.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "Контроллер постов")
public class PostController {

    private final JwtService jwtService;

    private final PostService postService;

    @Operation(summary = "Добавить пост")
    @PostMapping("/create")
    public ResponseEntity<Long> createPost(@RequestHeader("Authorization") String token, @RequestBody PostDto postDto) {
        Long userId = getUserByToken(token);
        Post post = postService.createPost( postDto.getText(), userId);
        return ResponseEntity.ok(post.getId());
    }

    @Operation(summary = "Обновить пост")
    @PutMapping("/update")
    public ResponseEntity<Long> updatePost(@RequestBody PostDto postDto) {
        Post post = postService.updatePost(postDto.getText(), postDto.getId());
        return ResponseEntity.ok(post.getId());
    }

    @Operation(summary = "Обновить пост")
    @PutMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Successfully deleted post");
    }

    @Operation(summary = "Получить пост по id")
    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> get(@PathVariable("id") Long postId) {
        Post post = postService.get(postId);
        PostDto postDto = convertToDto(post);
        return ResponseEntity.ok(postDto);
    }

    private static PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setText(post.getText());
        postDto.setUserId(post.getUserId());
        return postDto;
    }

    @Operation(summary = "Получить посты")
    @GetMapping("/feed")
    public ResponseEntity<List<PostDto>> getAll(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "100") Integer limit) {

        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<Post> posts = postService.getPosts(pageRequest);
        List<PostDto> list = posts.get().map(PostController::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    private Long getUserByToken(String token) {
        return jwtService.extractUserId(token.replace("Bearer ", ""));
    }

}
