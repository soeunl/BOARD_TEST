package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.global.entities.BaseEntity;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BoardData extends BaseEntity {
    @Id @GeneratedValue
    private Long seq;

    @Column(length = 65, unique = true, nullable = false)
    private String email;

    @Column(length = 65, nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;
}
