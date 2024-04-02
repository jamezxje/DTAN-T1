package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Image;
import com.capstone.app.entity.Member;
import com.capstone.app.entity.Post;
import com.capstone.app.entity.dto.common.PostDTO;
import com.capstone.app.entity.dto.dashboard.request.PostFilterRequest;
import com.capstone.app.entity.dto.dashboard.request.PostRequestDTO;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.PostStatus;
import com.capstone.app.repository.MemberRepo;
import com.capstone.app.repository.PostRepo;
import com.capstone.app.service.common.FilesStorageService;
import com.capstone.app.service.dash.PostService;
import com.capstone.app.util.RequestUtils;
import com.capstone.app.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final FilesStorageService filesStorageService;

    @Override
    public List<PostDTO> findLatestPublishedPosts(Integer limit, PostStatus postStatus) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("createdAt").descending());
        return postRepo.findLatestPosts(pageable, postStatus);
    }

    @Override
    public Post savePost(PostRequestDTO postRequestDTO, Member member) {
        LocalDateTime now = LocalDateTime.now();
        Post post = postRequestDTO.toEntity();
        post.setAuthor(member);
        post.setCreatedAt(now);
        post.setUpdatedAt(now);

        if ( !postRequestDTO.getPostFeaturedImage().isEmpty() ) {
            Image image = filesStorageService.save(postRequestDTO.getPostFeaturedImage());
            post.setPostFeaturedImage(image);
        }

        postRepo.save(post);

        return post;
    }

    @Override
    public Post updatePost(PostRequestDTO postRequestDTO) {
        Post post = postRepo.findById(postRequestDTO.getPostId()).orElse(null);

        if (post == null) return null;

        post.setPostTitle(postRequestDTO.getPostTitle());
        post.setPostSlug(postRequestDTO.getPostTitle());
        post.setPostSlug(StringUtil.toSlug(post.getPostTitle()));
        post.setPostContent(postRequestDTO.getPostContent());
        post.setPostStatus(postRequestDTO.getPostStatus());
        post.setUpdatedAt(LocalDateTime.now());

        if ( !postRequestDTO.getPostFeaturedImage().isEmpty() ) {
            if(post.getPostFeaturedImage() != null) {
                filesStorageService.delete(post.getPostFeaturedImage());
            }
            Image image = filesStorageService.save(postRequestDTO.getPostFeaturedImage());
            post.setPostFeaturedImage(image);
        }

        postRepo.save(post);
        return post;
    }


    @Override
    public PostDTO getPostItemResponseDTO(Integer postId, PostStatus postStatus) {
        return postRepo.findPostDTOByPostId(postId, postStatus);
    }

    @Override
    public PostDTO getPostDTOByPostId(Integer postId) {
        return postRepo.findPostDTOByPostId(postId);
    }

    @Override
    public PostDTO getPublishPostDTOByPostSlug(String postSlug) {
        return postRepo.findPublishPostDTOByPostSlug(postSlug, PostStatus.PUBLISH);
    }

    @Override
    public PaginationResponse<List<PostDTO>> findPostsByFilter(PostFilterRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by("createdAt").descending());
        Page<PostDTO> page = postRepo.findByFilter(request, pageable);
        return new PaginationResponse<>(page.getContent(), request.getPageIndex() + 1
                , page.getTotalPages() + 1, page.getTotalElements(), request.getPageSize());
    }

    @Override
    public void changeStatus(Integer postId, PostStatus postStatus) {
        Post post = postRepo.findById(postId).orElse(null);
        if (post != null) {
            post.setPostStatus(postStatus);
            postRepo.save(post);
        }

    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElse(null);
        if (post != null) {
            if (post.getPostFeaturedImage() != null) {
                filesStorageService.delete(post.getPostFeaturedImage());
            }
            postRepo.delete(post);
        }
    }

}
