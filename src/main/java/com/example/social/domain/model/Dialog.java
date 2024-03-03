package com.example.social.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dialogs")
public class Dialog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dialog_id_seq")
    @SequenceGenerator(name = "dialog_id_seq", sequenceName = "dialog_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "from_user_id")
    private Long from;
    @Column(name = "to_user_id")
    private Long to;

    @Column(name = "text")
    private String text;


}
