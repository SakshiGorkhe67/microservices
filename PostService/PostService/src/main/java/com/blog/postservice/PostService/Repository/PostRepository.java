package com.blog.postservice.PostService.Repository;

import com.blog.postservice.PostService.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key")String title);



}
