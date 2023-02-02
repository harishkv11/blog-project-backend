package com.harishverma.blog.services.Impl;

import com.harishverma.blog.entities.Category;
import com.harishverma.blog.entities.Post;
import com.harishverma.blog.entities.User;
import com.harishverma.blog.exceptions.ResourceNotFoundException;
import com.harishverma.blog.payloads.CategoryDTO;
import com.harishverma.blog.payloads.PostDTO;
import com.harishverma.blog.payloads.PostResponse;
import com.harishverma.blog.repositories.CategoryRepo;
import com.harishverma.blog.repositories.PostRepo;
import com.harishverma.blog.repositories.UserRepo;
import com.harishverma.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));


        Post post = modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        return modelMapper.map(postRepo.save(post), PostDTO.class);

    }

    @Override
    public PostDTO getPost(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable  pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepo.findAll(pageable);

        List<Post> posts = pagePost.getContent();
        List<PostDTO> postDTOs = posts.stream()
                .map((post -> modelMapper.map(post,PostDTO.class)))
                .collect(Collectors.toList());

        return PostResponse.builder()
                .pageSize(pagePost.getSize())
                .pageNumber(pagePost.getNumber())
                .content(postDTOs)
                .totalElements(pagePost.getTotalElements())
                .totalPages(pagePost.getTotalPages())
                .lastPage(pagePost.isLast())
                .build();
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepo.delete(post);
    }

    @Override
    public List<PostDTO> getAllPostByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        List<Post> posts = postRepo.findByCategory(category);

        return posts.stream()
                .map((post -> modelMapper.map(post,PostDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<Post> posts = postRepo.findByUser(user);

        return posts.stream()
                .map((post -> modelMapper.map(post,PostDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = postRepo.findByTitleContaining(keyword);
        return posts.stream().map((post) -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }
}
