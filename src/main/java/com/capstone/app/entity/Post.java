package com.capstone.app.entity;

import com.capstone.app.entity.type.PostStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @Column(name = "post_title", nullable = false, length = 256)
    @NotNull
//    @Size(min = 10, max = 256)
    private String postTitle;

    @Column(name = "slug", nullable = false, length = 256, unique = true)
    @NotNull
//    @Size(min = 3, max = 256)
    private String postSlug;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    @NotNull
    private String postContent;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Nullable
    private Image postFeaturedImage;

    @Column(name = "post_status", nullable = false, length = 10, columnDefinition = "VARCHAR(10) DEFAULT 'DRAFT'")
    @Enumerated(EnumType.STRING)
    @NotNull
    private PostStatus postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    public Member author;

    @Column(name = "created_at", updatable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @NotNull
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedAt;

}
