package com.capstone.app.entity.dto.front.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FrontPostItemResponseDTO {
    private Integer postId;
    private String postTitle;
    private String postSlug;
    private String postContent;
    private LocalDateTime updatedAt;
    private String author;
    private String featuredImage;
}
