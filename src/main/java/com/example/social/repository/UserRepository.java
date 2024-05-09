package com.example.social.repository;

import com.example.social.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:firstName% and u.lastName LIKE %:lastName%")
    Optional<List<User>> findByFirstLastNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);
    @Query(value = "SELECT * FROM users WHERE users.first_name_ts @@ to_tsquery(:firstName) and users.second_name_ts @@ to_tsquery(:lastName)",
            nativeQuery = true)
    Optional<List<User>> findByFirstLastNameIndex(@Param("firstName") String firstName, @Param("lastName") String lastName);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
