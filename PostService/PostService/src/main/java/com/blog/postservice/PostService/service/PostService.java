package com.blog.postservice.PostService.service;

import com.blog.postservice.PostService.payload.PostDto;
import com.blog.postservice.PostService.payload.PostResponse;

import java.util.List;

public interface PostService {
    //create

    PostDto createPost(PostDto postDto);

    //update

    PostDto updatePost(PostDto postDto,Integer postId);

    //Delete
    String deletePost(Integer postId);

    //Get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //Get post by id

    PostDto getPostById(Integer postId);

    //Get all post by Category



    //Search Post

    List <PostDto> searchPosts(String keyword);
}
