package com.project.guestApp.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.guestApp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByUserIdAndPostId(Long userId, Long postId);

	List<Comment> findByUserId(Long userId);

	List<Comment> findByPostId(Long postId);

}
