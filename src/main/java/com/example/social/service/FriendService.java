package com.example.social.service;

import com.example.social.domain.model.Friend;
import com.example.social.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository repository;

    public Friend saveFriend(Long userId,Long friendId) {
        Friend entity = new Friend();
        entity.setUserId(userId);
        entity.setFriendId(friendId);
        return repository.save(entity);
    }

    @Transactional
    public void deleteFriend(Long userId,Long friendId) {
        repository.delete(friendId, userId);
    }

}
