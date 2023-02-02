package com.harishverma.blog.services;

import com.harishverma.blog.entities.Post;
import com.harishverma.blog.payloads.PostDTO;
import com.harishverma.blog.payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId);
    PostDTO getPost(Integer id);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDTO updatePost(PostDTO postDTO, Integer id);
    void deletePost(Integer id);

    List<PostDTO> getAllPostByCategory(Integer categoryId);
    List<PostDTO> getAllPostByUser(Integer userId);

    List<PostDTO> searchPosts(String keyword);

}
