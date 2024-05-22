package com.project.guestApp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.guestApp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

}
