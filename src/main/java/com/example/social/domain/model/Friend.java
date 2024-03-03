package com.example.social.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "friends")
public class Friend {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_id_seq")
    @SequenceGenerator(name = "friend_id_seq", sequenceName = "friend_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "friend_id")
    private Long friendId;


}
