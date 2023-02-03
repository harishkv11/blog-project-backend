package com.harishverma.blog.services.Impl;

import com.harishverma.blog.entities.Comment;
import com.harishverma.blog.entities.Post;
import com.harishverma.blog.exceptions.ResourceNotFoundException;
import com.harishverma.blog.payloads.CommentDTO;
import com.harishverma.blog.repositories.CommentRepo;
import com.harishverma.blog.repositories.PostRepo;
import com.harishverma.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id", postId));
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        return modelMapper.map(commentRepo.save(comment), CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepo.delete(comment);
    }
}
