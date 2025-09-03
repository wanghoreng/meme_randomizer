package com.meme.randomizer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "meme")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // BIGSERIAL 매핑
    private Long id;

    @Column(name = "image_url", nullable = false, length = 255)
    private String imageUrl;

    @Builder.Default
    @Column(name = "like_count", nullable = false)
    private int likeCount = 0;

    @Builder.Default
    @Column(name = "view_count", nullable = false)
    private int viewCount = 0;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
