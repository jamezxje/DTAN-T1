package com.capstone.app.service.dash;

import com.capstone.app.entity.Member;
import com.capstone.app.entity.Post;
import com.capstone.app.entity.dto.common.PostDTO;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.dto.dashboard.request.PostRequestDTO;

import com.capstone.app.entity.dto.dashboard.request.PostFilterRequest;
import com.capstone.app.entity.type.PostStatus;

import java.util.List;

public interface PostService {

    List<PostDTO> findLatestPublishedPosts(Integer limit, PostStatus postStatus);
    Post savePost(PostRequestDTO postRequestDTO, Member member);
    Post updatePost(PostRequestDTO postRequestDTO);
    PostDTO getPostItemResponseDTO(Integer postId, PostStatus postStatus);
    PostDTO getPostDTOByPostId(Integer postId);
    PostDTO getPublishPostDTOByPostSlug(String postSlug);
    PaginationResponse<List<PostDTO>> findPostsByFilter(PostFilterRequest request);
    void changeStatus(Integer postId, PostStatus postStatus);
    void deletePost(Integer postId);
}
