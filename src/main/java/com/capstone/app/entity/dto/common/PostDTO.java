package com.capstone.app.entity.dto.common;

import com.capstone.app.entity.type.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Integer postId;
    private String postTitle;
    private String postSlug;
    private String postContent;
    private PostStatus postStatus;
    private String postFeaturedImageUrl = "";
    private String author;
    private LocalDateTime updatedAt;
}
