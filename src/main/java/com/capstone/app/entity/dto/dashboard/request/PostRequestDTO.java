package com.capstone.app.entity.dto.dashboard.request;

import com.capstone.app.entity.Post;
import com.capstone.app.entity.type.PostStatus;
import com.capstone.app.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private PostStatus postStatus = PostStatus.DRAFT;
    private MultipartFile postFeaturedImage;

    public Post toEntity() {
        Post post = new Post();
        post.setPostTitle(this.postTitle);
        post.setPostSlug(StringUtil.toSlug(this.postTitle));
        post.setPostContent(this.postContent);
        post.setPostStatus(this.postStatus);
        return post;
    }
}
