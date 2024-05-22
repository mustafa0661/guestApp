package com.project.guestApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.guestApp.entities.Comment;
import com.project.guestApp.entities.Post;
import com.project.guestApp.entities.User;
import com.project.guestApp.repos.CommentRepository;
import com.project.guestApp.requests.CommentCreateRequest;
import com.project.guestApp.requests.CommentUpdateRequest;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	public CommentService(CommentRepository commentRepository, PostService postService, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<Comment> getAllCommentWithParam(Optional<Long> userId, Optional<Long> postId) {
		if (userId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
		} else if (userId.isPresent()) {
			return commentRepository.findByUserId(userId.get());
		} else if (postId.isPresent()) {
			return commentRepository.findByPostId(postId.get());
		} else
			return commentRepository.findAll();
	}

	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentCreateRequest newCommentRequest) {
		User user = userService.getOneUserById(newCommentRequest.getUserId());
		Post post = postService.getOnePostById(newCommentRequest.getPostId());
		if (user != null && post != null) {
			Comment commentToSave = new Comment();
			commentToSave.setId(newCommentRequest.getId());
			commentToSave.setText(newCommentRequest.getText());
			commentToSave.setUser(user);
			commentToSave.setPost(post);
			return commentRepository.save(commentToSave);
		} else
			return null;
	}

	public Comment updateOneCommentById(Long commentId, CommentUpdateRequest updateCommentRequest) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isPresent()) {
			Comment toUpdate = comment.get();
			toUpdate.setText(updateCommentRequest.getText());
			commentRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOneCommentById(Long commentId) {
		commentRepository.deleteById(commentId);

	}

}
