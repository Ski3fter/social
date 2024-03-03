package com.example.social.repository;

import com.example.social.domain.model.Friend;
import com.example.social.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Modifying
    @Query("DELETE FROM Friend f where f.friendId = :friendId and f.userId = :userId")
    void delete(Long friendId, Long userId);

}
