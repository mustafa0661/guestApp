package com.project.guestApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.guestApp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
