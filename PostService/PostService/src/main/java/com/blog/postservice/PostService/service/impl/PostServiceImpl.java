package com.blog.postservice.PostService.service.impl;


import com.blog.postservice.PostService.Repository.PostRepository;
import com.blog.postservice.PostService.entity.Post;
import com.blog.postservice.PostService.exception.ResourceNotFoundException;
import com.blog.postservice.PostService.payload.PostDto;
import com.blog.postservice.PostService.payload.PostResponse;
import com.blog.postservice.PostService.service.PostService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    //***************************Create Post ********************************
    @Override
    public PostDto createPost(PostDto postDto)
    {

        Post post = this.modelMapper.map(postDto, Post.class);

        post.setAddDate(java.time.LocalDateTime.now());
        post.setImageName("default.png");

        Post savedPost = this.postRepository.save(post);

        return this.modelMapper.map(savedPost, PostDto.class);
    }

    //*************************** Update Post ********************************
    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=this.postRepository.save(post);

        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    //*************************** Delete Post ********************************
    @Override
    public String deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        postRepository.delete(post);
        postRepository.flush();
        return "Post delete Successfully";
    }

    //*************************** Get All Post ********************************
    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort=null;

        if(sortDir.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }
        else
        {
            sort=Sort.by(sortBy).descending();
        }

        Pageable p= PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost=this.postRepository.findAll(p);
        List<Post> allPost=pagePost.getContent();


        List<PostDto> postDtos= allPost.stream()
                .map((post)->this.modelMapper.map(post,PostDto.class))
                .toList();
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotleElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());


        return postResponse;
    }

    //*************************** Get Post By ID  ********************************
    @Transactional
    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
        return this.modelMapper.map(post,PostDto.class);
    }






    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts=this.postRepository.searchByTitle("%"+keyword+"%");
        List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).toList();
        return postDtos;
    }





}
