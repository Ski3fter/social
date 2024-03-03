package com.example.social.service;

import com.example.social.domain.model.Post;
import com.example.social.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(String text, Long userId) {
        final Post post = new Post();
        post.setUserId(userId);
        post.setText(text);

        return postRepository.save(post);
    }

    public Post updatePost(String text, Long id) {
        Post post = postRepository.getReferenceById(id);
        post.setText(text);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
       postRepository.deleteById(id);
    }

    public Post get(Long id) {
        return postRepository.getReferenceById(id);
    }

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }



}
