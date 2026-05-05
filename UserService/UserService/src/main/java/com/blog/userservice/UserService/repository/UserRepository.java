package com.blog.userservice.UserService.repository;
import com.blog.userservice.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
