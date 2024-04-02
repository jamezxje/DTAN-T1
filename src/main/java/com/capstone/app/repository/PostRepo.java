package com.capstone.app.repository;

import com.capstone.app.entity.Post;
import com.capstone.app.entity.dto.common.PostDTO;
import com.capstone.app.entity.dto.dashboard.request.PostFilterRequest;
import com.capstone.app.entity.type.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    @Query("SELECT new com.capstone.app.entity.dto.common.PostDTO(" +
            "p.postId, p.postTitle, p.postSlug,p.postContent, p.postStatus," +
            "COALESCE(pi.imageUrl, ''), p.author.fullName, p.updatedAt) " +
            "FROM Post p LEFT JOIN p.postFeaturedImage pi " +
            "WHERE p.postId = :postId " +
            "AND (:postStatus IS NULL OR p.postStatus = :postStatus)")
    PostDTO findPostDTOByPostId(@Param("postId") Integer postId, @Param("postStatus") PostStatus postStatus);

    @Query("SELECT new com.capstone.app.entity.dto.common.PostDTO(" +
            "p.postId, p.postTitle, p.postSlug,p.postContent, p.postStatus," +
            "COALESCE(pi.imageUrl, ''), p.author.fullName, p.updatedAt) " +
            "FROM Post p LEFT JOIN p.postFeaturedImage pi " +
            "WHERE p.postId = :postId")
    PostDTO findPostDTOByPostId(@Param("postId") Integer postId);

    @Query("SELECT new com.capstone.app.entity.dto.common.PostDTO(p.postId, p.postTitle, p.postSlug,p.postContent, p.postStatus, " +
            "COALESCE(pi.imageUrl, ''), p.author.fullName, p.updatedAt) FROM Post p LEFT JOIN p.postFeaturedImage pi " +
            "WHERE (:#{#request.postTitle} IS NULL OR p.postTitle LIKE %:#{#request.postTitle}%) " +
            "AND (:#{#request.postStatus} IS NULL OR p.postStatus = :#{#request.postStatus}) " +
            "AND (:#{#request.startDate} IS NULL OR p.createdAt >= :#{#request.getStartDateTime()}) " +
            "AND (:#{#request.endDate} IS NULL OR p.createdAt <= :#{#request.getEndDateTime()})"
    )
    Page<PostDTO> findByFilter(PostFilterRequest request, Pageable pageable);

    @Query("SELECT new com.capstone.app.entity.dto.common.PostDTO(p.postId, p.postTitle, p.postSlug,p.postContent, p.postStatus, " +
            "COALESCE(pi.imageUrl, ''), p.author.userName, p.updatedAt) " +
            "FROM Post p LEFT JOIN p.postFeaturedImage pi " +
            "WHERE p.postStatus = :postStatus")
    List<PostDTO> findLatestPosts(Pageable pageable, PostStatus postStatus);

    @Query("SELECT new com.capstone.app.entity.dto.common.PostDTO(p.postId, p.postTitle, p.postSlug, p.postContent, p.postStatus, " +
            "COALESCE(pi.imageUrl, ''), p.author.userName, p.updatedAt) " +
            "FROM Post p LEFT JOIN p.postFeaturedImage pi " +
            "WHERE p.postSlug = :postSlug AND p.postStatus = :postStatus")
    PostDTO findPublishPostDTOByPostSlug(String postSlug, PostStatus postStatus);
}

